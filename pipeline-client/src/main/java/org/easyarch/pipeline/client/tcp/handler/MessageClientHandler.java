package org.easyarch.pipeline.client.tcp.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.easyarch.pipeline.client.listener.ConMessageListener;
import org.easyarch.pipeline.client.listener.MessageListener;
import org.easyarch.pipeline.client.listener.PubMessageListener;
import org.easyarch.pipeline.common.msg.Message;
import org.easyarch.pipeline.common.msg.head.Action;
import org.easyarch.pipeline.common.msg.head.Header;

/**
 * Created by xingtianyu on 17-5-21
 * 下午8:45
 * description:
 */

public class MessageClientHandler extends ChannelInboundHandlerAdapter {

    private MessageListener listener;

    public MessageClientHandler(MessageListener listener) {
        this.listener = listener;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Channel channel = ctx.channel();
        Message message = (Message) msg;
        Header header = message.getHeader();
        int act = header.getAct();
        System.out.println("客户端收到消息："+message);
        switch (act){
            case Action.PUB_ACK:
                ((PubMessageListener)listener).onAck(header);
                break;
            case Action.BODY:
                ((ConMessageListener)listener).onMessage(message);
                break;
            case Action.REGISTED:
                break;
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }
}
