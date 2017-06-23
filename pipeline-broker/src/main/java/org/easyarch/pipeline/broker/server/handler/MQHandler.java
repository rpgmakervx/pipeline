package org.easyarch.pipeline.broker.server.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.easyarch.pipeline.broker.persist.mem.MQueue;
import org.easyarch.pipeline.broker.persist.mem.Queues;
import org.easyarch.pipeline.broker.persist.mem.redis.RedisQueue;
import org.easyarch.pipeline.common.msg.Message;
import org.easyarch.pipeline.common.msg.head.Action;
import org.easyarch.pipeline.common.msg.head.Header;

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
                Message consumeMessage = consume(id);
                channel.writeAndFlush(consumeMessage);
                break;
            case Action.REGIST:

                break;
        }

    }

    public void publish(Message message){
        Header header = message.getHeader();
        String queueId = header.getDestId();
        MQueue queue = Queues.getQueue(queueId);
        if (queue == null){
            queue = new RedisQueue(queueId);
        }
        queue.push(message);
        System.out.println("持久化一个消息："+message);
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
