package org.easyarch.pipeline.client;

import org.easyarch.pipeline.client.config.MQConfig;
import org.easyarch.pipeline.client.listener.PubMessageListener;
import org.easyarch.pipeline.common.msg.Message;
import org.easyarch.pipeline.common.msg.body.Body;
import org.easyarch.pipeline.common.msg.head.Action;
import org.easyarch.pipeline.common.msg.head.Header;
import org.easyarch.pipeline.common.msg.head.Mode;

/**
 * Created by xingtianyu on 17-6-10
 * 下午1:37
 * description:
 */

public class ProduceMain {

    public static void main(String[] args) {
        MQConfig.config().setIp("127.0.0.1").setPort(10000);
        MQSession session = new DefaultMQSession();
        Destination destination = session.createQueue("xingtianyu");
        Producer producer = session.createProducer(destination);
        Header header = new Header();
        header.setMode(Mode.QUEUE);
        header.setAct(Action.PUBLISH);
        header.setDestId("xingtianyu");
        producer.send(new Message(header, new Body("hello world")), new PubMessageListener() {
            @Override
            public void onAck(Header header) {
                System.out.println("ack header:"+header);
            }

            @Override
            public void onMessage(Message message) {
                System.out.println("get message:"+message);
            }
        });
    }
}
