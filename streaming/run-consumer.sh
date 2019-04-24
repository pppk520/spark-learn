#java -cp target/streaming-1.0-SNAPSHOT.jar mygroup.kafka.SupplierConsumer MySupplyTopic localhost:32770 MyConsumerGroup
#java -cp target/streaming-1.0-SNAPSHOT.jar mygroup.kafka.ManualConsumer MyTopic localhost:32770 MyConsumerGroup
java -cp target/streaming-1.0-SNAPSHOT.jar mygroup.kafka.ListenerConsumer MyTopic localhost:32770 MyConsumerGroup
