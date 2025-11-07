package com.login.exemplo.controller;

import java.util.HashMap;
import java.util.LinkedHashMap;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.login.exemplo.dto.UsuarioRequestDTO;
import com.login.exemplo.dto.UsuarioResponseDTO;
import com.login.exemplo.entity.Usuario;
import com.login.exemplo.repository.UsuarioRepository;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "usuario")
public class UsuarioController {

	@Autowired
	UsuarioRepository usuarioRepository;

	// cadastro de usuario
	@PostMapping(value = "cadastro")
	public ResponseEntity<?> saveUser(@Valid @RequestBody UsuarioRequestDTO user) {
		Usuario usuario = new Usuario(user.getNome(), user.getEmail(), user.getSenha());
		usuarioRepository.save(usuario);
		System.out.println("Usuário salvo com sucesso");

		Map<String, Object> response = new LinkedHashMap<>();
		response.put("message", "Usuário salvo com sucesso.");
		response.put("nome", user.getNome());
		response.put("email", user.getEmail());

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

	// Listagem de usuario DTO - lambda
	@GetMapping(value = "view")
	public List<UsuarioResponseDTO> mostrar() {
		List<Usuario> usuarios = usuarioRepository.findAll();
		List<UsuarioResponseDTO> listadeUsuarios = usuarios.stream().map(UsuarioResponseDTO::new).toList();

		return listadeUsuarios;
	}

	// busca por id - dto
	@GetMapping(value = "view/{id}")
	public ResponseEntity<?> searchById(@PathVariable int id) {
		Optional<Usuario> usuario = usuarioRepository.findById(id);

		if (usuario.isPresent()) {
			UsuarioResponseDTO dto = new UsuarioResponseDTO(usuario.get());
			return ResponseEntity.status(HttpStatus.OK).body(dto);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Esse ID não existe.");
		}
	}

	// mudar o usuario
	@PutMapping("/{id}")
	public ResponseEntity<?> atualizar(@PathVariable int id, @RequestBody Usuario novoUsuario) {
		Optional<Usuario> UsuarioExistente = usuarioRepository.findById(id);

		if (UsuarioExistente.isPresent()) {
			Usuario Usuario = UsuarioExistente.get();
			Usuario.setNome(novoUsuario.getNome());
//			Usuario.setSenha(novoUsuario.getSenha());
			usuarioRepository.save(Usuario);
			UsuarioResponseDTO dto = new UsuarioResponseDTO(Usuario);
			return ResponseEntity.status(HttpStatus.OK).body(dto);

		} else {
			return ResponseEntity.notFound().build();
		}
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

	// cadastro pelo endpoint
	@PostMapping(value = "cadastro/link/{nome}/{email}/{senha}")
	public ResponseEntity<?> saveUser1(@PathVariable String nome, @PathVariable String email,
			@PathVariable String senha) {
		Usuario usuario = new Usuario(nome, email, senha);
		usuarioRepository.save(usuario);

		Map<String, Object> response = new HashMap<>();
		response.put("message", "Usuário salvo com sucesso.");
		response.put("nome", nome);
		response.put("email", email);

		return ResponseEntity.ok(response);

	}

	// listagem de usuarios
//	@GetMapping(value = "usuario/view")
//	public List<Usuario> mostrar() {
//		List<Usuario> usuarios = usuarioRepository.findAll();
//		return usuarios;
//	}

	// listagem de dados DTO - for
//	@GetMapping(value = "usuario/view")
//	public List<UsuarioResponseDTO> mostrar() {
//		List<Usuario> usuarios = usuarioRepository.findAll();
//		List<UsuarioResponseDTO> listadeUsuarios = new ArrayList<>();
//		
//		for (Usuario usuario : usuarios) {
//			listadeUsuarios.add(new UsuarioResponseDTO(usuario));
//		}
//		
//		return listadeUsuarios;
//	}
}