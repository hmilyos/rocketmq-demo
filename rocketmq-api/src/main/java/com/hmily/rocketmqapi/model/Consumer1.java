package com.hmily.rocketmqapi.model;

import com.hmily.rocketmqapi.constants.Const;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;

import java.util.List;

@Slf4j
public class Consumer1 {
    public Consumer1(){
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("test_model_producer_name1");
        consumer.setNamesrvAddr(Const.NAMESRV_ADDR_SINGLE);
        try {
//            consumer.subscribe(Producer.TOPIC, "TagA || TagC");
            consumer.subscribe(Producer.TOPIC, "TagA");
            consumer.setMessageModel(MessageModel.CLUSTERING); // 集群模式

//            consumer.subscribe(Producer.TOPIC, "*");
//            consumer.setMessageModel(MessageModel.BROADCASTING); // 广播模式

            consumer.registerMessageListener(new Listener());
            consumer.start();
        } catch (MQClientException e) {
            e.printStackTrace();
        }
    }

    class Listener implements MessageListenerConcurrently {
        public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
            try {
                for(MessageExt msg : msgs){
                    String topic = msg.getTopic();
                    String msgBody = new String(msg.getBody(),"utf-8");
                    String tags = msg.getTags();
//                    if ("TagA".equals(tags)){
                        log.info("收到消息  topic : {},  tags : {},  msg: {}", topic, tags, msgBody);
//                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                return ConsumeConcurrentlyStatus.RECONSUME_LATER;
            }
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        }

    }

    public static void main(String[] args) {
        Consumer1 c1 = new Consumer1();
        log.info("c1 start..");

    }
}
