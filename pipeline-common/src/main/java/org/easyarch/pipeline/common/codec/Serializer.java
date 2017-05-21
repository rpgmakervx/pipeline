package org.easyarch.pipeline.common.codec;

/**
 * Description :
 * Created by xingtianyu on 16-11-30
 * 上午9:29
 */

public interface Serializer<T> {

    public byte[] serialize(T bean);

    public T deserialize(byte[] bytes, Class<T> cls);
}
