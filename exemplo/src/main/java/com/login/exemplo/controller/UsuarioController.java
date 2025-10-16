package com.login.exemplo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.login.exemplo.entity.Usuario;
import com.login.exemplo.repository.UsuarioRepository;

@RestController
public class UsuarioController {

	@Autowired
	UsuarioRepository usuarioRepository;

	@PostMapping(value = "usuario/cadastro")
	public ResponseEntity<?> saveUser(@RequestBody Usuario user) {
	    Usuario usuario = new Usuario(user.getNome(), user.getEmail(), user.getSenha());
	    usuarioRepository.save(usuario);
	    System.out.println("Usuário salvo com sucesso");
	    return ResponseEntity.ok("Usuário salvo com sucesso.");
	}


	@PostMapping(value = "login")
	public ResponseEntity<?> login(@RequestBody Usuario user) {
		Usuario findUser = usuarioRepository.findByEmail(user.getEmail());
		if (findUser == null) {
			return ResponseEntity.ok("Logado com sucesso");
		} else {
			if (findUser.getSenha().equals(user.getSenha()))  {
				return ResponseEntity.ok("Logado com sucesso.");
			} else {
				return ResponseEntity.ok("Senha incorreta.");
			}
		}
	}
}
