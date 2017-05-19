package org.easyarch.pipeline.broker.persist.mem.disruptor.handler;

import com.lmax.disruptor.EventHandler;
import org.easyarch.pipeline.broker.persist.mem.disruptor.event.MessageEvent;

/**
 * Created by xingtianyu on 17-5-18
 * 下午5:32
 * description:
 */

public class MessageEventHandler implements EventHandler<MessageEvent> {

    @Override
    public void onEvent(MessageEvent messageEvent,long sequence, boolean endOfBatch) throws Exception {
        System.out.println("Event: " + messageEvent);
    }
}
