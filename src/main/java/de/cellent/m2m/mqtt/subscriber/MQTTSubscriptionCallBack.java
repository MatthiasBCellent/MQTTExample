package de.cellent.m2m.mqtt.subscriber;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 * The one doing the work upon receiving a message ...
 *
 * @author mbohnen, Jun 5, 2015
 *
 */
public class MQTTSubscriptionCallBack implements MqttCallback {

	public void connectionLost(Throwable cause) {
	}

	public void deliveryComplete(IMqttDeliveryToken token) {
		// TODO Auto-generated method stub

	}

	public void messageArrived(String topic, MqttMessage message)
			throws Exception {
		
		System.out.println("Message arrived! Topic: " + topic + " Message: "
				+ message.toString());
		
		// we can convert to a Java object, ... save the data ... whatever

		// check out for the last will
		if ("home/LWT".equals(topic)) {
			System.err.println("Sensor gone!");
		}

	}
}
