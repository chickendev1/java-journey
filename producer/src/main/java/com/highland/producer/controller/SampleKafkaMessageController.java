package com.highland.producer.controller;

import com.highland.producer.entity.WorkUnit;
import com.highland.producer.service.WorkUnitDispatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "redis/v1")
public class SampleKafkaMessageController {

    @Autowired
    private WorkUnitDispatcher workUnitDispatcher;

    @GetMapping("/generateWork")
    public boolean sendMessage(WorkUnit workUnit) {
        return this.workUnitDispatcher.dispatch(workUnit);
    }
}
