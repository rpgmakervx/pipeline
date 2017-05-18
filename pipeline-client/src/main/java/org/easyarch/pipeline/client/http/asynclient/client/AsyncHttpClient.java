package org.easyarch.pipeline.client.http.asynclient.client;


import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.http.*;
import org.easyarch.pipeline.client.http.asynclient.handler.callback.AsyncResponseHandler;
import org.easyarch.pipeline.client.http.asynclient.http.entity.FileParam;
import org.easyarch.pipeline.client.http.asynclient.http.entity.Json;
import org.easyarch.pipeline.client.http.asynclient.http.entity.RequestEntity;
import org.easyarch.pipeline.client.http.protocol.HttpHeaderValue;
import org.easyarch.pipeline.client.kits.ByteKits;

import java.net.InetSocketAddress;
import java.util.List;
import java.util.Map;

/**
 * Created by xingtianyu on 17-4-5
 * 下午4:50
 * description:
 */

public class AsyncHttpClient {

    private Launcher launcher;

    public AsyncHttpClient(String host) throws Exception {
        launcher = new Launcher(host);
    }
    public AsyncHttpClient(String protocel,InetSocketAddress host) throws Exception {
        launcher = new Launcher(protocel,host);
    }

    /**
     * get请求
     * @param uri 请求路径
     * @param handler 处理器
     */
    public void get(String uri, AsyncResponseHandler handler) throws Exception {
        RequestEntity entity = new RequestEntity(uri, HttpMethod.GET,null,(ByteBuf) null);
        launcher.execute(entity,handler);
    }

    public void get(String uri, Map<String,Object> headers, AsyncResponseHandler handler) throws Exception {
        HttpHeaders header = new DefaultHttpHeaders();
        for (Map.Entry<String,Object> entry:headers.entrySet()){
            header.add(entry.getKey(),entry.getValue());
        }
        RequestEntity entity = new RequestEntity(uri, HttpMethod.GET,header,(ByteBuf)null);
        launcher.execute(entity,handler);
    }

    public void postEntity(String uri,Map<String,String> params,AsyncResponseHandler handler) throws Exception {
        requestEntity(uri, HttpMethod.POST,params,handler);
    }
    public void postEntity(String uri,Map<String,String> params,Map<String,String> headers,AsyncResponseHandler handler) throws Exception {
        requestEntity(uri, HttpMethod.POST,params,headers,handler);
    }

    public void postJson(String uri, Json json, AsyncResponseHandler handler) throws Exception {
        requestJson(uri, HttpMethod.POST,json,handler);
    }
    public void postJson(String uri, Json json, Map<String,String> headers, AsyncResponseHandler handler) throws Exception {
        requestJson(uri, HttpMethod.POST,json,headers,handler);
    }

    public void putEntity(String uri,Map<String,String> params,Map<String,String> headers,AsyncResponseHandler handler) throws Exception {
        requestEntity(uri, HttpMethod.PUT,params,headers,handler);
    }
    public void putEntity(String uri,Map<String,String> params,AsyncResponseHandler handler) throws Exception {
        requestEntity(uri, HttpMethod.PUT,params,handler);
    }

    /**
     * 带Json参数的put请求
     * @param uri 请求路径
     * @param json 请求json参数
     * @param handler 处理器
     */
    public void putJson(String uri, Json json, AsyncResponseHandler handler) throws Exception {
        requestJson(uri, HttpMethod.PUT,json,handler);
    }

    /**
     * 带header和Json参数的put请求
     * @param uri 请求路径
     * @param json 请求json参数
     * @param headers 请求headers
     * @param handler 处理器
     */
    public void putJson(String uri, Json json, Map<String,String> headers, AsyncResponseHandler handler) throws Exception {
        requestJson(uri, HttpMethod.PUT,json,headers,handler);
    }

    /**
     * 带header的delete请求
     * @param uri 请求路径
     * @param headers 请求headers
     * @param handler 处理器
     */
    public void deleteEntity(String uri,Map<String,String> headers,AsyncResponseHandler handler) throws Exception {
        requestEntity(uri, HttpMethod.DELETE,null,headers,handler);
    }

    /**
     * delete请求
     * @param uri 请求路径
     * @param handler 处理器
     */
    public void deleteEntity(String uri,AsyncResponseHandler handler) throws Exception {
        requestEntity(uri, HttpMethod.DELETE,null,handler);
    }

    /**
     * 单文件上传
     * @param uri 请求路径
     * @param param 文件参数
     * @param handler 处理器
     */
    public void fileUpload(String uri, FileParam param, AsyncResponseHandler handler) throws Exception {
        HttpHeaders httpHeaders = new DefaultHttpHeaders();
        RequestEntity entity = new RequestEntity(uri, HttpMethod.POST,httpHeaders,param);
        launcher.execute(entity,handler);
    }

