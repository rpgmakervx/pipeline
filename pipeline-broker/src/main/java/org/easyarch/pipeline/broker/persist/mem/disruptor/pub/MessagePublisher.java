package org.easyarch.pipeline.broker.persist.mem.disruptor.pub;

import com.lmax.disruptor.RingBuffer;
import org.easyarch.pipeline.broker.msg.Message;
import org.easyarch.pipeline.broker.persist.mem.disruptor.MessageHolder;
import org.easyarch.pipeline.broker.persist.mem.disruptor.event.MessageEvent;

/**
 * Created by xingtianyu on 17-5-19
 * 下午4:15
 * description:
 */

public class MessagePublisher {

    public void publish(Message message){
        RingBuffer<MessageEvent> ringBuffer = MessageHolder.getDisruptor().getRingBuffer();
        long sequence = ringBuffer.next();
        try {
            MessageEvent messageEvent = ringBuffer.get(sequence);
            messageEvent.setMessage(message);
        }finally {
            ringBuffer.publish(sequence);
        }
    }
}
