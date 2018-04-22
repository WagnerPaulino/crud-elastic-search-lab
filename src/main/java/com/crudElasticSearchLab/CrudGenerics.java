package com.crudElasticSearchLab;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentType;

import com.crudElasticSearchLab.classes.Pessoa;

public abstract class CrudGenerics<T> {
	
	public CrudGenerics(Class<Pessoa> obj) {
	}
	
	public CrudGenerics() {
		// TODO Auto-generated constructor stub
	}

	public void save(TransportClient client ,String index, String type, String id, T obg){
		IndexResponse response = client.prepareIndex(index, type, id).setSource(obg, XContentType.JSON).get();
	}
	
}
