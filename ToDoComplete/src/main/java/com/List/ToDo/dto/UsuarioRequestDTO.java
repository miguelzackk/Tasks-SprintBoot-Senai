package com.List.ToDo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UsuarioRequestDTO {

	@NotBlank(message = "O nome não pode vazio e/ou nulo.")
	private String nome;

	@NotBlank(message = "O email não pode vazio e nulo.")
	@Email(message = "Digite um email válido.")
	private String email;

	@Size(min = 6, max = 20, message = "A senha deve ter entre 6 a 20 caracteres.")
	private String senha;

	public UsuarioRequestDTO() {

	}

	public UsuarioRequestDTO(String nome, String email, String senha) {
		super();
		this.nome = nome;
		this.email = email;
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
