package org.easyarch.pipeline.client.http.asynclient.handler.callback;


import org.easyarch.pipeline.client.http.asynclient.http.response.AsyncHttpResponse;

/**
 * Created by xingtianyu on 17-4-4
 * 下午8:27
 * description:
 */

public interface AsyncResponseHandler {

    public void onSuccess(AsyncHttpResponse response) throws Exception;

    public void onFailure(int statusCode, Object message) throws Exception;

    public void onFinally(AsyncHttpResponse response) throws Exception;
}
