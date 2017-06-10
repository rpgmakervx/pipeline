package org.easyarch.pipeline.common.handler.decoder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.easyarch.pipeline.common.codec.Serializer;
import org.easyarch.pipeline.common.codec.proto.ProtoBufSerializer;
import org.easyarch.pipeline.common.kits.ByteKits;
import org.easyarch.pipeline.common.msg.Message;

import java.util.List;

/**
 * Created by xingtianyu on 17-5-21
 * 下午2:52
 * description:
 */

public class MessageDecoder extends ByteToMessageDecoder {

    private Serializer<Message> serializer;

    public MessageDecoder(){
        serializer = new ProtoBufSerializer<>();
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        if (in.readableBytes() <= 0){
            return;
        }
        byte[] data = ByteKits.readByteBuf(in);
        Message message = serializer.deserialize(data,Message.class);
        System.out.println("解码器得到的消息："+message);
        out.add(message);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("解码异常");
        cause.printStackTrace();
    }
}
