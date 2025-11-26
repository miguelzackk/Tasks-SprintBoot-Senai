package com.List.ToDo.dto;

import java.time.LocalDate;

import com.List.ToDo.entity.Status;

import jakarta.validation.constraints.NotBlank;

public class TarefaRequestDTO {

	@NotBlank(message = "O nome não pode ser vazio e/ou nulo.")
	private String nome;
	@NotBlank(message = "A descrição não pode ser vazia e/ou nula.")
	private String descricao;
//	@NotBlank(message = "O status não pode ser vazio e/ou nulo.")
	private Status status;
//	@NotBlank(message = "A data de início não pode ser vazia e/ou nula.")
	private LocalDate dtInicio;
//	@NotBlank(message = "A data de fim não pode ser vazia e/ou nula.")
	private LocalDate dtFim;

	public TarefaRequestDTO() {

	}

	public TarefaRequestDTO(String nome, String descricao, Status status, LocalDate dtInicio, LocalDate dtFim) {
		super();
		this.nome = nome;
		this.descricao = descricao;
		this.status = status;
		this.dtInicio = dtInicio;
		this.dtFim = dtFim;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public LocalDate getDtInicio() {
		return dtInicio;
	}

	public void setDtInicio(LocalDate dtInicio) {
		this.dtInicio = dtInicio;
	}

	public LocalDate getDtFim() {
		return dtFim;
	}

	public void setDtFim(LocalDate dtFim) {
		this.dtFim = dtFim;
	}

}
