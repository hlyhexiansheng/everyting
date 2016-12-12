package kafka;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by noodles on 16/9/30 上午9:59.
 */
public class KakkaPro2 {

    private static Properties props;

    public static void main(String[] args) throws ExecutionException, InterruptedException, IOException {

        initProperty();

        pushMessage();

    }

    private static void pushMessage() throws ExecutionException, InterruptedException, IOException {

        Producer<String, byte[]> producer = new KafkaProducer<>(props);

        for(;;){
            Scanner scanner = new Scanner(System.in);
            final String val = scanner.nextLine();
            if(StringUtils.isEmpty(val)){
                continue;
            }
            Map map = new HashMap();
            map.put("key",val);
            final String s = JSON.toJSONString(map);

            final ProducerRecord<String, byte[]> record = new ProducerRecord<>("test", "eventkey", s.getBytes());

            final Future<RecordMetadata> future = producer.send(record);

            final RecordMetadata metadata = future.get();

            System.out.println(String.format("offset=%s,partition=%s,topic=%s",metadata.offset(),metadata.partition(),metadata.topic()));
        }
    }


    private static void initProperty(){
        props = new Properties();
        props.put("bootstrap.servers", KafkaDef.Kafka_url);
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.ByteArraySerializer");
    }


}
