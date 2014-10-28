package me.niketpatel.mdb;
import java.util.Date;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;


/**
 * Message-Driven Bean implementation class for: QueueListnerMDB
 */
@MessageDriven(
		activationConfig = { @ActivationConfigProperty(
				propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
							 @ActivationConfigProperty(
				propertyName = "destination", propertyValue = "queue/MyQueue")
		})
public class QueueListnerMDB implements MessageListener {

    public QueueListnerMDB() {
    }
	
    public void onMessage(Message message) {
        if (message instanceof TextMessage){
        	System.out.println("Queue: I received a TextMessage at "
                    + new Date());
            TextMessage msg = (TextMessage) message;
            try {
				System.out.println("Message is : " + msg.getText());
			} catch (JMSException e) {
				e.printStackTrace();
			}
        } else if (message instanceof ObjectMessage){
        	System.out.println("Queue: I received a ObjectMessage at "
                    + new Date());
        	ObjectMessage msg = (ObjectMessage) message;
            try {
				System.out.println("Message is : " + msg.getObject());
			} catch (JMSException e) {
				e.printStackTrace();
			}
        }
        
    }

}
