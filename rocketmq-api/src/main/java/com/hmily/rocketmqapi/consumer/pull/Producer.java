package com.hmily.rocketmqapi.consumer.pull;

import com.hmily.rocketmqapi.constants.Const;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;


@Slf4j
public class Producer {
    public static final String GROUP_NAME = "test_pull_producer_name";
    public static final String TOPIC = "test_pull_topic";

    public static void main(String[] args) throws MQClientException, InterruptedException {
        DefaultMQProducer producer = new DefaultMQProducer(GROUP_NAME);
        producer.setNamesrvAddr(Const.NAMESRV_ADDR_SINGLE);
        producer.start();

        for (int i = 0; i < 10; i++) {
            try {
                Message msg = new Message(TOPIC,// topic
                        "TagA",// tag
                        ("Hello RocketMQ " + i).getBytes()// body
                );

                SendResult sendResult = producer.send(msg);
                log.info("sendResult: {}", sendResult);
                Thread.sleep(1000);
            }
            catch (Exception e) {
                e.printStackTrace();
                Thread.sleep(3000);
            }
        }

        producer.shutdown();
    }
}
