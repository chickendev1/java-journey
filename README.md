# java-journey
# Enviroment
1. docker
2. gradle
3. Mongodb
4. Redis
5. Spring boot
# Run application
- Before running this project. You must access to ./producer and run docker-compose up -d from command line
- Setting brew package on mac os and using brew cask install redis | zookeeper | kafka | mongodb etc for installing directly.
- brew services restart "services name" to start an service on mac os. For example: bew services restart kafka | zookeeper | redis | mongodb | mysql
- brew services list to check a service if it is runing
# Error
- when publishing a message to kafka. Consummer side can not get message with following error
org.apache.kafka.common.errors.SerializationException: Error deserializing key/value for partition workunits-0 at offset 1. If needed, please seek past the record to continue consumption.
Caused by: java.lang.IllegalStateException: No type information in headers and no default type provided
	at org.springframework.util.Assert.state(Assert.java:73) ~[spring-core-5.0.4.RELEASE.jar:5.0.4.RELEASE]
	at org.springframework.kafka.support.serializer.JsonDeserializer.deserialize(JsonDeserializer.java:193) ~[spring-kafka-2.1.4.RELEASE.jar:2.1.4.RELEASE]
	at org.apache.kafka.clients.consumer.internals.Fetcher.parseRecord(Fetcher.java:923) ~[kafka-clients-1.0.0.jar:na]
	at org.apache.kafka.clients.consumer.internals.Fetcher.access$2600(Fetcher.java:93) ~[kafka-clients-1.0.0.jar:na]
	at org.apache.kafka.clients.consumer.internals.Fetcher$PartitionRecords.fetchRecords(Fetcher.java:1100) ~[kafka-clients-1.0.0.jar:na]
	at org.apache.kafka.clients.consumer.internals.Fetcher$PartitionRecords.access$1200(Fetcher.java:949) ~[kafka-clients-1.0.0.jar:na]
	at org.apache.kafka.clients.consumer.internals.Fetcher.fetchRecords(Fetcher.java:570) ~[kafka-clients-1.0.0.jar:na]
	at org.apache.kafka.clients.consumer.internals.Fetcher.fetchedRecords(Fetcher.java:531) ~[kafka-clients-1.0.0.jar:na]
	at org.apache.kafka.clients.consumer.KafkaConsumer.pollOnce(KafkaConsumer.java:1146) ~[kafka-clients-1.0.0.jar:na]
	at org.apache.kafka.clients.consumer.KafkaConsumer.poll(KafkaConsumer.java:1103) ~[kafka-clients-1.0.0.jar:na]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.run(KafkaMessageListenerContainer.java:667) ~[spring-kafka-2.1.4.RELEASE.jar:2.1.4.RELEASE]
	at java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:511) [na:1.8.0_144]
	at java.util.concurrent.FutureTask.run(FutureTask.java:266) [na:1.8.0_144]
	at java.lang.Thread.run(Thread.java:748) [na:1.8.0_144]

2018-03-17 10:42:40.989 ERROR 27866 --- [ntainer#0-0-C-1] essageListenerContainer$ListenerConsumer : Container exception
- This error will be verified in the next commit. In case of fetching a message from kafka, but It can not success when consumer continute to request to kafka in my project. I think that It may be root cause for stoping kafka.

# Reference
[1]. https://github.com/bijukunjummen/sample-spring-kafka-producer-consumer <br/>
[2]. Neha Narkhede, Gwen shapira, Todd Palino, "Kafka the definitive guiede real-time data and stream processing at scale", Oreilly, 2017