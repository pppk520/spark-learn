package mygroup.kafka;

import java.util.*;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.ConsumerRecord;

public class SupplierConsumer {
    public static void main(String[] args) throws Exception {
        String topic = args[0];
        String brokers = args[1];
        String group = args[2];

        Properties props = new Properties();
        props.put("bootstrap.servers", brokers);
        props.put("group.id", group);
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "mygroup.kafka.SupplierDeserializer");

        KafkaConsumer<String, Supplier> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList(topic));

        while (true) {
            ConsumerRecords<String, Supplier> records = consumer.poll(100);
            for (ConsumerRecord<String, Supplier> record : records) {
                System.out.println("Supplier id = " + String.valueOf(record.value().getId()) + 
                                   " Supplier Name = " + record.value().getName() + 
                                   " Supplier Start Date = " + record.value().getStartDate().toString());
            }
        }
    }
}
 
