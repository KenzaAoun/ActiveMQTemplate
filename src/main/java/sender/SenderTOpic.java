package sender;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import javax.jms.*;

public class SenderTOpic {

    public static void main(String[] args) {
        try{

            ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContextJMS.xml");
            TopicConnectionFactory factory = (TopicConnectionFactory) applicationContext.getBean("connectionFactory");
            Topic topic = (Topic) applicationContext.getBean("topic");

            // Create a connection. See https://docs.oracle.com/javaee/7/api/javax/jms/package-summary.html
            TopicConnection conn = factory.createTopicConnection() ;
            // Open a session without transaction and acknowledge automatic
            TopicSession session = conn.createTopicSession( false, Session.AUTO_ACKNOWLEDGE) ;
            // Start the connection
            conn.start();
            // Create a sender
            TopicPublisher producer = session.createPublisher( topic) ;
            // Create a message
            String msg ="message to send";
            TextMessage m = session.createTextMessage();
            m.setText(msg);
            // Send the message
            producer.send(m, DeliveryMode.PERSISTENT,4,10000) ;
            // Close the session
            session.close();
            // Close the connection
            conn.close();

        }catch(Exception e){

            e.printStackTrace();
        }

    }

}

