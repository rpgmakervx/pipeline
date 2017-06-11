package org.easyarch.pipeline.common.msg;

import com.alibaba.fastjson.JSON;
import org.easyarch.pipeline.common.msg.body.Body;
import org.easyarch.pipeline.common.msg.head.Header;

/**
 * Created by xingtianyu on 17-5-18
 * 下午5:20
 * description:
 */

public class Message {

    private Header header;

    private Body body;

    public Message(){
        this(new Header(),null);
    }

    public Message(Header header, Body body) {
        this.header = header;
        this.body = body;
    }

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    public boolean isEmpty(){
        return body == null||body.getData() == null;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
