package org.easyarch.pipeline.client.tcp;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.easyarch.pipeline.client.listener.MessageListener;
import org.easyarch.pipeline.client.tcp.handler.BaseChildHandler;
import org.easyarch.pipeline.common.msg.Message;

/**
 * Created by xingtianyu on 17-5-21
 * 下午3:45
 * description:
 */

public class Connector {

    private ChannelFuture future;
    private Bootstrap b;
    private String ip;
    private int port;

    public Connector(String ip,int port){
        this.ip = ip;
        this.port = port;
    }

    private void connect(MessageListener listener) {
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            b = new Bootstrap();
            b.group(workerGroup)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 1000)
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .handler(new BaseChildHandler(listener));
            future = b.connect(ip, port).sync();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void send(Message message){
        Channel channel = future.channel();
        channel.writeAndFlush(message);
    }

}
