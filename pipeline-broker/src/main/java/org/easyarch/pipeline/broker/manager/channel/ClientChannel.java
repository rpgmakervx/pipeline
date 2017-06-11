package org.easyarch.pipeline.broker.manager.channel;

import io.netty.channel.Channel;

/**
 * Created by xingtianyu on 17-6-11
 * 下午4:47
 * description:
 */

public class ClientChannel {

    private String id;

    private Channel channel;

    public ClientChannel(Channel channel) {
        this.channel = channel;
        this.id = channel.id().asLongText();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }
}
