package org.easyarch.pipeline.broker.persist.mem.jdk;

import org.easyarch.pipeline.broker.msg.Message;
import org.easyarch.pipeline.broker.persist.mem.MQueue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by xingtianyu on 17-5-20
 * 下午4:48
 * description:
 */

public class JavaQueue implements MQueue<Message> {

    private String queueName;

    private List<Message> queue;

    public JavaQueue(String queueName){
        this.queueName = queueName;
        this.queue = new CopyOnWriteArrayList<>();
    }

    @Override
    public Message get() {
        return queue.get(0);
    }

    @Override
    public void push(Message element) {
        queue.add(element);
    }

    @Override
    public void pushAll(Message... elements) {
        queue.addAll(Arrays.asList(elements));
    }

    @Override
    public Message fetch() {
        return queue.remove(0);
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
