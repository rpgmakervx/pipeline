package org.easyarch.pipeline.client.http.asynclient.handler.callback;


import org.easyarch.pipeline.client.http.asynclient.http.response.AsyncHttpResponse;

/**
 * Created by xingtianyu on 17-4-8
 * 下午3:35
 * description:
 */

abstract public class AsyncResponseHandlerAdapter implements AsyncResponseHandler {

    @Override
    public void onFinally(AsyncHttpResponse response) throws Exception {

    }
}
