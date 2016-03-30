package org.spring.jms.example;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.spring.jms.example.activemq.ActiveMQServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-config.xml")
public class JmsExampleTest {

	@BeforeClass
	public static void before() {
		ActiveMQServer.start();
	}

	@AfterClass
	public static void after() {
		ActiveMQServer.stop();
	}

	@Autowired
	private ConnectionFactory connectionFactory;

	@Autowired
	private Destination destination;

	@Autowired
	private JmsTemplate jmsTemplate;

	@Test
	public void testNoSpring() {
		Connection conn = null;
		Session session = null;
		try {
			conn = connectionFactory.createConnection();
			session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
			MessageProducer producer = session.createProducer(destination);
			TextMessage message = session.createTextMessage();

			message.setText("Hello World");
			producer.send(message);
		} catch (JMSException e) {
			e.printStackTrace();
		} finally {
			try {
				if (session != null) {
					session.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (JMSException ex) {
			}
		}
	}

	@Test
	public void testSpring() {
		jmsTemplate.send(destination, new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage("Hello World From spring-jms");
			}
		});
	}

	@Test
	public void testSpring2() {
		jmsTemplate.convertAndSend("Hello World from XXXXX");
	}

	@Ignore
	@Test
	public void testReceive() {
		TextMessage message = (TextMessage) jmsTemplate.receive();
		try {
			System.out.println(message.getText());
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}
