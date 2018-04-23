package com.crudElasticSearchLab.persistences;

import java.util.HashMap;
import java.util.Map;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;

public abstract class CrudGenerics<T> {
	
	public CrudGenerics(Class<T> obj) {
	}
	
	public CrudGenerics() {
		// TODO Auto-generated constructor stub
	}

	public IndexResponse save(TransportClient client ,String index, String type, String id, T obg){
		Map<String, T> map = new HashMap<>();
		map.put("content", obg);
		IndexResponse response = client.prepareIndex(index, type, id).setSource(map).get();
		return response;
	}
	
	public T findOne(TransportClient client ,String index, String type, String id){
		Map<String, Object> m = client.prepareGet("twitter", "tweet", "1").get().getSource();
		Object t = new Object();
		for(Map.Entry<String, Object> entry: m.entrySet()){
			t = entry.getValue();
		}
		return (T) t;
	}
	
}
