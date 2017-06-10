package org.easyarch.pipeline.client;

/**
 * Created by xingtianyu on 17-6-10
 * 下午8:49
 * description:
 */

public class Destination {

    private int mode;

    private String destinationId;

    public Destination(int mode) {
        this.mode = mode;
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public String getDestinationId() {
        return destinationId;
    }

    public void setDestinationId(String destinationId) {
        this.destinationId = destinationId;
    }
}
