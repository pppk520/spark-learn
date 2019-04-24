package mygroup.kafka;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class SimpleProducerWithPartitioner {
    public static void main(String[] args) {
        String topic = args[0];
        String brokers = args[1];
        int eventNum = Integer.valueOf(args[2]);

        Properties props = new Properties();
        props.put("bootstrap.servers", brokers);
        props.put("client.id", "ProducerExample");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        props.put("partitioner.class", "mygroup.kafka.SimplePartitioner");
        props.put("my.config.theName", "PTT");

        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(props);

        for (int i = 0; i < eventNum/2; i++) {
            try {
                producer.send(new ProducerRecord<>(topic, "GeneralEvent" + i, "Value" + i));
                producer.send(new ProducerRecord<>(topic, "PTT", "Value" + i));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        if (eventNum % 2 == 1) {
            producer.send(new ProducerRecord<>(topic, "PTT", "Last Value"));
        }

        producer.close();
        System.out.println("Completed.");   
    }

}
