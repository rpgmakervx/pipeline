package org.easyarch.pipeline.broker.server.handler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import org.easyarch.pipeline.common.handler.decoder.MessageDecoder;
import org.easyarch.pipeline.common.handler.encoder.MessageEncoder;

/**
 * Description :
 * Created by xingtianyu on 17-2-23
 * 下午4:36
 * description:
 */

public class BaseChildHandler extends ChannelInitializer<SocketChannel> {


    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new MessageDecoder());
        pipeline.addLast(new MessageEncoder());
    }
}
