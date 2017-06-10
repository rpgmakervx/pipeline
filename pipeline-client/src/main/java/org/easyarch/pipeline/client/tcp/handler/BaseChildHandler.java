package org.easyarch.pipeline.client.tcp.handler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import org.easyarch.pipeline.client.listener.MessageListener;
import org.easyarch.pipeline.common.handler.decoder.MessageDecoder;
import org.easyarch.pipeline.common.handler.encoder.MessageEncoder;

/**
 * Description :
 * Created by xingtianyu on 17-2-23
 * 下午4:36
 * description:
 */

public class BaseChildHandler extends ChannelInitializer<SocketChannel> {

    private MessageListener listener;

    public BaseChildHandler(MessageListener listener) {
        this.listener = listener;
    }

    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new MessageEncoder());
        pipeline.addLast(new MessageDecoder());
        pipeline.addLast(new MessageClientHandler(listener));
    }
}
