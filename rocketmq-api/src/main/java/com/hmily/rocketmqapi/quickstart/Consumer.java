package com.hmily.rocketmqapi.quickstart;

import com.hmily.rocketmqapi.constants.Const;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.util.List;

@Slf4j
public class Consumer {

    public static void main(String[] args) throws MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("test_quick_consumer_name");

        consumer.setNamesrvAddr(Const.NAMESRV_ADDR_SINGLE);

        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);

        consumer.subscribe("test_quick_topic", "*");
        consumer.registerMessageListener(new MessageListenerConcurrently() {

            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                MessageExt me = msgs.get(0);
                try {
                    String topic = me.getTopic();
                    String tags = me.getTags();
                    String keys = me.getKeys();
                    String msgBody = new String(me.getBody(), RemotingHelper.DEFAULT_CHARSET);
//                    if (keys.equals("key1")) {
//                        log.error("模拟 消息消费失败..");
//                        int a = 1 / 0;
//                    }
                    log.info("topic: {}, tags: {}, keys: {}, msgBody: {}", topic, tags, keys, msgBody);
                } catch (Exception e) {
                    e.printStackTrace();
                    int recousumeTimes = me.getReconsumeTimes();
                    log.error("recousumeTimes: {}", recousumeTimes);
                    if (recousumeTimes == 3) {
                        //		设置最多重试 3 次， 记录日志....
                        //  	做补偿处理
                        log.info("重试 3 次了，请进行补偿");
                        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                    }
                    return ConsumeConcurrentlyStatus.RECONSUME_LATER;
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        consumer.start();
        log.info("consumer start...");
    }
}
