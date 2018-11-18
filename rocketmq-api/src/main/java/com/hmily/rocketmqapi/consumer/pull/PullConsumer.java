package com.hmily.rocketmqapi.consumer.pull;

import com.hmily.rocketmqapi.constants.Const;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.*;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.message.MessageQueue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 官方给的示例，但是感觉并不好用，建议使用 PullScheduleService 里面的写法
 */
@Slf4j
public class PullConsumer {

    //Map<key, value>  key为指定的队列，value为这个队列拉取数据的最后位置
    private static final Map<MessageQueue, Long> offseTable = new HashMap<MessageQueue, Long>();

    public static void main(String[] args) throws MQClientException {

        DefaultMQPullConsumer consumer = new DefaultMQPullConsumer(Producer.GROUP_NAME);
        consumer.setNamesrvAddr(Const.NAMESRV_ADDR_SINGLE);
        consumer.start();
       log.info("consumer start");
        //	从TopicTest这个主题去获取所有的队列（默认会有4个队列）
        Set<MessageQueue> mqs = consumer.fetchSubscribeMessageQueues(Producer.TOPIC);
        //	遍历每一个队列，进行拉取数据
        for (MessageQueue mq : mqs) {
            log.info("Consume from the queue: {}" , mq);

            SINGLE_MQ: while (true) {
                try {
                    //	从queue中获取数据，从什么位置开始拉取数据 单次对多拉取32条记录
                    PullResult pullResult = consumer.pullBlockIfNotFound(mq, null, getMessageQueueOffset(mq), 32);
                    log.info("pullResult: {}", pullResult);

                    putMessageQueueOffset(mq, pullResult.getNextBeginOffset());
                    switch (pullResult.getPullStatus()) {
                        case FOUND:
                            List<MessageExt> list = pullResult.getMsgFoundList();
                            for(MessageExt msg : list){
                                log.info("收到消息：{}", new String(msg.getBody()));
                            }
                            break;
                        case NO_MATCHED_MSG:
                            break;
                        case NO_NEW_MSG:
                            System.out.println("没有新的数据啦...");
                            break SINGLE_MQ;
                        case OFFSET_ILLEGAL:
                            break;
                        default:
                            break;
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        consumer.shutdown();
    }

    private static void putMessageQueueOffset(MessageQueue mq, long offset) {
        offseTable.put(mq, offset);
    }


    private static long getMessageQueueOffset(MessageQueue mq) {
        Long offset = offseTable.get(mq);
        if (offset != null)
            return offset;
        return 0;
    }

}
