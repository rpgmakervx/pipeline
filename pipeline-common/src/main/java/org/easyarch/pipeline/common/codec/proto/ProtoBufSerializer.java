package org.easyarch.pipeline.common.codec.proto;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtobufIOUtil;
import com.dyuproject.protostuff.Schema;
import com.dyuproject.protostuff.runtime.RuntimeEnv;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import org.easyarch.pipeline.common.codec.Serializer;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Description :
 * Created by xingtianyu on 16-11-27
 * 上午11:57
 */

public class ProtoBufSerializer<T> implements Serializer<T> {
    private static Map<Class<?>, Schema<?>> cachedSchema = new ConcurrentHashMap<Class<?>, Schema<?>>();
    private Schema<T> getSchema(Class<T> cls) {
        Schema<T> schema = (Schema<T>) cachedSchema.get(cls);
        if (schema == null) {
            schema = RuntimeSchema.createFrom(cls, RuntimeEnv.ID_STRATEGY);
            if (schema != null) {
                cachedSchema.put(cls, schema);
            }
        }
        return schema;
    }

    @Override
    public byte[] serialize(T object) {
        if (object == null){
            return null;
        }
        Class<T> clazz = (Class<T>) object.getClass();
        LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
        Schema<T> schema = getSchema(clazz);
        byte[] bytes = ProtobufIOUtil.toByteArray(object, schema, buffer);
        return bytes;
    }

    @Override
    public T deserialize(byte[] bytes, Class<T> clazz) {
        if (bytes==null||bytes.length==0) {
            return null;
        }
        try {
            T bean = clazz.newInstance();
            Schema<T> schema = getSchema(clazz);
            ProtobufIOUtil.mergeFrom(bytes, bean, schema);
            return bean;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
