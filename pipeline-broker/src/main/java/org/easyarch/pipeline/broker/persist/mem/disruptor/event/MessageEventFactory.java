package org.easyarch.pipeline.broker.persist.mem.disruptor.event;

import com.lmax.disruptor.EventFactory;

/**
 * Created by xingtianyu on 17-5-18
 * 下午5:10
 * description:
 */

public class MessageEventFactory implements EventFactory<MessageEvent> {

    @Override
    public MessageEvent newInstance() {
        return new MessageEvent();
    }
}
