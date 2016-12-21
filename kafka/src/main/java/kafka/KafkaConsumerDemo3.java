package kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.PartitionInfo;

import java.util.*;

/**
 * Created by noodles on 16/9/30 上午11:56.
 */
public class KafkaConsumerDemo3 {

    public static void main(String[] args) {
        final Properties properties = initProps("testGroup");

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties);

        consumer.subscribe(Arrays.asList("q1"));

        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(100);
            for (ConsumerRecord<String, String> record : records) {
                System.out.println(record.value());
            }

            final Set<String> subscription = consumer.subscription();
            printIterator(subscription.iterator());

            Scanner scanner = new Scanner(System.in);
            final String line = scanner.nextLine();
            if (line != null && !line.equals("")) {
                final String[] split = line.split(",");
                consumer.subscribe(Arrays.asList(split));
            }
        }
    }

    private static Properties initProps(String groupId) {
        Properties props = new Properties();
        props.put("bootstrap.servers", KafkaDef.Kafka_url);
        props.put("group.id", groupId);
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("session.timeout.ms", "30000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");////
        props.put("auto.offset.reset", "earliest");
        return props;
    }

    private static void printIterator(Iterator iterator) {
        StringBuilder sb = new StringBuilder("subscribeList:");
        while (iterator.hasNext()) {
            sb.append(iterator.next().toString()).append(",");
        }
        sb.deleteCharAt(sb.length() -1);
        System.out.println(sb);
    }
}
