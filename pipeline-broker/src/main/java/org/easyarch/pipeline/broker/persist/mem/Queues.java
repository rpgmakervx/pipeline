package org.easyarch.pipeline.broker.persist.mem;

import org.easyarch.pipeline.broker.persist.mem.disruptor.event.MessageEvent;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by xingtianyu on 17-5-20
 * 下午3:28
 * description:
 */

public class Queues {

    private static Map<String,MQueue<MessageEvent>> queues = new ConcurrentHashMap();

    public void addQueue(String id,MQueue<MessageEvent> queue){
        queues.put(id,queue);
    }

    public MQueue<MessageEvent> delQueue(String id){
        return queues.remove(id);
    }

    public MQueue<MessageEvent> getQueue(String id){
        return queues.get(id);
    }

}
