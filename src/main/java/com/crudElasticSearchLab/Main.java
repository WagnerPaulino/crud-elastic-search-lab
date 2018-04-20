package com.crudElasticSearchLab;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

public class Main {
	public static void main(String args[]) throws UnknownHostException {
		Settings settings = Settings.builder()
		        .put("cluster.name", "myClusterName").build();
		TransportClient client = new PreBuiltTransportClient(settings)
				.addTransportAddress(new TransportAddress(InetAddress.getByName("192.168.100.242"), 9300));

		Map<String, Object> json = new HashMap<String, Object>();
		json.put("user", "Wagner");
		json.put("postDate", new Date());
		json.put("message", "Tentando salvar dados no elasticsearch");
		
		IndexResponse response = client.prepareIndex("twitter", "tweet", "1")
			    .setSource(json, XContentType.JSON)
			    .get();
	}
}
