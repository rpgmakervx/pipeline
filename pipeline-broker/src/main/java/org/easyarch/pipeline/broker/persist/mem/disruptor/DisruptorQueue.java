package org.easyarch.pipeline.broker.persist.mem.disruptor;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import org.easyarch.pipeline.broker.msg.Message;
import org.easyarch.pipeline.broker.persist.mem.MQueue;
import org.easyarch.pipeline.broker.persist.mem.disruptor.event.MessageEvent;
import org.easyarch.pipeline.broker.persist.mem.disruptor.event.MessageEventFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

/**
 * Created by xingtianyu on 17-5-20
 * 下午6:44
 * description:
 */

public class DisruptorQueue implements MQueue<Message> {
    private static final int DEFAULT_SIZE = 2<<4;
    private Disruptor<MessageEvent> disruptor;

    private RingBuffer<MessageEvent> ringBuffer;

    public DisruptorQueue(){
        this(DEFAULT_SIZE);
    }

    public DisruptorQueue(int factor) {
        this.disruptor = new Disruptor<>(new MessageEventFactory(), 2<<factor,
                Executors.defaultThreadFactory(), ProducerType.SINGLE,
                new YieldingWaitStrategy());
//        this.disruptor.handleEventsWith(new MessageEventHandler());
        this.disruptor.start();
        this.ringBuffer = disruptor.getRingBuffer();
    }

    @Override
    public Message get() {
        MessageEvent messageEvent = ringBuffer.get(0);
        return messageEvent.getMessage();
    }

    @Override
    public void push(Message element) {
        long sequence = ringBuffer.next();
        MessageEvent messageEvent = ringBuffer.get(sequence);
        try {
            messageEvent.setMessage(element);
        }finally {
            ringBuffer.publish(sequence);
        }
    }

    @Override
    public void pushAll(Message... elements) {
        for (Message message:elements){
            push(message);
        }
    }

    @Override
    public Message fetch() {
        MessageEvent messageEvent = ringBuffer.get(ringBuffer.next() - 1);
        return messageEvent.getMessage();
    }

    @Override
    public List<Message> top(int count) {
        List<Message> list = new ArrayList<>(count);
        for (int index=0;index<count;index++){
            list.add(fetch());
        }
        return list;
    }
}
