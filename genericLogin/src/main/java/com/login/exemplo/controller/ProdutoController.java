package com.login.exemplo.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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

import com.login.exemplo.dto.ProdutoRequestDTO;
import com.login.exemplo.dto.ProdutoResponseDTO;
import com.login.exemplo.entity.Produto;
import com.login.exemplo.repository.ProdutoRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "produto")
public class ProdutoController {

	@Autowired
	ProdutoRepository produtorepository;

	// cadastro de produto usando body do postman
	@PostMapping(value = "cadastro")
	public ResponseEntity<?> cadastroProduto(@Valid @RequestBody ProdutoRequestDTO produto) {
		Produto produtos = new Produto(produto.getNome(), produto.getPreco(), produto.getQuantidade());
		produtorepository.save(produtos);

		Map<String, Object> response = new LinkedHashMap<>();
		response.put("message", "Usuário salvo com sucesso.");
		response.put("nome", produto.getNome());
		response.put("preço", produto.getPreco());
		response.put("quantidade", produto.getQuantidade());

		return ResponseEntity.ok(response);
	}

	@GetMapping(value = "list")
	public List<ProdutoResponseDTO> searchAll() {
		List<Produto> produto = produtorepository.findAll();

		List<ProdutoResponseDTO> listarProdutos = produto.stream().map(ProdutoResponseDTO::new).toList();
		return listarProdutos;
	}

	// pesquisa por id
	@GetMapping(value = "{id}")
	public ResponseEntity<?> searchById(@PathVariable int id) {
		Optional<Produto> produto = produtorepository.findById(id);

		if (produto.isPresent()) {
			ProdutoResponseDTO dto = new ProdutoResponseDTO(produto.get());
			return ResponseEntity.status(HttpStatus.OK).body(dto);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Esse ID não existe.");
		}
	}

	// atualizar quantidade de produtos pelo id
	@PutMapping(value = "update/{id}")
	public ResponseEntity<?> atualizarQuantidade(@PathVariable int id, @RequestBody Produto newProduct) {
		Optional<Produto> ProdutoExistente = produtorepository.findById(id);

		if (ProdutoExistente.isPresent()) {
			Produto Produto = ProdutoExistente.get();
			Produto.setQuantidade(newProduct.getQuantidade());
			produtorepository.save(Produto);
			return ResponseEntity.status(HttpStatus.OK).body(Produto);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Esse ID não existe");
		}

	}

	// deletar produto pelo id
	@DeleteMapping(value = "delete/{id}")
	public ResponseEntity<?> deletarProduto(@PathVariable int id) {
		Optional<Produto> ProdutoExistente = produtorepository.findById(id);

		if (ProdutoExistente.isPresent()) {
			produtorepository.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK).body("O produto foi deletado.");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Esse ID não existe");
		}
	}
// listagem de todos os produtos
//	@GetMapping(value = "listnormal")
//	public List<Produto> searchAlls() {
//		List<Produto> produtos = produtorepository.findAll();
//		return produtos;
//	}
}
