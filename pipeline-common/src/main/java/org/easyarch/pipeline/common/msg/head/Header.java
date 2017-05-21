package org.easyarch.pipeline.common.msg.head;

/**
 * Created by xingtianyu on 17-5-18
 * 下午5:20
 * description:
 */

public class Header {

    private Integer code;

    private Integer mode;

    private Integer act;

    private String topicId;

    private String queueId;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Integer getMode() {
        return mode;
    }

    public void setMode(Integer mode) {
        this.mode = mode;
    }

    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }

    public String getQueueId() {
        return queueId;
    }

    public void setQueueId(String queueId) {
        this.queueId = queueId;
    }

    public Integer getAct() {
        return act;
    }

    public void setAct(Integer act) {
        this.act = act;
    }
}