    /**
     * 带header的单文件上传
     * @param uri 请求路径
     * @param headers 请求headers
     * @param param 文件参数
     * @param handler 处理器
     */
    public void fileUpload(String uri, Map<String,String> headers , FileParam param, AsyncResponseHandler handler) throws Exception {
        HttpHeaders httpHeaders = new DefaultHttpHeaders();
        for (Map.Entry<String,String> entry:headers.entrySet()){
            httpHeaders.add(entry.getKey(),entry.getValue());
        }
        RequestEntity entity = new RequestEntity(uri, HttpMethod.POST,httpHeaders,param);
        launcher.execute(entity,handler);
    }

    /**
     * 多文件上传请求
     * @param uri 请求路径
     * @param params 多个文件参数
     * @param handler 处理器
     */
    public void multipartFileUpload(String uri, List<FileParam> params, AsyncResponseHandler handler) throws Exception {
        HttpHeaders httpHeaders = new DefaultHttpHeaders();
        httpHeaders.add(HttpHeaderNames.CONTENT_TYPE, HttpHeaderValues.MULTIPART_FORM_DATA);
        RequestEntity entity = new RequestEntity(uri, HttpMethod.POST,httpHeaders,params);
        launcher.execute(entity,handler);
    }

    /**
     * 带header的多文件上传请求
     * @param uri 请求路径
     * @param headers 请求headers
     * @param params 多个文件参数
     * @param handler 处理器
     */
    public void multipartFileUpload(String uri, Map<String,String> headers , List<FileParam> params, AsyncResponseHandler handler) throws Exception {
        HttpHeaders httpHeaders = new DefaultHttpHeaders();
        httpHeaders.add(HttpHeaderNames.CONTENT_TYPE, HttpHeaderValues.MULTIPART_FORM_DATA);
        for (Map.Entry<String,String> entry:headers.entrySet()){
            httpHeaders.add(entry.getKey(),entry.getValue());
        }
        RequestEntity entity = new RequestEntity(uri, HttpMethod.POST,httpHeaders,params);
        launcher.execute(entity,handler);
    }

    /**
     * 用来直接转发底层netty提供的请求
     * @param request 请求体
     * @param handler 处理器
     */
    public void send(FullHttpRequest request, AsyncResponseHandler handler) throws Exception {
        launcher.execute(request,handler);
    }

    private void requestEntity(String uri, HttpMethod method, Map<String,String> params, AsyncResponseHandler handler) throws Exception {
        ByteBuf buf = ByteKits.toByteBuf(Json.stringify(params));
        HttpHeaders headers = new DefaultHttpHeaders();
        headers.add(HttpHeaderNames.CONTENT_TYPE, HttpHeaderValues.APPLICATION_X_WWW_FORM_URLENCODED);
        RequestEntity entity = new RequestEntity(uri, method,headers,buf);
        launcher.execute(entity,handler);
    }

    public void requestEntity(String uri, HttpMethod method, Map<String,String> params, Map<String,String> headers, AsyncResponseHandler handler) throws Exception {
        ByteBuf buf = ByteKits.toByteBuf(Json.stringify(params));
        HttpHeaders httpHeaders = new DefaultHttpHeaders();
        httpHeaders.add(HttpHeaderNames.CONTENT_TYPE, HttpHeaderValues.APPLICATION_X_WWW_FORM_URLENCODED);
        for (Map.Entry<String,String> entry:headers.entrySet()){
            httpHeaders.add(entry.getKey(),entry.getValue());
        }
        RequestEntity entity = new RequestEntity(uri, method,httpHeaders,buf);
        launcher.execute(entity,handler);
    }

    private void requestJson(String uri, HttpMethod method, Json json, AsyncResponseHandler handler) throws Exception {
        ByteBuf buf = ByteKits.toByteBuf(Json.stringify(json));
        HttpHeaders headers = new DefaultHttpHeaders();
        headers.add(HttpHeaderNames.CONTENT_TYPE, HttpHeaderValue.APPLICATION_JSON);
        RequestEntity entity = new RequestEntity(uri, method,headers,buf);
        launcher.execute(entity,handler);
    }

    private void requestJson(String uri, HttpMethod method, Json json, Map<String,String> headers, AsyncResponseHandler handler) throws Exception {
        ByteBuf buf = ByteKits.toByteBuf(Json.stringify(json));
        HttpHeaders httpHeaders = new DefaultHttpHeaders();
        httpHeaders.add(HttpHeaderNames.CONTENT_TYPE, HttpHeaderValue.APPLICATION_JSON);
        for (Map.Entry<String,String> entry:headers.entrySet()){
            httpHeaders.add(entry.getKey(),entry.getValue());
        }
        RequestEntity entity = new RequestEntity(uri, method,httpHeaders,buf);
        launcher.execute(entity,handler);
    }

    public void close(){
        launcher.close();
    }

}
