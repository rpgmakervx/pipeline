package org.easyarch.pipeline.client.http.asynclient.http.entity;

import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpMethod;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xingtianyu on 17-4-5
 * 下午7:38
 * description:
 */

public class RequestEntity {
    private String path;

    private HttpMethod method;

    private HttpHeaders headers;

    private ByteBuf buf;

    private List<FileParam> files = new ArrayList<>();

    public RequestEntity(String path, HttpMethod method, HttpHeaders headers, ByteBuf buf) {
        this.path = path;
        this.method = method;
        this.headers = headers;
        this.buf = buf;
    }
    public RequestEntity(String path, HttpMethod method, HttpHeaders headers, FileParam param) {
        this.path = path;
        this.method = method;
        this.headers = headers;
        this.files.add(param);
    }
    public RequestEntity(String path, HttpMethod method, HttpHeaders headers, List<FileParam> params) {
        this.path = path;
        this.method = method;
        this.headers = headers;
        this.files.addAll(params);
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public HttpMethod getMethod() {
        return method;
    }

    public void setMethod(HttpMethod method) {
        this.method = method;
    }

    public HttpHeaders getHeaders() {
        return headers;
    }

    public void setHeaders(HttpHeaders headers) {
        this.headers = headers;
    }

    public ByteBuf getBuf() {
        return buf;
    }

    public void setBuf(ByteBuf buf) {
        this.buf = buf;
    }

    public void addFile(FileParam param){
        files.add(param);
    }
    public void addFiles(List<FileParam> params){
        files.addAll(params);
    }

    public List<FileParam> getFiles(){
        return files;
    }
}
