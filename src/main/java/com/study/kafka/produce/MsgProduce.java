package com.study.kafka.produce;

import com.alibaba.fastjson.JSON;
import com.study.kafka.produce.domain.Order;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @program: kafka-study
 * @description: 生产端
 * @author: liuB
 * @create: 2021-03-25 17:41
 **/
public class MsgProduce {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"node1:9092,node1:9093,node1:9094");
        props.put(ProducerConfig.ACKS_CONFIG,"1");
        props.put(ProducerConfig.RETRIES_CONFIG,3);
        props.put(ProducerConfig.RETRY_BACKOFF_MS_CONFIG,300);
        props.put(ProducerConfig.RECEIVE_BUFFER_CONFIG,33554432);
        props.put(ProducerConfig.BATCH_SIZE_CONFIG,16384);
        props.put(ProducerConfig.LINGER_MS_CONFIG,100);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());
        Producer<String,String> producer = new KafkaProducer<String, String>(props);

        int msgNum=10;
        CountDownLatch countDownLatch = new CountDownLatch(msgNum);
        for(int i=0;i<msgNum;i++){
            Order order = new Order(i,i+100,1000.00);
            ProducerRecord<String,String> producerRecord = new ProducerRecord<String, String>("order-topic",
                    0,order.getOrderId()+"", JSON.toJSONString(order));
            RecordMetadata metadata = producer.send(producerRecord).get();
            System.out.println("同步发送结果： topic-"+metadata.topic()+"|partition-"+metadata.partition()+" |offset-"
            +metadata.offset());
            countDownLatch.countDown();
        }

        countDownLatch.await(5, TimeUnit.SECONDS);
        producer.close();
    }
}