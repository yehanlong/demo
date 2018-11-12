package com.yuanju.demo.kafkatest;

import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;


public class consumer {
    public static void main(String[] args) throws Exception {
        String topic = "test"; // 定义要操作的主题
        Properties pro = new Properties(); // 定义相应的属性保存
        pro.setProperty("zookeeper.connect", "192.168.3.61:2181");
        pro.setProperty("metadata.broker.list", "kafka-server-alone:9092");
        pro.setProperty("group.id", "group1");
        ConsumerConnector consumer = Consumer.createJavaConsumerConnector(new   ConsumerConfig(pro));
        // 需要定义一个主题的映射的存储集合
        Map<String,Integer> topicMap = new HashMap<String,Integer>() ;
        topicMap.put(topic, 1) ; // 设置要读取数据的主题
        Map<String, List<KafkaStream<byte[], byte[]>>> messageStreams =
                consumer.createMessageStreams(topicMap) ;
        // 现在只有一个主题，所以此处只接收第一个主题的数据即可
        // 第一个主题
        KafkaStream<byte[], byte[]> stream = messageStreams.get(topic).get(0) ;
        ConsumerIterator<byte[], byte[]> iter = stream.iterator() ;
        while(iter.hasNext()) {
            String msg = new String(iter.next().message()) ;
            System.out.println("接收到消息：" + msg);
        }
    }
}