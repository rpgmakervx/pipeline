package org.easyarch.pipeline.broker.persist.mem;


import org.easyarch.pipeline.common.msg.Message;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by xingtianyu on 17-5-20
 * 下午3:28
 * description:
 */

public class Queues {

    private static Map<String,MQueue<Message>> queues = new ConcurrentHashMap();

    public static void addQueue(String id,MQueue<Message> queue){
        queues.put(id,queue);
    }

    public static MQueue<Message> delQueue(String id){
        return queues.remove(id);
    }

    public static MQueue<Message> getQueue(String id){
        return queues.get(id);
    }

}
