package com.todo.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todo.entity.Tarefa;
import com.todo.repository.TarefaRepository;

@RestController
@RequestMapping("/tasks")
public class TarefaController {

	@Autowired
	TarefaRepository taskrepository;

	@PostMapping(value = "cadastro")
	public ResponseEntity<?> salvar(@RequestBody Tarefa tarefa) {
		LocalDate dataentrega = LocalDate.now();
		Tarefa tarefas = new Tarefa(tarefa.getNometarefa(), dataentrega, tarefa.getStatus());
		taskrepository.save(tarefas);
		return ResponseEntity.ok("Tarefa criada.");
	}

	@GetMapping(value = "view")
	public List<Tarefa> mostrar() {
		List<Tarefa> tarefa = taskrepository.findAll();
		return tarefa;
	}

	@GetMapping(value = "view/{id}")
	public ResponseEntity<?> viewbyid(@PathVariable long id) {
		Optional<Tarefa> TarefaExistente = taskrepository.findById(id);

		if (TarefaExistente.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(TarefaExistente);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Esse ID não existe");
		}
	}

	@PutMapping(value = "update/{id}")
	public ResponseEntity<?> updatenametask(@PathVariable long id, @RequestBody Tarefa newTarefa) {
		Optional<Tarefa> TarefaExistente = taskrepository.findById(id);

		if (TarefaExistente.isPresent()) {
			Tarefa tarefa = TarefaExistente.get();
			tarefa.setNometarefa(newTarefa.getNometarefa());
			taskrepository.save(tarefa);
			return ResponseEntity.status(HttpStatus.OK).body("Nome da tarefa altetado");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Esse ID não existe");
		}
	}

	@DeleteMapping(value = "delete/{id}")
	public ResponseEntity<?> deletetask(@PathVariable long id) {
		Optional<Tarefa> TarefaExistente = taskrepository.findById(id);

		if (TarefaExistente.isPresent()) {
			taskrepository.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK).body("Tarefa deletada.");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Esse ID não existe.");
		}
	}

}
