package com.crudElasticSearchLab.classes;

public class Pessoa {

	private String nome;

	private String sobrenome;

	private String apelido;

	private int idade;

	public Pessoa(String nome, String sobrenome, String apelido, int idade) {
		super();
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.apelido = apelido;
		this.idade = idade;
	}

	public static class PessoaBuilder {

		private String nome;

		private String sobrenome;

		private String apelido;

		private int idade;

		public PessoaBuilder() {// para deixar um atributo obrigatorio, coloque
								// ele no construtor
			super();
		}

		public PessoaBuilder nome(String nome) {
			this.nome = nome;
			return this;
		}

		public PessoaBuilder sobrenome(String sobrenome) {
			this.sobrenome = sobrenome;
			return this;
		}

		public PessoaBuilder apelido(String apelido) {
			this.apelido = apelido;
			return this;
		}

		public PessoaBuilder idade(int idade) {
			this.idade = idade;
			return this;
		}

		public Pessoa build() {
			return new Pessoa(nome, sobrenome, apelido, idade);
		}

	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getApelido() {
		return apelido;
	}

	public void setApelido(String apelido) {
		this.apelido = apelido;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	@Override
	public String toString() {
		return "Pessoa [nome=" + nome + ", sobrenome=" + sobrenome + ", apelido=" + apelido + ", idade=" + idade + "]";
	}

}
