package com.study.kafka.produce.domain;

import lombok.Data;

/**
 * @program: kafka-study
 * @description:
 * @author: liuB
 * @create: 2021-03-25 17:54
 **/
@Data
public class Order {
    private int orderId;
    private int seq;
    private double fee;

    public Order(int orderId,int seq,double fee){
        this.orderId = orderId;
        this.seq = seq;
        this.fee = fee;
    }
}