package serializer;

import org.apache.avro.io.BinaryDecoder;
import org.apache.avro.io.BinaryEncoder;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.io.EncoderFactory;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;
import org.apache.flume.source.avro.AvroFlumeEvent;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by noodles on 16/12/1 下午2:11.
 */
public class AvroTest {

    public static void main(String[] args) throws IOException {

        final byte[] bytes = writeEvent();

        String s = new String();
        AvroSerializer.deSerializer(s.getBytes());

        final AvroFlumeEvent avroFlumeEvent = readEvent(bytes);

        System.out.println("---" + avroFlumeEvent.getHeaders());
        System.out.println(new String(avroFlumeEvent.getBody().array()));
    }

    private static AvroFlumeEvent readEvent(byte[] bytes) throws IOException {

        ByteArrayInputStream in = new ByteArrayInputStream(bytes);

        BinaryDecoder decoder = DecoderFactory.get().directBinaryDecoder(in, null);

        SpecificDatumReader<AvroFlumeEvent> reader = new SpecificDatumReader<AvroFlumeEvent>(AvroFlumeEvent.class);

        AvroFlumeEvent avroevent = reader.read(null, decoder);

        return avroevent;
    }

    private static byte[] writeEvent() throws IOException {

        Map<String,String> headers = new HashMap<String, String>();
        headers.put("topic","aaaa");
        headers.put("module","bbb");

        AvroFlumeEvent e = new AvroFlumeEvent(toCharSeqMap(headers), ByteBuffer.wrap("this is bfsadfodyvrecord.valuerecord.value.this is bfsadfodyvrecord.valuerecord.valuethis is bfsadfodyvrecord.valuerecord.valuethis is bfsadfodyvrecord.valuerecord.valuethis is bfsadfodyvrecord.valuerecord.valuethis is bfsadfodyvrecord.valuerecord.valuethis is bfsadfodyvrecord.valuerecord.valuethis is bfsadfodyvrecord.valuerecord.valuethis is bfsadfodyvrecord.valuerecord.valuethis is bfsadfodyvrecord.valuerecord.valuethis is bfsadfodyvrecord.valuerecord.valuethis is bfsadfodyvrecord.valuerecord.valuethis is bfsadfodyvrecord.valuerecord.valuethis is bfsadfodyvrecord.valuerecord.valuethis is bfsadfodyvrecord.valuerecord.valuethis is bfsadfodyvrecord.valuerecord.valuethis is bfsadfodyvrecord.valuerecord.valuethis is bfsadfodyvrecord.valuerecord.valuethis is bfsadfodyvrecord.valuerecord.value".getBytes()));


        ByteArrayOutputStream tempOutStream = new ByteArrayOutputStream();

        final SpecificDatumWriter<AvroFlumeEvent> writer = new SpecificDatumWriter<AvroFlumeEvent>(AvroFlumeEvent.class);

        BinaryEncoder encoder = EncoderFactory.get().directBinaryEncoder(tempOutStream, null);

        writer.write(e, encoder);
        encoder.flush();

        return tempOutStream.toByteArray();

    }


    private static Map<CharSequence, CharSequence> toCharSeqMap(Map<String, String> stringMap) {
        Map<CharSequence, CharSequence> charSeqMap = new HashMap<CharSequence, CharSequence>();
        for (Map.Entry<String, String> entry : stringMap.entrySet()) {
            charSeqMap.put(entry.getKey(), entry.getValue());
        }
        return charSeqMap;
    }
}
