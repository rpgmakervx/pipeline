package org.easyarch.pipeline.broker.server.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.easyarch.pipeline.broker.persist.mem.MQueue;
import org.easyarch.pipeline.broker.persist.mem.Queues;
import org.easyarch.pipeline.broker.persist.mem.redis.RedisQueue;
import org.easyarch.pipeline.common.msg.head.Action;
import org.easyarch.pipeline.common.msg.head.Header;
import org.easyarch.pipeline.common.msg.Message;

/**
 * Created by xingtianyu on 17-5-21
 * 下午4:19
 * description:
 */

public class MQHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("建立连接");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("失去连接");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Channel channel = ctx.channel();
        Message message = (Message) msg;
        System.out.println("服务端收到一条消息："+message);
        Header header = message.getHeader();
        int act = header.getAct();
        String id = header.getDestId();
        switch (act){
            case Action.PUBLISH:
                message.getHeader().setAct(Action.BODY);
                publish(message);
                break;
            case Action.CONSUME:
                Message storageMessage = getMessage(id);
                System.out.println("消费模式得到的消息："+storageMessage);
                Header head = new Header();
                head.setAct(Action.POLL);
                if (storageMessage == null){
                    head.setAct(Action.NONE);
                    return ;
                }
                head.setDestId(id);
                Message pollMessage = new Message(head,null);
                channel.writeAndFlush(pollMessage);
                System.out.println("poll 写回的消息："+pollMessage);
                break;
            case Action.CON_POLL:
                Message consumeMessage = consume(id);
                System.out.println("CON_POLL action:"+consumeMessage.getHeader().getAct());
                channel.writeAndFlush(consumeMessage);
                break;
        }

    }

    public void publish(Message message){
        Header header = message.getHeader();
        int mode = header.getMode();
        String queueId = header.getDestId();
        MQueue queue = Queues.getQueue(queueId);
        if (queue == null){
            queue = new RedisQueue(queueId);
        }
        queue.push(message);
        Queues.addQueue(queueId,queue);
    }

    public Message consume(String id){
        MQueue<Message> queue = Queues.getQueue(id);
        if (queue == null){
            return null;
        }
        return queue.fetch();
    }

    public Message getMessage(String id){
        MQueue<Message> queue = Queues.getQueue(id);
        if (queue == null){
            return null;
        }
        return queue.get();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }
}
