package org.easyarch.pipeline.client;

import org.easyarch.pipeline.common.msg.head.Mode;

/**
 * Created by xingtianyu on 17-6-10
 * 上午11:21
 * description:
 */

public class DefaultMQSession implements MQSession {


    @Override
    public Consumer createConsumer(Destination destination) {
        return new Consumer(destination);
    }

    @Override
    public Producer createProducer(Destination destination) {
        return new Producer(destination);
    }

    @Override
    public Destination createQueue(String queueId) {
        Destination destination = new Destination(Mode.QUEUE);
        destination.setDestinationId(queueId);
        return destination;
    }

    @Override
    public Destination createTopic(String topicId) {
        Destination destination = new Destination(Mode.TOPIC);
        destination.setDestinationId(topicId);
        return destination;
    }

    @Override
    public Destination createFanout() {
        return new Destination(Mode.FANOUT);
    }
}
