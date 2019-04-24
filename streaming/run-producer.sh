#java -cp target/streaming-1.0-SNAPSHOT.jar mygroup.kafka.SimpleProducerWithPartitioner MyTopic localhost:32770 100
#java -cp target/streaming-1.0-SNAPSHOT.jar mygroup.kafka.SimpleProducer MyTopic localhost:32770 100
#java -cp target/streaming-1.0-SNAPSHOT.jar mygroup.kafka.SupplierProducer MySupplyTopic localhost:32770 100
java -cp target/streaming-1.0-SNAPSHOT.jar mygroup.kafka.ContinuousProducer MyTopic localhost:32770

