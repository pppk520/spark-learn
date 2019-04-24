package mygroup.kafka;

import java.util.*;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.*;

public class ListenerConsumer {
    public static void main(String[] args) throws Exception {
        String topic = args[0];
        String brokers = args[1];
        String group = args[2];

        Properties props = new Properties();
        props.put("bootstrap.servers", brokers);
        props.put("group.id", group);
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("enable.auto.commit", "false");  // disable auto commit

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        RebalanceListener rebalanceListener = new RebalanceListener(consumer);

        consumer.subscribe(Arrays.asList(topic),rebalanceListener);

        try {
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(100);

                for (ConsumerRecord<String, String> record : records) { 
                    System.out.println("Topic:"+ record.topic() +" Partition:" + record.partition() + " Offset:" + record.offset() + " Value:" + record.value());
                    // update latest processed offset
                    // when rebalance happens, our listener will be called and do the commits
                    rebalanceListener.addOffset(record.topic(), record.partition(),record.offset());
                }
            }
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            consumer.close();
        }
    }
}
