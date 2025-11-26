package com.login.exemplo.controller;

import java.util.List;

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
import com.login.exemplo.service.UsuarioService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "usuario")
public class UsuarioController {

	@Autowired
	UsuarioService usuarioService;

	
	//ta funfando
	// cadastro de usuario
	@PostMapping(value = "cadastro")
	public ResponseEntity<?> saveUser(@Valid @RequestBody UsuarioRequestDTO user) {
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.saveUser(user));
	}
	
	// fazer login - arrumado para funcionar no frontend
	@PostMapping(value = "login")
	public ResponseEntity<?> login(@Valid @RequestBody UsuarioRequestDTO user) {
		return ResponseEntity.status(HttpStatus.OK).body(usuarioService.login(user));
	}

	// Listagem de usuario DTO - lambda
	@GetMapping(value = "view")
	public List<UsuarioResponseDTO> mostrar() {
		return usuarioService.mostrar();
	}
	
	
	//ta retornando em string
	// busca por id - dto
	@GetMapping(value = "view/{id}")
	public ResponseEntity<?> searchById(@PathVariable int id) {
		return ResponseEntity.status(HttpStatus.OK).body(usuarioService.searchById(id));
	}
	
	
	//retorna em string
	// mudar o usuario
	@PutMapping("/{id}")
	public ResponseEntity<?> atualizar(@Valid @PathVariable int id, @RequestBody Usuario novoUsuario) {
		return ResponseEntity.status(HttpStatus.OK).body(usuarioService.atualizar(id, novoUsuario));
	}

	// deletar usuairo por id
	@DeleteMapping(value = "delete/{id}")
	public ResponseEntity<?> deleteUsuario(@PathVariable int id) {
		return ResponseEntity.status(HttpStatus.OK).body(usuarioService.deleteUsuario(id));
	}

	// cadastro pelo endpoint
	@PostMapping(value = "cadastro/link/{nome}/{email}/{senha}")
	public ResponseEntity<?> saveUser1(@Valid @PathVariable String nome, @PathVariable String email,
			@PathVariable String senha) {
		return ResponseEntity.status(HttpStatus.OK).body(usuarioService.saveUser1(nome, email, senha));

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