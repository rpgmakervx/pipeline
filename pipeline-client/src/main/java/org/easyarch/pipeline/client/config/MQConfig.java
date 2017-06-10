package org.easyarch.pipeline.client.config;

/**
 * Created by xingtianyu on 17-6-10
 * 上午11:13
 * description:
 */

public class MQConfig {

    private static MQConfig config = new MQConfig();

    private String ip;

    private int port;

    private MQConfig(){

    }

    public static MQConfig config(){
        if (config == null){
            config = new MQConfig();
        }
        return config;
    }


    public String getIp() {
        return ip;
    }

    public MQConfig setIp(String ip) {
        this.ip = ip;
        return this;
    }

    public int getPort() {
        return port;
    }

    public MQConfig setPort(int port) {
        this.port = port;
        return this;
    }
}
