package kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * Created by noodles on 16/9/30 上午11:56.
 */
public class KafkaConsumerDemo4 {


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
            consumer.subscribe(Arrays.asList("elog-portal"));
            while (true) {
                ConsumerRecords<String, byte[]> records = consumer.poll(5000);
                System.out.println("-----------" + records.count());
                for (ConsumerRecord<String, byte[]> record : records){
                    System.out.println("topic=" + record.topic() + "," + new String(record.value()));;

                }
            }

        }
    }

    public static void main(String[] args) {

        final Properties props2 = initProps("test44");

        KafkaRunner kafkaRunner2 = new KafkaRunner(props2);

        kafkaRunner2.setName("thread-test2");

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
        props.put("auto.offset.reset", "earliest");
        return props;
    }

    private static void insertIntoDb(List<ConsumerRecord<String, String>> buffer) {
        for(ConsumerRecord record : buffer){
            System.out.println(String.format(" inserted to DB. offset = %d, key = %s, value = %s", record.offset(), record.key(), record.value()));
        }
    }


}
