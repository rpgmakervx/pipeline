package org.easyarch.pipeline.broker.manager;

import io.netty.util.AttributeKey;
import org.easyarch.pipeline.broker.manager.channel.ClientChannel;
import org.easyarch.pipeline.common.msg.head.Mode;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Created by xingtianyu on 17-6-11
 * 下午4:44
 * description:
 */

public class Registry {

    public static final Set<ClientChannel> REGISTRY = new CopyOnWriteArraySet<>();

    /**
     * queue和消费者的绑定
     */
    public static final Map<String,Set<ClientChannel>> QUEUE_CHANNEL = new ConcurrentHashMap<>();

    /**
     * topic和消费者的绑定
     */
    public static final Map<String,Set<ClientChannel>> TOPIC_CHANNEL = new ConcurrentHashMap<>();

    public static final AttributeKey<ClientChannel> NETTY_CHANNEL_KEY = AttributeKey.valueOf("netty.channel");

    public static Set<ClientChannel> getChannelByQueue(String queueId){
        return QUEUE_CHANNEL.get(queueId);
    }

    public static Set<ClientChannel> getChannelByTopic(String topicId){
        return TOPIC_CHANNEL.get(topicId);
    }

    public static boolean registChannel(String destId, ClientChannel channel,int mode){
        REGISTRY.add(channel);
        switch (mode){
            case Mode.QUEUE:
                if (QUEUE_CHANNEL.containsKey(destId)){
                    return QUEUE_CHANNEL.get(destId).add(channel);
                }else{
                    Set<ClientChannel> channelSet = new CopyOnWriteArraySet<>();
                    channelSet.add(channel);
                    QUEUE_CHANNEL.put(destId,channelSet);
                    return true;
                }
            case Mode.TOPIC:
                if (TOPIC_CHANNEL.containsKey(destId)){
                    return TOPIC_CHANNEL.get(destId).add(channel);
                }else{
                    Set<ClientChannel> channelSet = new CopyOnWriteArraySet<>();
                    channelSet.add(channel);
                    TOPIC_CHANNEL.put(destId,channelSet);
                    return true;
                }
            default:
                return false;
        }
    }

}
