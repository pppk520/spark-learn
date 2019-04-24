package mygroup.kafka;

import java.util.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import org.apache.kafka.clients.producer.*;

public class SupplierProducer {
    public static void main(String[] args) throws Exception {
        String topic = args[0];
        String brokers = args[1];
        int eventNum = Integer.valueOf(args[2]);

        Properties props = new Properties();
        props.put("bootstrap.servers", brokers);
        props.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "mygroup.kafka.SupplierSerializer");

        Producer<String, Supplier> producer = new KafkaProducer <>(props);

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        for (int i = 0; i < eventNum; i++) {
            Supplier sp = new Supplier(i, "SupplyEvent No." + i, new Date());
            // sync
            producer.send(new ProducerRecord<String, Supplier>(topic, "SUP", sp)).get();
        }

        System.out.println("SupplierProducer Completed.");
        producer.close();
    }

}
