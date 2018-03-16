package com.highland.producer.service;

import com.highland.producer.configuration.KafkaProducerProperties;
import com.highland.producer.entity.WorkUnit;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

@Service
public class WorkUnitDispatcher {
    @Autowired
    private KafkaTemplate<String, WorkUnit> kafkaTemplate;

    @Autowired
    private KafkaProducerProperties kafkaProducerProperties;

    private static final Logger LOGGER = LoggerFactory.getLogger(WorkUnitDispatcher.class);

    public boolean dispatch(WorkUnit workUnit) {
        try {
            SendResult<String, WorkUnit> sendResult = kafkaTemplate.sendDefault(
                    workUnit.getId(),
                    workUnit
            ).get();
            RecordMetadata recordMetadata = sendResult.getRecordMetadata();
            LOGGER.info("topic = {}, partition = {}, offset = {}, workUnit = {}",
                    recordMetadata.topic(), recordMetadata.partition(), recordMetadata.offset(), workUnit);
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
