package org.easyarch.pipeline.broker.persist.mem.redis;

import org.easyarch.pipeline.broker.persist.mem.MQueue;
import org.easyarch.pipeline.common.codec.Serializer;
import org.easyarch.pipeline.common.codec.proto.ProtoBufSerializer;
import org.easyarch.pipeline.common.msg.Message;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xingtianyu on 17-5-20
 * 下午10:24
 * description:
 */

public class RedisQueue implements MQueue<Message> {

    private RedisClient.Lists lists = RedisKits.lists();

    private Serializer<Message> serializer;

    private String queueName;

    public RedisQueue(String queueName) {
        this.queueName = queueName;
        serializer = new ProtoBufSerializer();
    }

    @Override
    public Message get() {
        byte[] data = lists.lindex(queueName.getBytes(), (int) length() - 1);
        return serializer.deserialize(data,Message.class);
    }

    @Override
    public void push(Message element) {
        byte[] data = serializer.serialize(element);
        lists.lpush(queueName.getBytes(),data);
    }

    @Override
    public void pushAll(Message... elements) {
        for (Message message:elements){
            push(message);
        }
    }

    @Override
    public Message fetch() {
        Message message = get();
        if (message == null){
            return null;
        }
        lists.lrem(queueName.getBytes(),1,serializer.serialize(message));
        System.out.println("fetch一个元素,queueName:"+queueName+":"+message);
        return message;
    }

    @Override
    public List<Message> top(int count) {
        List<Message> list = new ArrayList<>();
        for (int index=0;index<count;index++){
            list.add(fetch());
        }
        return list;
    }

    private long length(){
        return lists.llen(queueName);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RedisQueue)) return false;

        RedisQueue that = (RedisQueue) o;

        return queueName != null ? queueName.equals(that.queueName) : that.queueName == null;
    }

    @Override
    public int hashCode() {
        return queueName != null ? queueName.hashCode() : 0;
    }
}
