package receiver;

import javax.jms.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ReceiverTopic {

    public static void main(String[] args) {

        try{

                ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContextJMS.xml");
                TopicConnectionFactory factory = (TopicConnectionFactory) applicationContext.getBean("connectionFactory");
                Topic topic = (Topic) applicationContext.getBean("topic");

                // Create a connection. See https://docs.oracle.com/javaee/7/api/javax/jms/package-summary.html
                TopicConnection conn = factory.createTopicConnection() ;
                // Open a session
                TopicSession session = conn.createTopicSession( false, Session.AUTO_ACKNOWLEDGE) ;
                // start the connection
                conn.start();
                // Create a receive
                TopicSubscriber test = session.createSubscriber(topic);
                // receive the message
                TextMessage m = (TextMessage) test.receive();
                // print the message
                System.out.println("Message is"+m);//display the message sent


        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
