package com.crudElasticSearchLab;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import com.crudElasticSearchLab.classes.Pessoa;

public class Main extends CrudGenerics<Pessoa> {

	public Main() {
		super();
	}

	public Main(Class<Pessoa> obj) {
		super(obj);
		// TODO Auto-generated constructor stub
	}

	public static void main(String args[]) throws UnknownHostException {
		Settings settings = Settings.builder().put("cluster.name", "myClusterName").build();
		TransportClient client = new PreBuiltTransportClient(settings)
				.addTransportAddress(new TransportAddress(InetAddress.getByName("192.168.100.242"), 9300));
		Pessoa json = new Pessoa.PessoaBuilder().nome("Wagner").sobrenome("Paulino").apelido("celebro").idade(25)
				.build();
		new Main().save(client, "twitter", "tweet", "1", json);

	}
}
