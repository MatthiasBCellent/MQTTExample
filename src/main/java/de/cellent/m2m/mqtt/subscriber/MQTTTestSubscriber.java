package de.cellent.m2m.mqtt.subscriber;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;

import de.cellent.m2m.mqtt.util.MQTTUtil;
import de.cellent.m2m.mqtt.util.MQTTUtil.ClientType;

/**
 * The 'autonomous' standalone receiver ...
 * 
 * @author mbohnen, Jun 5, 2015
 *
 */
public class MQTTTestSubscriber {

	private MqttClient mqttClient;

	public static void main(String... args) {
		MQTTTestSubscriber subscriber = new MQTTTestSubscriber();
		subscriber.start();
	}

	public MQTTTestSubscriber() {

		try {
			mqttClient = new MQTTUtil().getClient(
					ClientType.TYPE_SUBSCRIBER);
		} catch (MqttException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void start() {
		try {

			mqttClient.setCallback(new MQTTSubscriptionCallBack());
			mqttClient.connect();

			// Subscribe to all subtopics of sensors
			String topic = "nfc/#";
			mqttClient.subscribe(topic);

			System.out.println("Subscriber is now listening to " + topic);

		} catch (MqttException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
}
