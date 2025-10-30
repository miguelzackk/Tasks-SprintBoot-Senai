package com.login.exemplo.controller;

// Impor do Map e HashMap
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.login.exemplo.entity.Usuario;
import com.login.exemplo.repository.UsuarioRepository;

@RestController
@CrossOrigin(origins = "*")
public class UsuarioController {

	@Autowired
	UsuarioRepository usuarioRepository;

	// cadastro de usuario - arrumado para funcionar no frontend
	@PostMapping(value = "usuario/cadastro")
	public ResponseEntity<?> saveUser(@RequestBody Usuario user) {
		Usuario usuario = new Usuario(user.getNome(), user.getEmail(), user.getSenha());
		usuarioRepository.save(usuario);
		System.out.println("Usuário salvo com sucesso");

		Map<String, String> response = new HashMap<>();
		response.put("message", "Usuário salvo com sucesso.");
		return ResponseEntity.ok(response);
	}

	@PostMapping(value = "usuario/cadastro/link/{nome}/{email}/{senha}")
	public ResponseEntity<?> saveUser1(@PathVariable String nome, @PathVariable String email,
			@PathVariable String senha) {
		Usuario usuario = new Usuario(nome, email, senha);
		usuarioRepository.save(usuario);

		Map<String, String> response = new HashMap<>();
		response.put("message", "Usuário salvo com sucesso.");
		return ResponseEntity.ok(response);
	}

	// fazer login - arrumado para funcionar no frontend
	@PostMapping(value = "login")
	public ResponseEntity<?> login(@RequestBody Usuario user) {
		Usuario findUser = usuarioRepository.findByEmail(user.getEmail());

		Map<String, String> response = new HashMap<>();

		if (findUser == null) {

			response.put("message", "Usuário não encontrado.");

			return ResponseEntity.status(401).body(response);
		} else {
			if (findUser.getSenha().equals(user.getSenha())) {
				response.put("message", "Logado com sucesso.");
				return ResponseEntity.ok(response);
			} else {
				response.put("message", "Senha incorreta.");
				return ResponseEntity.status(401).body(response);
			}
		}
	}

	// listagem de usuarios
	@GetMapping(value = "view")
	public List<Usuario> mostrar() {
		List<Usuario> usuarios = usuarioRepository.findAll();
		return usuarios;
	}

	// deletar usuairo por id
	@DeleteMapping(value = "delete/{id}")
	public ResponseEntity<?> deleteUsuario(@PathVariable int id) {
		Optional<Usuario> usuario = usuarioRepository.findById(id);

		if (usuario.isPresent()) {
			usuarioRepository.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK).body("Usuário deletado com sucesso.");
		} else {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Esse ID não existe");
		}
	}
	
	
	//mudar o usuairo
	@PutMapping("/{id}")
	public ResponseEntity<?> atualizar(@PathVariable int id, @RequestBody Usuario novoUsuario) {
		Optional<Usuario> UsuarioExistente = usuarioRepository.findById(id);

		if (UsuarioExistente.isPresent()) {
			Usuario Usuario = UsuarioExistente.get();
			Usuario.setNome(novoUsuario.getNome());
//			Usuario.setSenha(novoUsuario.getSenha());
			usuarioRepository.save(Usuario);
			return ResponseEntity.ok(Usuario);

		} else {
			return ResponseEntity.notFound().build();
		}
	}
}