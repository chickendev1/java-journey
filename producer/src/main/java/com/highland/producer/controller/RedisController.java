package com.highland.producer.controller;

import com.highland.producer.entity.RedisVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "redis")
@CacheConfig(cacheNames = "redisCache")
public class RedisController {
    @Autowired
    private MongoTemplate mongoTemplate;

    @PostMapping(value = "/set")
    public RedisVO insert(@RequestBody RedisVO redisVO) {
        mongoTemplate.insert(redisVO, "rediscollection");
        return redisVO;
    }

    @GetMapping(value = "/get/{id}")
    @Cacheable(keyGenerator = "keyGenerator")
    public RedisVO get(@PathVariable Integer id) {
        return mongoTemplate.findById(id, RedisVO.class, "rediscollection");
    }

    @PutMapping(value = "/update")
    @CachePut(keyGenerator = "keyGenerator")
    public RedisVO update(@RequestBody RedisVO redisVO) {
        mongoTemplate.save(redisVO, "rediscollection");
        return redisVO;
    }

    @DeleteMapping(value = "/del/{id}")
    @CacheEvict(keyGenerator = "keyGenerator")
    public String delete(@PathVariable Integer id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("keyId").is(id));
        mongoTemplate.findAndRemove(query, RedisVO.class, "rediscollection");
        return "delete with id: " + id;
    }

    public static RedisController valueOf(Object o) {
        return (RedisController) o;
    }
}
