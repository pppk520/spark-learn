package mygroup.kafka;

import java.util.*;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.*;

// from https://github.com/LearningJournal/ApacheKafkaTutorials/blob/master/ConsumerExample/RebalanceListner.java
public class RebalanceListener implements ConsumerRebalanceListener {
    private KafkaConsumer consumer;
    private Map<TopicPartition, OffsetAndMetadata> currentOffsets = new HashMap();

    public RebalanceListener(KafkaConsumer con){
        this.consumer = con;
    }

    public void addOffset(String topic, int partition, long offset) {
        currentOffsets.put(new TopicPartition(topic, partition), new OffsetAndMetadata(offset, "Commit"));
    } 

    public Map<TopicPartition, OffsetAndMetadata> getCurrentOffsets() {
        return currentOffsets;
    }

    public void onPartitionsAssigned(Collection<TopicPartition> partitions) {
        System.out.println("Following Partitions Assigned ....");

        for(TopicPartition partition: partitions) {
            System.out.println(partition.partition() + ",");
        }
    }

    public void onPartitionsRevoked(Collection<TopicPartition> partitions) {
        System.out.println("Following Partitions Revoked ....");

        for(TopicPartition partition : partitions) {
            System.out.println(partition.partition() + ",");
        }

        System.out.println("Following Partitions commited ...." );
        for(TopicPartition tp : currentOffsets.keySet()) {
            System.out.println(tp.partition());
        }

        consumer.commitSync(currentOffsets);
        currentOffsets.clear();
    }

}
