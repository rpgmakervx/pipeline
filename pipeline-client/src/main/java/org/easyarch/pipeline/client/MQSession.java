package org.easyarch.pipeline.client;

/**
 * Created by xingtianyu on 17-6-10
 * 上午12:10
 * description:
 */

public interface MQSession {


    Consumer createConsumer(Destination destination);

    Producer createProducer(Destination destination);

    Destination createQueue(String queueId);

    Destination createTopic(String topicId);

    Destination createFanout();
}
