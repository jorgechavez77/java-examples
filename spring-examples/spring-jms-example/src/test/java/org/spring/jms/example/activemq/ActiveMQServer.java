package org.spring.jms.example.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.broker.BrokerService;
import org.apache.log4j.Logger;

public class ActiveMQServer {

	private static final Logger LOG = Logger.getLogger(ActiveMQServer.class);

	private static BrokerService broker;

	public static void start() {
		LOG.info("Starting ActiveMQ Server");
		new ActiveMQConnectionFactory("vm://localhost?broker.persistent=false");
		broker = new BrokerService();
		broker.setPersistent(false);
		try {
			broker.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void stop() {
		LOG.info("Stopping ActiveMQ Server");
		try {
			broker.stop();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
