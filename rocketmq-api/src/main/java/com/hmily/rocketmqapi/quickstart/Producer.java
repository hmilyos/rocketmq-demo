package com.hmily.rocketmqapi.quickstart;

import com.hmily.rocketmqapi.constants.Const;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.util.List;


@Slf4j
public class Producer {

    public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException, MQBrokerException {
        test4();
    }

    //    模拟消息发送到指定队列
    public static void test4() throws MQClientException, RemotingException, InterruptedException, MQBrokerException{
        DefaultMQProducer producer = new DefaultMQProducer("test_quick_producer_name");

        producer.setNamesrvAddr(Const.NAMESRV_ADDR_SINGLE);

        producer.start();
        for (int i = 0; i < 5; i++) {
            //	1.	创建消息
            Message message = new Message("test_quick_topic",    //	主题
                    "TagA", //	标签
                    "key" + i,    // 	用户自定义的key ,唯一的标识
                    ("Hello RocketMQ" + i).getBytes());    //	消息内容实体（byte[]）
//            同步发送消息
            SendResult sr = producer.send(message, new MessageQueueSelector() {
                @Override
                public MessageQueue select(List<MessageQueue> list, Message message, Object arg) {
//                    外面指定的 queueId 就是 这里面的 arg
                    Integer queueNumber = (Integer) arg;
                    return list.get(queueNumber);
                }
            }, 2); // 2 代表指定发送到 queueId == 2 的队列中去，MessageQueueSelector 里面的 arg 就是这个数值
//            只有 0 - 3 这 4 个 queueId 可选择

            log.info("SendResult: {}", sr);
        }
        producer.shutdown();
    }

//    模拟延迟消息
    public static void test3() throws MQClientException, RemotingException, InterruptedException, MQBrokerException{
        DefaultMQProducer producer = new DefaultMQProducer("test_quick_producer_name");

        producer.setNamesrvAddr(Const.NAMESRV_ADDR_SINGLE);

        producer.start();
        for (int i = 0; i < 2; i++) {
            //	1.	创建消息
            Message message = new Message("test_quick_topic",    //	主题
                    "TagA", //	标签
                    "key" + i,    // 	用户自定义的key ,唯一的标识
                    ("Hello RocketMQ" + i).getBytes());    //	消息内容实体（byte[]）
            if (i == 1){
//            设置延迟级别， 1：对应延迟级别里面的第一个，2：对应第二个级别
                message.setDelayTimeLevel(3);
            }
//            同步发送消息
            SendResult sr = producer.send(message);
            log.info("SendResult: {}", sr);
        }
        producer.shutdown();
    }

    public static void test1() throws MQClientException, RemotingException, InterruptedException, MQBrokerException{
        DefaultMQProducer producer = new DefaultMQProducer("test_quick_producer_name");

        producer.setNamesrvAddr(Const.NAMESRV_ADDR_SINGLE);

        producer.start();
        for (int i = 0; i < 5; i++) {
            //	1.	创建消息
            Message message = new Message("test_quick_topic",    //	主题
                    "TagA", //	标签
                    "key" + i,    // 	用户自定义的key ,唯一的标识
                    ("Hello RocketMQ" + i).getBytes());    //	消息内容实体（byte[]）
//            设置延迟级别， 1：对应延迟级别里面的第一个，2：对应第二个级别
            message.setDelayTimeLevel(1);

//            同步发送消息
            SendResult sr = producer.send(message);
            log.info("SendResult: {}", sr);

        }
        producer.shutdown();
    }


    public static void test2() throws MQClientException, RemotingException, InterruptedException, MQBrokerException{
        DefaultMQProducer producer = new DefaultMQProducer("test_quick_producer_name");

        producer.setNamesrvAddr(Const.NAMESRV_ADDR_SINGLE);

        producer.start();
        for (int i = 0; i < 5; i++) {
            //	1.	创建消息
            Message message = new Message("test_quick_topic",    //	主题
                    "TagA", //	标签
                    "key" + i,    // 	用户自定义的key ,唯一的标识
                    ("Hello RocketMQ" + i).getBytes());    //	消息内容实体（byte[]）
//            异步发送消息
            producer.send(message, new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {
                    log.info("sendResult: {}", sendResult);
                }

                @Override
                public void onException(Throwable throwable) {
                    log.error("消息发送失败: {}", throwable);
                }
            });
        }
//        不能 shutdown ，要不然异步消息发送失败
//        producer.shutdown();
    }
}
