package org.easyarch.pipeline.broker.persist.mem.disruptor;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import org.easyarch.pipeline.broker.msg.Message;
import org.easyarch.pipeline.broker.persist.mem.disruptor.event.MessageEvent;
import org.easyarch.pipeline.broker.persist.mem.disruptor.event.MessageEventFactory;
import org.easyarch.pipeline.broker.persist.mem.disruptor.handler.MessageEventHandler;

import java.util.concurrent.Executors;

/**
 * Created by xingtianyu on 17-5-19
 * 下午5:22
 * description:
 */

public class MessageHolder {

    private static final int DEFAULT_SIZE = 1024 * 1024;

    private static Disruptor<MessageEvent> disruptor;
    static {
        disruptor = new Disruptor<>(new MessageEventFactory(),
                DEFAULT_SIZE, Executors.defaultThreadFactory(), ProducerType.SINGLE,
                new YieldingWaitStrategy());
        disruptor.handleEventsWith(new MessageEventHandler());
        disruptor.start();
    }
    public static Disruptor<MessageEvent> getDisruptor(){
        return disruptor;
    }

    public Message getMessage(){
        RingBuffer<MessageEvent> ringBuffer = disruptor.getRingBuffer();
        long sequence = ringBuffer.getCursor();
        MessageEvent messageEvent = ringBuffer.get(sequence);
        return messageEvent.getMessage();
    }
}
