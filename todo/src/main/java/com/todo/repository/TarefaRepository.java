package com.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.todo.entity.Tarefa;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

}
