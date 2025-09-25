package com.todo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todo.entity.Usuario;
import com.todo.repository.UsuarioRepository;

@RestController
@RequestMapping("/users")
public class UsuarioController {
	@Autowired
	UsuarioRepository user;

	@PostMapping(value = "new")
	public ResponseEntity<?> salvar() {
		Usuario Ryan = new Usuario("Ryan", "ry90ryan@gmail.com", "123456");
		user.save(Ryan);
		return ResponseEntity.ok(Ryan);
	}

	@GetMapping(value = "view")
	public List<Usuario> mostrar() {
		List<Usuario> usuario = user.findAll();
		return usuario;
	}
}