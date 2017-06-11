package org.easyarch.pipeline.common.msg.body;

/**
 * Created by xingtianyu on 17-5-18
 * 下午5:20
 * description:
 */

public class Body<T> {
    private T data;

    public Body(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


}
