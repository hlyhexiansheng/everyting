package kafka;

import org.apache.flume.source.avro.AvroFlumeEvent;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import serializer.AvroSerializer;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * Created by noodles on 16/9/30 上午11:56.
 */
public class KafkaConsumerDemo {


    private static Properties props;

    private static class KafkaRunner extends Thread{

        private Properties properties;

        public KafkaRunner(Properties properties){
            this.properties = properties;
        }

        @Override
        public void run() {
            autoConsumer();
        }

        private void autoConsumer() {
            KafkaConsumer<String, byte[]> consumer = new KafkaConsumer<>(this.properties);
            consumer.subscribe(Arrays.asList("SearchSystem"));
            while (true) {
                ConsumerRecords<String, byte[]> records = consumer.poll(100);
                for (ConsumerRecord<String, byte[]> record : records){
                    System.out.println(record.offset());
                    System.out.println(record.key());


                    final AvroFlumeEvent avroFlumeEvent = AvroSerializer.deSerializer(record.value());

                    System.out.println(avroFlumeEvent.getHeaders());
                    System.out.println(new String(avroFlumeEvent.getBody().array()));

                    System.out.println(record.partition());
                    System.out.println(record.topic());
                }
            }

        }
    }

    public static void main(String[] args) {


//        final Properties props1 = initProps("test1");
        final Properties props2 = initProps("test2");

//        KafkaRunner kafkaRunner1 = new KafkaRunner(props1);
        KafkaRunner kafkaRunner2 = new KafkaRunner(props2);

//        kafkaRunner1.setName("thread-test1");
        kafkaRunner2.setName("thread-test2");

//        kafkaRunner1.start();
        kafkaRunner2.start();

    }

    private static Properties initProps(String groupId) {
        Properties props = new Properties();
        props.put("bootstrap.servers", KafkaDef.Kafka_url);
        props.put("group.id", groupId);
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("session.timeout.ms", "30000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.ByteArrayDeserializer");////
        props.put("auto.offset.reset","earliest");
        return props;
    }

    private static void insertIntoDb(List<ConsumerRecord<String, String>> buffer) {
        for(ConsumerRecord record : buffer){
            System.out.println(String.format(" inserted to DB. offset = %d, key = %s, value = %s", record.offset(), record.key(), record.value()));
        }
    }


}
