package de.cellent.m2m.mqtt.publisher;

import java.util.Date;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttTopic;

import de.cellent.m2m.mqtt.util.JSONBuilderParserUtil;
import de.cellent.m2m.mqtt.util.MQTTUtil;
import de.cellent.m2m.mqtt.util.ProductionOrderMessage;
import de.cellent.m2m.mqtt.util.MQTTUtil.ClientType;

/**
 * Test-driver to fill data into the system
 * 
 * @author mbohnen, Jun 4, 2015
 *
 */
public class MQTTPublisher {
	
	private MqttClient client;
	
	// you can hierarchically structure further on, i.e. for every sensor
	private final String SENSOR_TOPIC = "nfc/ABC999";
	
	private MqttTopic topic;
	
	public static void main(String[] args) {
		MQTTPublisher client = new MQTTPublisher();
		client.init();
		client.publish();
		
		System.exit(0);
	}
	
	
	private void publish() {
		ProductionOrderMessage poMsg = new ProductionOrderMessage("Station-ABC999", "PO-123456789-ABCD", new Date(System.currentTimeMillis()));

		// converting the message to JSON
		String json = JSONBuilderParserUtil.getInstance().toJSON(poMsg);
		
		MqttMessage msg = new MqttMessage();
		msg.setPayload(json.getBytes());
		
		try {
			topic.publish(msg);
		} catch (MqttException e) {
			e.printStackTrace();
		}
	}
	
	private void init() {
		try {
			client = new MQTTUtil().getClient(ClientType.TYPE_PUBLISHER);
			client.connect();
			topic = client.getTopic(SENSOR_TOPIC);
		} catch (MqttException e) {
			e.printStackTrace();
		}
	}

}
