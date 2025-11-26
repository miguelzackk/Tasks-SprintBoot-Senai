package com.login.exemplo.dto;

import jakarta.validation.constraints.NotNull;

public class ProdutoRequestDTO {
	@NotNull
	private String nome;

	@NotNull
	private double preco;

	@NotNull
	private int quantidade;

	public ProdutoRequestDTO() {

	}

	public ProdutoRequestDTO(String nome, double preco, int quantidade) {
		super();
		this.nome = nome;
		this.preco = preco;
		this.quantidade = quantidade;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

}
