package com.todo.entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_tarefa")

public class Tarefa {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(length = 100, nullable = false)
	private String nometarefa;
	@Column(nullable = false)
	private Date dataentrega;
	@Column(nullable = false)
	private StatusTarefa status;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNometarefa() {
		return nometarefa;
	}

	public void setNometarefa(String nometarefa) {
		this.nometarefa = nometarefa;
	}

	public Date getDataentrega() {
		return dataentrega;
	}

	public void setDataentrega(Date dataentrega) {
		this.dataentrega = dataentrega;
	}

	public StatusTarefa getStatus() {
		return status;
	}

	public void setStatus(StatusTarefa status) {
		this.status = status;
	}

	public Tarefa() {

	}

	public Tarefa(String nometarefa, Date dataentrega, StatusTarefa status) {
		super();
		this.nometarefa = nometarefa;
		this.dataentrega = dataentrega;
		this.status = status;
	}

}
