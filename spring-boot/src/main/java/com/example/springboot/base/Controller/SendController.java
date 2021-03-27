package com.example.springboot.base.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: kafka-study
 * @description:
 * @author: liuB
 * @create: 2021-03-26 08:58
 **/
@RestController
@RequestMapping("/send")
public class SendController {

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    public void send(String msg){
        kafkaTemplate.send("mytopic",0,"key","msg:"+msg);
    }
}