package com.todo.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todo.entity.StatusTarefa;
import com.todo.entity.Tarefa;
import com.todo.repository.TarefaRepository;

@RestController
@RequestMapping("/tasks")
public class TarefaController {
	@Autowired
	TarefaRepository task;

	@PostMapping(value = "new")
	public ResponseEntity<?> salvar() {
		Date dataentrega = Date.valueOf("2025-11-03");
		Tarefa Ryan = new Tarefa("Trabalho de matemática", dataentrega, StatusTarefa.PENDENTE);
		task.save(Ryan);
		return ResponseEntity.ok(Ryan);
	}

	@GetMapping(value = "view")
	public List<Tarefa> mostrar() {
		List<Tarefa> tarefa = task.findAll();
		return tarefa;
	}
}
