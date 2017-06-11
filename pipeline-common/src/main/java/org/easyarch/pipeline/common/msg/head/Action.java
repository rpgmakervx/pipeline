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
     * 客户端消费者准备从broker拉取(服务端判断)
     */
    int CONSUME = 1;
    /**
     * 消费者注册
     */
    int REGIST = 2;

    /**
     * 服务端通知客户端消费数据(客户端判断)
     */
    int POLL = 3;
    /**
     * 客户端开始向broker拉取数据
     */
    int CON_POLL = 4;
    /**
     * 收到生产信息后的ack(客户端判断)
     */
    int PUB_ACK = 5;
    /**
     * 收到消费完成后的ack(服务端判断)
     */
    int CON_ACK = 6;
    /**
     * 消费者跟broker建立tcp链接
     */
    int CON_SYN = 7;
    /**
     * 生产者生产完的消息进入结束状态，act会被设置为BODY,表示该消息带消息体.
     * 客户端收到消息带fin的表示收到消息(客户端判断)
     */
    int BODY = 8;

    /**
     * 消费者获取的消息不存在
     */
    int NOT_EXISTS = 9;
    /**
     * 不作任何处理
     */
    int NONE = 10;
}
