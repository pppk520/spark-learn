package mygroup.kafka;

import java.util.*;
import org.apache.kafka.clients.producer.*;
import java.util.concurrent.TimeUnit;
import java.text.SimpleDateFormat;

public class ContinuousProducer {
    public static void main(String[] args) throws InterruptedException {
        String topic = args[0];
        String brokers = args[1];

        Properties props = new Properties();
        props.put("bootstrap.servers", brokers);
        props.put("client.id", "ProducerExample");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer <>(props);

        Random rg = new Random();
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr;

        String msg;

        try {
            while (true) {
                dateStr = sdf.format(cal.getTime());

                System.out.println("Data sent for " + dateStr);
                for (int i = 0; i < 100; i++) {
                    msg = String.format("%s, %s", dateStr, rg.nextInt(1000));
                    producer.send(new ProducerRecord<String, String>(topic, "Key" + i, msg)).get();

                    TimeUnit.MILLISECONDS.sleep(50);
                }

                // add one day for date change simulation
                cal.add(Calendar.DATE, 1);            
            }
        } catch (Exception ex) {
            System.out.println("Exception: {}" + ex.getMessage());
        } finally {
            producer.close();
        }
    }
}
