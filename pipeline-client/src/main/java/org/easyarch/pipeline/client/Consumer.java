package org.easyarch.pipeline.client;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.easyarch.pipeline.client.config.MQConfig;
import org.easyarch.pipeline.client.listener.ConMessageListener;
import org.easyarch.pipeline.common.msg.Message;
import org.easyarch.pipeline.common.msg.MessageFactory;

import javax.jms.*;


/**
 * Created by xingtianyu on 17-5-20
 * 下午5:48
 * description:
 */

public class Consumer {

    private Connector connector;

    private Destination destination;

    private MQConfig config = MQConfig.config();

    public Consumer(Destination destination){
        this.connector = new Connector(config.getIp(),config.getPort());
        this.destination = destination;
    }

    public void recieve(ConMessageListener listener){
        connector.connect(listener);
        Message message = MessageFactory
                .createConsumeMessage(destination.getDestinationId(), destination.getMode());
        connector.send(message);
        System.out.println("准备接受消息");
    }

    public void close(){
        connector.close();
    }

    public static void main(String[] args) {
        // ConnectionFactory ：连接工厂，JMS 用它创建连接
        ConnectionFactory connectionFactory;
        // Connection ：JMS 客户端到JMS Provider 的连接
        Connection connection = null;

        // Session： 一个发送或接收消息的线程
        Session session;
        // Destination ：消息的目的地;消息发送给谁.
        javax.jms.Destination destination;
        // 消费者，消息接收者
        MessageConsumer consumer;
        connectionFactory = new ActiveMQConnectionFactory(
                ActiveMQConnection.DEFAULT_USER,
                ActiveMQConnection.DEFAULT_PASSWORD,
                "tcp://localhost:61616");
        try {
            // 构造从工厂得到连接对象
            connection = connectionFactory.createConnection();
            // 启动
            connection.start();
            // 获取操作连接
            session = connection.createSession(Boolean.FALSE,
                    Session.AUTO_ACKNOWLEDGE);
            // 获取session注意参数值xingbo.xu-queue是一个服务器的queue，须在在ActiveMq的console配置
            destination = session.createTopic("myTopic2");
            consumer = session.createConsumer(destination);
            while (true) {
                //设置接收者接收消息的时间，为了便于测试，这里谁定为100s
                TextMessage message = (TextMessage) consumer.receive(100000);
                if (null != message) {
                    System.out.println("收到消息" + message.getText());
                } else {
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != connection)
                    connection.close();
            } catch (Throwable ignore) {
            }
        }
    }

}
