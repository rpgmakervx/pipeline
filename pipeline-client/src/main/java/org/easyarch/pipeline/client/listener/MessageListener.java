package org.easyarch.pipeline.client.listener;

import org.easyarch.pipeline.common.msg.Message;

/**
 * Created by code4j on 17-5-21
 * 下午8:46
 * description:
 */

public interface MessageListener {

    void onMessage(Message message);

}
