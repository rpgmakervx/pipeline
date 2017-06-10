package org.easyarch.pipeline.common.handler.encoder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.easyarch.pipeline.common.codec.Serializer;
import org.easyarch.pipeline.common.codec.proto.ProtoBufSerializer;
import org.easyarch.pipeline.common.msg.Message;

/**
 * Created by xingtianyu on 17-5-21
 * 下午2:40
 * description:
 */

public class MessageEncoder extends MessageToByteEncoder<Message> {

    private Serializer<Message> serializer;

    public MessageEncoder(){
        serializer = new ProtoBufSerializer();
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, Message msg, ByteBuf out) throws Exception {
        byte[] data = serializer.serialize(msg);
        out.writeBytes(data);
        System.out.println("编码数据："+msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("编码异常");
        cause.printStackTrace();
    }
}
