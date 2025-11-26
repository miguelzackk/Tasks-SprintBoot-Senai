package com.login.exemplo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.login.exemplo.dto.UsuarioRequestDTO;
import com.login.exemplo.dto.UsuarioResponseDTO;
import com.login.exemplo.entity.Usuario;
import com.login.exemplo.repository.UsuarioRepository;

@Service
public class UsuarioService {
	private final UsuarioRepository usuarioRepository;

	public UsuarioService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	public UsuarioResponseDTO saveUser(UsuarioRequestDTO usuarioRequestDTO) {
		Usuario usuario = new Usuario(usuarioRequestDTO.getNome(), usuarioRequestDTO.getEmail(),
				usuarioRequestDTO.getSenha());
		usuarioRepository.save(usuario);
		UsuarioResponseDTO user = new UsuarioResponseDTO(usuario);
		return user;
	}

	// isso foi copiar e colar

	public String login(UsuarioRequestDTO user) {
		Usuario findUser = usuarioRepository.findByEmail(user.getEmail());

		Map<String, String> response = new HashMap<>();

		if (findUser == null) {

			return "Usuário não encontrado.";
		} else {
			if (findUser.getSenha().equals(user.getSenha())) {
				return response.toString() + "Logado com sucesso.";
			} else {

				return "Senha incorreta.";
			}
		}
	}

	// Listagem de usuario DTO - lambda
	public List<UsuarioResponseDTO> mostrar() {
		List<Usuario> usuarios = usuarioRepository.findAll();
		List<UsuarioResponseDTO> listadeUsuarios = usuarios.stream().map(UsuarioResponseDTO::new).toList();

		return listadeUsuarios;
	}

	// busca por id - dto
	public String searchById(int id) {
		Optional<Usuario> usuario = usuarioRepository.findById(id);

		if (usuario.isPresent()) {
			UsuarioResponseDTO dto = new UsuarioResponseDTO(usuario.get());
			return dto.toString();
		} else {
			return "Esse ID não existe.";
		}
	}

	// mudar o usuario
	public String atualizar(int id, Usuario novoUsuario) {
		Optional<Usuario> UsuarioExistente = usuarioRepository.findById(id);

		if (UsuarioExistente.isPresent()) {
			Usuario Usuario = UsuarioExistente.get();
			Usuario.setNome(novoUsuario.getNome());
//			Usuario.setSenha(novoUsuario.getSenha());
			usuarioRepository.save(Usuario);
			UsuarioResponseDTO dto = new UsuarioResponseDTO(Usuario);
			return dto.toString();

		} else {
			return "Não foi achado.";
		}
	}

	// deletar usuairo por id
	public String deleteUsuario(int id) {
		Optional<Usuario> usuario = usuarioRepository.findById(id);

		if (usuario.isPresent()) {
			usuarioRepository.deleteById(id);
			return "Usuário deletado com sucesso.";
		} else {

			return "Esse ID não existe";
		}
	}

	// cadastro pelo endpoint
	public String saveUser1(String nome, String email, String senha) {
		Usuario usuario = new Usuario(nome, email, senha);
		usuarioRepository.save(usuario);

		Map<String, Object> response = new HashMap<>();
		response.put("message", "Usuário salvo com sucesso.");
		response.put("nome", nome);
		response.put("email", email);

		return response.toString();

	}
}
