package kafka;

import org.apache.commons.lang3.StringUtils;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by noodles on 16/9/30 上午9:59.
 */
public class KakkaPro3 {

    private static Properties props;

    public static void main(String[] args) throws ExecutionException, InterruptedException, IOException {

        initProperty();

        pushMessage();

    }

    private static void pushMessage() throws ExecutionException, InterruptedException, IOException {

        Producer<String, String> producer = new KafkaProducer<>(props);

        for (; ; ) {
            Scanner scanner = new Scanner(System.in);
            final String tag = scanner.nextLine();
            if (StringUtils.isEmpty(tag)) {
                continue;
            }

            final ProducerRecord<String, String> record = new ProducerRecord<>("abcd", "eventkey", tag);

            final Future<RecordMetadata> future = producer.send(record);

            final RecordMetadata metadata = future.get();

            System.out.println(String.format("offset=%s,partition=%s,topic=%s", metadata.offset(), metadata.partition(), metadata.topic()));
        }
    }


    private static void initProperty() {
        props = new Properties();
        props.put("bootstrap.servers", "10.40.6.151:9092");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
    }


}
