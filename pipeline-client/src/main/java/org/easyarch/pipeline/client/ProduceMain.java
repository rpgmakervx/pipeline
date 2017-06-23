package org.easyarch.pipeline.client;

import org.easyarch.pipeline.client.config.MQConfig;
import org.easyarch.pipeline.client.listener.PubMessageListener;
import org.easyarch.pipeline.common.msg.head.Header;

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
        producer.send("hello world1", new PubMessageListener() {
            @Override
            public void onAck(Header header) {
                System.out.println("ack header:"+header);
            }
        });
//        producer.close();
    }
}
