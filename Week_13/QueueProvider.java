package com.example.activemq.queue;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;

import javax.jms.*;

/**
 * @author liugenghua
 **/
public class QueueProvider {

    public static void main(String[] args) {
        Queue queue = new ActiveMQQueue("test.queue");
        testQueue(queue);
    }

    /**
     * 发送消息到test.queue
     * @param queue
     */
    private static void testQueue(Queue queue) {
        ActiveMQConnection conn = null;
        Session session = null;
        try {
            ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");
            conn = (ActiveMQConnection) factory.createConnection();
            conn.start();
            session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer producer = session.createProducer(queue);
            int index = 0;
            while (index++ < 100) {
                Thread.sleep(100);
                TextMessage message = session.createTextMessage(index + " message.");
                System.out.println("send message " + index);
                producer.send(message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != session){
                try {
                    session.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
            if (null != conn){
                try {
                    conn.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
