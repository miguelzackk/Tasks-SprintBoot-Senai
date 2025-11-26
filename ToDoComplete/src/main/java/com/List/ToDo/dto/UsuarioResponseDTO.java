package com.List.ToDo.dto;

import com.List.ToDo.entity.Usuario;

public class UsuarioResponseDTO {
	private long id;
	private String nome;
	private String email;

	public UsuarioResponseDTO(Usuario usuario) {
		super();
		this.id = usuario.getId();
		this.nome = usuario.getNome();
		this.email = usuario.getEmail();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	@Override
	public String toString() {
		return "Usuário criado! " + "Nome=" + nome + ", Email=" + email;
	}

}
