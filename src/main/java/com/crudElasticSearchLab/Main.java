package com.crudElasticSearchLab;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import com.crudElasticSearchLab.classes.Pessoa;
import com.crudElasticSearchLab.persistences.PessoaDao;

public class Main{
	
	private static PessoaDao dao;
	public Main() {
		dao = new PessoaDao();
	}

	public static void main(String args[]) throws UnknownHostException {
		Settings settings = Settings.builder().put("cluster.name", "myClusterName").build();
		TransportClient client = new PreBuiltTransportClient(settings);
		client.addTransportAddress(new TransportAddress(InetAddress.getByName("192.168.100.242"), 9300));
		Pessoa json = new Pessoa.PessoaBuilder().nome("Wagner").sobrenome("Paulino").apelido("celebro").idade(25)
				.build();
		dao.save(client, "twitter", "tweet", "1", json);

	}
}
