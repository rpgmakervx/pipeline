package org.easyarch.pipeline.broker.codec.json;

import com.alibaba.fastjson.JSON;
import org.easyarch.pipeline.broker.codec.Serializer;

import java.nio.charset.Charset;

/**
 * Description :
 * Created by xingtianyu on 16-12-3
 * 上午9:44
 */

public class JsonSerializer<T> implements Serializer<T> {

    @Override
    public byte[] serialize(T bean) {
        if (bean == null)
            return null;
        return JSON.toJSONString(bean).getBytes(Charset.forName("UTF-8"));
    }

    @Override
    public T deserialize(byte[] bytes, Class<T> cls) {
        if (bytes==null||bytes.length==0)
            return null;
        return JSON.parseObject(bytes,0,bytes.length, Charset.forName("UTF-8"),cls);
    }
}
