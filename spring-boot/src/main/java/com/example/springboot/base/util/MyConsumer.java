package com.example.springboot.base.util;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import javax.sound.midi.Soundbank;
import javax.xml.bind.SchemaOutputResolver;

/**
 * @program: kafka-study
 * @description:
 * @author: liuB
 * @create: 2021-03-26 09:03
 **/
@Component
public class MyConsumer {

    @KafkaListener(topics = "mytopic",groupId = "testGroup")
    public void listen(ConsumerRecord<String,String> record){
        String value = record.value();
        System.out.println("value:"+value);
        System.out.println("record:"+record);
    }
}