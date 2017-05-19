package org.easyarch.pipeline.broker.persist.mem.disruptor.event;

import org.easyarch.pipeline.broker.msg.Message;

/**
 * Created by xingtianyu on 17-5-18
 * 下午4:59
 * description:
 */

public class MessageEvent{

    private Message message;

    public void setMessage(Message message){
        this.message = message;
    }

    public Message getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "MessageEvent{" +
                "message=" + message +
                '}';
    }
}
