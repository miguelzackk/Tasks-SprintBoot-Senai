package com.List.ToDo.dto;

import java.time.LocalDate;

import com.List.ToDo.entity.Status;
import com.List.ToDo.entity.Tarefa;

public class TarefaResponseDTO {
	private long id;
	private String nome;
	private String descricao;
	private Status status;
	private LocalDate dtInicio;
	private LocalDate dtFim;

	public TarefaResponseDTO(Tarefa tarefa) {
		super();
		this.id = tarefa.getId();
		this.nome = tarefa.getNome();
		this.descricao = tarefa.getDescricao();
		this.status = tarefa.getStatus();
		this.dtInicio = tarefa.getDtInicio();
		this.dtFim = tarefa.getDtFim();
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

	@Override
	public String toString() {
		return "TarefaResponseDTO [id=" + id + ", nome=" + nome + ", descricao=" + descricao + ", status=" + status
				+ ", dtInicio=" + dtInicio + ", dtFim=" + dtFim + "]";
	}
	
	
}
