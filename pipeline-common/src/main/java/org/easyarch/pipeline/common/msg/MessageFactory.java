package org.easyarch.pipeline.common.msg;

import org.easyarch.pipeline.common.msg.body.Body;
import org.easyarch.pipeline.common.msg.head.Action;
import org.easyarch.pipeline.common.msg.head.Header;

/**
 * Created by xingtianyu on 17-6-10
 * 下午10:40
 * description:
 */

public class MessageFactory {

    public static Message createPollMessage(String destId,int mode){
        Header header = new Header();
        header.setAct(Action.POLL);
        header.setDestId(destId);
        header.setCode(200);
        header.setMode(mode);
        return new Message(header,null);
    }

    public static Message createConsumerPollMessage(String destId,int mode){
        Header header = new Header();
        header.setAct(Action.CON_POLL);
        header.setDestId(destId);
        header.setCode(200);
        header.setMode(mode);
        return new Message(header,null);
    }

    public static Message createMessage(Object message,String destId){
        Header header = new Header();
        header.setAct(Action.BODY);
        header.setDestId(destId);
        return new Message(header,new Body(message));
    }

    public static Message createPubMessage(Object message,String destId){
        Header header = new Header();
        header.setAct(Action.PUBLISH);
        header.setDestId(destId);
        return new Message(header,new Body(message));

    }

}
