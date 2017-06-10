package org.easyarch.pipeline.client;

import org.easyarch.pipeline.client.config.MQConfig;
import org.easyarch.pipeline.client.listener.ConMessageListener;
import org.easyarch.pipeline.common.msg.Message;
import org.easyarch.pipeline.common.msg.head.Header;

/**
 * Created by xingtianyu on 17-6-10
 * 上午12:08
 * description:
 */

public class ConsumerMain {

    public static void main(String[] args) {
        MQConfig.config().setIp("127.0.0.1").setPort(10000);
        MQSession session = new DefaultMQSession();
        Destination destination = session.createQueue("xingtianyu");
        Consumer consumer = session.createConsumer(destination);
        consumer.recieve(new ConMessageListener() {
            @Override
            public void onPoll(Header header) {
                System.out.println("poll header:"+header);
            }

            @Override
            public void onMessage(Message message) {
                System.out.println("recieve message:"+message);

            }
        });
    }
}
