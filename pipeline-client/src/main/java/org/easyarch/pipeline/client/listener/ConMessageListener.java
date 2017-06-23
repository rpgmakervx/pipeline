package org.easyarch.pipeline.client.listener;

import org.easyarch.pipeline.common.msg.Message;

/**
 * Created by xingtianyu on 17-5-21
 * 下午9:30
 * description:
 */

public interface ConMessageListener extends MessageListener {
    void onMessage(Message message);
}
