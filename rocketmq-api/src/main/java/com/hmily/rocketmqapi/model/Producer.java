package com.hmily.rocketmqapi.model;

import com.hmily.rocketmqapi.constants.Const;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

@Slf4j
public class Producer {
    public static String GROUP_NAME = "test_model_producer_name";
    public static String TOPIC = "test_model_topic";

    public static void main(String[] args) throws MQClientException, InterruptedException {
        DefaultMQProducer producer = new DefaultMQProducer(GROUP_NAME);
        producer.setNamesrvAddr(Const.NAMESRV_ADDR_SINGLE);
        producer.start();
        for (int i = 0; i < 10; i++) {
            try {
//                String tag = (i % 3 ==0) ? "TagA": ((i % 3 ==1) ?  "TagB" : "TagC");
                String tag = (i % 2 ==0) ? "TagA": "TagB";
                Message msg = new Message(TOPIC,// topic
                        tag,// tag
                        ("信息内容" + i).getBytes()
                );
                SendResult sendResult = producer.send(msg);
                log.info("sendResult: {}, 信息内容: {}", sendResult, ("信息内容" + i));
            }
            catch (Exception e) {
                e.printStackTrace();
                Thread.sleep(1000);
            }
        }
        producer.shutdown();
    }
}
