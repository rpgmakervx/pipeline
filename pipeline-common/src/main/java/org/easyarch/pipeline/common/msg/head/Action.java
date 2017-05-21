package org.easyarch.pipeline.common.msg.head;

/**
 * Created by xingtianyu on 17-5-21
 * 下午5:18
 * description:
 */

public interface Action {
    /**
     * 客户端生产数据推送到broker(服务端判断)
     */
    int PUBLISH = 0;
    /**
     * 客户端消费数据从broker拉取(服务端判断)
     */
    int CONSUME = 1;
    /**
     * 服务端通知客户端消费数据(客户端判断)
     */
    int POLL = 2;
    /**
     * 收到生产信息后的ack(客户端判断)
     */
    int PUB_ACK = 3;
    /**
     * 收到消费完成后的ack(服务端判断)
     */
    int CON_ACK = 4;
    /**
     * 生产者生产完的消息进入结束状态，act会被设置为BODY,表示该消息带消息体.
     * 客户端收到消息带fin的表示收到消息(客户端判断)
     */
    int BODY = 5;

}
