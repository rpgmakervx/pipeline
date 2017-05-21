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
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("失去连接");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Channel channel = ctx.channel();
        Message message = (Message) msg;
        Header header = message.getHeader();
        int act = header.getAct();
        String id = header.getQueueId();
        switch (act){
            case Action.PUBLISH:
                publish(message);
                break;
            case Action.CONSUME:
                Message consumeMessage = consume(id);
                consumeMessage.getHeader().setAct(Action.POLL);
                if (consumeMessage == null){
                    return ;
                }
                channel.writeAndFlush(consumeMessage);
                break;
        }

    }

    public void publish(Message message){
        Header header = message.getHeader();
        int mode = header.getMode();
        String queueId = header.getQueueId();
        MQueue queue = Queues.getQueue(queueId);
        if (queue == null){
            queue = new RedisQueue(queueId);
        }
        queue.push(message);
    }

    public Message consume(String id){
        MQueue<Message> queue = Queues.getQueue(id);
        if (queue == null){
            return null;
        }
        return queue.fetch();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }
}
