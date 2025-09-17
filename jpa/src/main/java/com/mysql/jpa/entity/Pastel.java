package com.mysql.jpa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_pastel")

public class Pastel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;	
	private String sabor;
	private double preco;
	private String tamanho;

}