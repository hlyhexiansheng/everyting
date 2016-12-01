package kafka;

import org.apache.commons.lang3.StringUtils;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import serializer.AvroSerializer;

import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by noodles on 16/9/30 上午9:59.
 */
public class KakkaPro {

    private static Properties props;

    private static String[] TOPICS = new String[]{KafkaDef.Topic_One,KafkaDef.Topic_Two,KafkaDef.Topic_Three};

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
//            String topicName = TOPICS[val.hashCode() % TOPICS.length];
            final byte[] bytes = AvroSerializer.writeEvent(val);

            final ProducerRecord<String, byte[]> record = new ProducerRecord<>("SearchSystem", "eventkey", bytes);

            final Future<RecordMetadata> future = producer.send(record);

//            final Future<RecordMetadata> future = producer.send(new ProducerRecord<>("SearchSystem",val));
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

    /**
     * 自动插入
     * @throws InterruptedException
     * @throws ExecutionException
     */
    private static void autoPushMessage() throws InterruptedException, ExecutionException {

        Producer<String, String> producer = new KafkaProducer<>(props);
        for(int i = 0; i < 100; i++){
            final Future<RecordMetadata> future = producer.send(new ProducerRecord<>("test", Integer.toString(i), Integer.toString(i)));
            final RecordMetadata recordMetadata = future.get();
            System.out.println(recordMetadata);
        }
        producer.close();
    }

}
