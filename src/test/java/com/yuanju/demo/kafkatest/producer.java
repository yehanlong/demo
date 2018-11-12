package com.yuanju.demo.kafkatest;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;
import kafka.serializer.StringEncoder;

import java.util.Properties;

public class producer {
    public static void main(String[] args) throws Exception {
        String topic = "test"; // 定义要操作的主题
        Properties pro = new Properties(); // 定义相应的属性保存
        pro.setProperty("zookeeper.connect", "192.168.3.61:2181");
        pro.setProperty("metadata.broker.list", "192.168.3.61:9092");
        pro.setProperty("serializer.class", StringEncoder.class.getName());
        Producer<Integer, String> prod = new Producer<Integer, String>(new ProducerConfig(pro));
        prod.send(new KeyedMessage<Integer, String>(topic, "test"));

//        Properties props = new Properties();
//        props.put("bootstrap.servers", "localhost:9092");
//        props.put("acks", "all");
//        props.put("retries", 0);
//        props.put("batch.size", 16384);
//        props.put("linger.ms", 1);
//        props.put("buffer.memory", 33554432);
//        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
//        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
//
//        Producer<String, String> producer = new KafkaProducer<>(props);
//        for(int i = 0; i < 100; i++)
//            producer.send(new ProducerRecord<String, String>("my-topic", Integer.toString(i), Integer.toString(i)));
//
//        producer.close();
    }
}