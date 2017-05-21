package org.easyarch.pipeline.client.listener;

import org.easyarch.pipeline.common.msg.head.Header;

/**
 * Created by xingtianyu on 17-5-21
 * 下午9:42
 * description:
 */

public interface PubMessageListener extends MessageListener {
    void onAck(Header header);
}
