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

    private String destId;

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

    public String getDestId() {
        return destId;
    }

    public void setDestId(String destId) {
        this.destId = destId;
    }

    public Integer getAct() {
        return act;
    }

    public void setAct(Integer act) {
        this.act = act;
    }

    @Override
    public String toString() {
        return "Header{" +
                "code=" + code +
                ", mode=" + mode +
                ", act=" + act +
                ", destId='" + destId + '\'' +
                '}';
    }
}
