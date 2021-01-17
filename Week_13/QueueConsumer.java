package com.example.activemq.queue;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;

import javax.jms.*;

/**
 * @author liugenghua
 **/
public class QueueConsumer {

    public static void main(String[] args) {
        Queue queue = new ActiveMQQueue("test.queue");
        testQueue(queue);
    }

    /**
     * 消费者监听test.queue，消费接收到的消息
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
            MessageConsumer consumer = session.createConsumer(queue);
            MessageListener listener = new MessageListener() {
                @Override
                public void onMessage(Message message) {
                    System.out.println(" => receive from " + queue.toString() + ": " + message);
                }
            };
            consumer.setMessageListener(listener);
            System.in.read();
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
