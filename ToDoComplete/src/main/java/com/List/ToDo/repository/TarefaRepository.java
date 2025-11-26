package com.List.ToDo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.List.ToDo.entity.Tarefa;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Integer>{
	
	Tarefa findByNome(String nome);

}
