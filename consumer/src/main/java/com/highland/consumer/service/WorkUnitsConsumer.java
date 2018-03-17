package com.highland.consumer.service;

import com.highland.consumer.entity.WorkUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

@Service
public class WorkUnitsConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(WorkUnitsConsumer.class);

    @KafkaListener(topics = "workunits")
    public void onReceiving(WorkUnit workUnit, @Header(KafkaHeaders.OFFSET) int offset,
                            @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
                            @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
        LOGGER.info("Processing topic = {}, partition = {}, offset = {}, workUnit = {}",
                topic, partition, offset, workUnit);
        System.out.println(
                "Processing topic: "
                + topic
                + " partition: "
                + partition
                + " offset "
                + offset
                + " workUnit "
                + workUnit
        );
    }
}
