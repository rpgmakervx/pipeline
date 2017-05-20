package org.easyarch.pipeline.broker.persist.mem;

import org.easyarch.pipeline.broker.msg.Body;
import org.easyarch.pipeline.broker.msg.Header;
import org.easyarch.pipeline.broker.msg.Message;
import org.easyarch.pipeline.broker.persist.mem.redis.RedisQueue;

/**
 * Created by xingtianyu on 17-5-20
 * 下午9:32
 * description:
 */

public class TestQueue {

    public static void main(String[] args) throws InterruptedException {
        MQueue<Message> queue = new RedisQueue("queue1");
        for (int index = 0;index<40;index++){
            queue.push(new Message(new Header(),new Body("hello"+index)));
        }
        while (true){
            Message message = queue.fetch();
            if (message == null){
                break;
            }
            System.out.println("message:"+message);
            Thread.sleep(500);
        }
    }

}
