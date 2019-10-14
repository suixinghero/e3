package org.e3.search.message;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * @author xujin
 * @package-name org.e3.search.message
 * @createtime 2019-10-13 20:33
 */

public class MyMessageListener implements MessageListener {
    @Override
    public void onMessage(Message message) {
        //取内容消息
        TextMessage textMessage= (TextMessage) message;
        try {
            String text=textMessage.getText();
            System.out.println(text);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
