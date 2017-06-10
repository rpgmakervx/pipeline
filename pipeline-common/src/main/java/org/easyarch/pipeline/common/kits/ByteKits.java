package org.easyarch.pipeline.common.kits;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

/**
 * Created by xingtianyu on 17-3-9
 * 下午12:02
 * description:对ByteBuf的所有操作一律先拷贝
 */

public class ByteKits {

    public static ByteBuf toByteBuf(byte[] bytes){
        if (bytes == null||bytes.length == 0){
            return Unpooled.EMPTY_BUFFER;
        }
        ByteBuf buf = Unpooled.wrappedBuffer(bytes);
        return buf;
    }
    public static ByteBuf toByteBuf(String content){
        return toByteBuf(content.getBytes());
    }

    /**
     * 拷贝字节后再读取，原来的bytebuf不会受影响
     * @param buf
     * @return
     */
    public static byte[] toByteArray(ByteBuf buf){
        if (buf == null){
            return new byte[0];
        }
        ByteBuf copyBuf = buf.copy();
        if (copyBuf == null||copyBuf.readableBytes() == 0){
            return new byte[0];
        }
        int readable = copyBuf.readableBytes();
        byte[] bytes = new byte[readable];
        copyBuf.readBytes(bytes);
        return bytes;
    }

    public static String toString(final ByteBuf buf){
        if (buf == null){
            return "";
        }
        ByteBuf copyBuf = buf.copy();
        return new String(toByteArray(copyBuf));
    }

    /**
     * 从bytebuf中读取字节
     * @param buf
     * @return
     */
    public static byte[] readByteBuf(ByteBuf buf){
        if (buf == null){
            return new byte[0];
        }
        if (buf == null||buf.readableBytes() == 0){
            return new byte[0];
        }
        int readable = buf.readableBytes();
        byte[] bytes = new byte[readable];
        buf.readBytes(bytes);
        return bytes;
    }
}
