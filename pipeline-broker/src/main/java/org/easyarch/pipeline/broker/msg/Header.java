package org.easyarch.pipeline.broker.msg;

/**
 * Created by xingtianyu on 17-5-18
 * 下午5:20
 * description:
 */

public class Header {

    private Integer code;

    private Integer mode;

    private Long topicId;

    private Long queueId;

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

    public Long getTopicId() {
        return topicId;
    }

    public void setTopicId(Long topicId) {
        this.topicId = topicId;
    }

    public Long getQueueId() {
        return queueId;
    }

    public void setQueueId(Long queueId) {
        this.queueId = queueId;
    }
}
