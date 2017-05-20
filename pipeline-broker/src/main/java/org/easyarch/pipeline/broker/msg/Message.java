package org.easyarch.pipeline.broker.msg;

import org.easyarch.netpet.web.mvc.entity.Json;

/**
 * Created by xingtianyu on 17-5-18
 * 下午5:20
 * description:
 */

public class Message {

    private Header header;

    private Body body;

    public Message(){
        this(new Header(),new Body(null));
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

    @Override
    public String toString() {
        return Json.stringify(this);
    }
}
