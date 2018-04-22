package com.crudElasticSearchLab.persistences;

import com.crudElasticSearchLab.classes.Pessoa;

public class PessoaDao extends CrudGenerics<Pessoa>{

	public PessoaDao() {
		super();
	}

	public PessoaDao(Class<Pessoa> obj) {
		super(obj);
	}
	
}
