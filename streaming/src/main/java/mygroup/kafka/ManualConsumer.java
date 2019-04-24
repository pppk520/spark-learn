package mygroup.kafka;

import java.util.*;
import java.io.*;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.ConsumerRecord;

public class ManualConsumer {
   public static void main(String[] args)  throws Exception {
        String topic = args[0];
        String brokers = args[1];
        String group = args[2];

        Properties props = new Properties();
        props.put("bootstrap.servers", brokers);
        props.put("group.id", group);
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("enable.auto.commit", "false");  // disable auto commit

        KafkaConsumer<String, String> consumer = null;

        try {
            consumer = new KafkaConsumer<>(props);
            consumer.subscribe(Arrays.asList(topic));

            while (true){
                ConsumerRecords<String, String> records = consumer.poll(100);
                for (ConsumerRecord<String, String> record : records){
                    System.out.println("received record: " + record.value());
                }
                consumer.commitAsync();
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            consumer.commitSync();
            consumer.close();
        }
    }
}
