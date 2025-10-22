package com.login.exemplo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.login.exemplo.entity.Usuario;
import com.login.exemplo.repository.UsuarioRepository;

// Importe Map e HashMap
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
public class UsuarioController {

	@Autowired
	UsuarioRepository usuarioRepository;

	@PostMapping(value = "usuario/cadastro")
	public ResponseEntity<?> saveUser(@RequestBody Usuario user) {
	    Usuario usuario = new Usuario(user.getNome(), user.getEmail(), user.getSenha());
	    usuarioRepository.save(usuario);
	    System.out.println("Usuário salvo com sucesso");
        
        Map<String, String> response = new HashMap<>();
        response.put("message", "Usuário salvo com sucesso.");
	    return ResponseEntity.ok(response); 
	}


	@PostMapping(value = "login")
	public ResponseEntity<?> login(@RequestBody Usuario user) {
		Usuario findUser = usuarioRepository.findByEmail(user.getEmail());
        
        
        Map<String, String> response = new HashMap<>();

		if (findUser == null) {
           
			response.put("message", "Usuário não encontrado.");
           
			return ResponseEntity.status(401).body(response); 
		} else {
			if (findUser.getSenha().equals(user.getSenha()))  {
                response.put("message", "Logado com sucesso.");
				return ResponseEntity.ok(response); 
			} else {
                response.put("message", "Senha incorreta.");
				return ResponseEntity.status(401).body(response); 
			}
		}
	}
	
	@GetMapping(value = "view")
	public List<Usuario> mostrar() {
		List<Usuario> usuarios = usuarioRepository.findAll(); 
        return usuarios;
	}
}