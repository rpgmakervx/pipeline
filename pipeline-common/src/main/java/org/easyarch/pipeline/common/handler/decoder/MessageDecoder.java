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
        byte[] data = ByteKits.toByteArray(in);
        Message message = serializer.deserialize(data,Message.class);
        out.add(message);
    }
}
