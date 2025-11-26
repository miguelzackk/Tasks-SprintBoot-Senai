package com.todo.controller;

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

import com.todo.entity.Usuario;
import com.todo.repository.UsuarioRepository;

@RestController
@RequestMapping("/users")
public class UsuarioController {
	@Autowired
	UsuarioRepository userrepository;

	// cadastras usuario
	@PostMapping(value = "cadastro")
	public ResponseEntity<?> salvar(@RequestBody Usuario user) {
		Usuario usuario = new Usuario(user.getNome(), user.getEmail(), user.getSenha());
		userrepository.save(usuario);
		return ResponseEntity.status(HttpStatus.OK).body("Usuário criado.");
	}

	// ver lista de usuarios
	@GetMapping(value = "view")
	public List<Usuario> mostrar() {
		List<Usuario> usuario = userrepository.findAll();
		return usuario;
	}

	// busca de usuario por id
	@GetMapping(value = "view/{id}")
	public ResponseEntity<?> searchid(@PathVariable long id) {
		Optional<Usuario> UsuarioExistente = userrepository.findById(id);

		if (UsuarioExistente.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(UsuarioExistente);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Esse ID não existe.");
		}
	}

	// atualizar senha do usuario por id
	@PutMapping(value = "update/password/{id}")
	public ResponseEntity<?> updatepassword(@PathVariable long id, @RequestBody Usuario newUsuario) {
		Optional<Usuario> UsuarioExistente = userrepository.findById(id);

		if (UsuarioExistente.isPresent()) {
			Usuario usuario = UsuarioExistente.get();
			usuario.setSenha(newUsuario.getSenha());
			userrepository.save(usuario);
			return ResponseEntity.status(HttpStatus.OK).body("Senha atualizada.");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Esse ID não existe");
		}
	}
	
	
	//deletar usuario por id
	@DeleteMapping(value = "delete/{id}")
	public ResponseEntity<?> deleteuser(@PathVariable long id) {
		Optional<Usuario> UsuarioExistente = userrepository.findById(id);

		if (UsuarioExistente.isPresent()) {
			userrepository.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK).body("Usuário deletado.");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Esse ID não existe");
		}

	}
}