package mygroup.kafka;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class SimpleProducer {
    public static void main(String[] args) {
        String topic = args[0];
        String brokers = args[1];
        int eventNum = Integer.valueOf(args[2]);

        Properties props = new Properties();
        props.put("bootstrap.servers", brokers);
        props.put("client.id", "ProducerExample");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(props);

        for (int i = 0; i < eventNum; i++) {
            try {
                String key = "Event " + i;
                String value = "Value " + i;

                ProducerRecord<String, String> record = new ProducerRecord<>(topic, key, value);
                producer.send(record);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        producer.close();
        System.out.println("Completed.");   
    }

}
