package com.login.exemplo.controller;

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

import com.login.exemplo.entity.Produto;
import com.login.exemplo.repository.ProdutoRepository;

@RestController
@CrossOrigin(origins = "*")
public class ProdutoController {

	@Autowired
	ProdutoRepository produtorepository;

	// pesquisa por id
	@GetMapping(value = "produto/{id}")
	public ResponseEntity<?> searchById(@PathVariable int id) {
		Optional<Produto> ProdutoSalvo = produtorepository.findById(id);

		if (ProdutoSalvo.isPresent()) {
			return ResponseEntity.ok(ProdutoSalvo);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Esse ID não existe");
		}
	}

	// listagem de todos os produtos
	@GetMapping(value = "produto/list")
	public List<Produto> searchAll() {
		List<Produto> produtos = produtorepository.findAll();
		return produtos;
	}

	// cadastro de produto usando body do postman
	@PostMapping(value = "produto/cadastro")
	public ResponseEntity<?> cadastroProduto(@RequestBody Produto produto) {
		Produto produtos = new Produto(produto.getNome(), produto.getPreco(), produto.getQuantidade());
		produtorepository.save(produtos);

		Map<String, String> response = new HashMap<>();
		response.put("message", "Produto salvo com sucesso.");
		return ResponseEntity.ok(response);
	}

	// atualizar quantidade de produtos pelo id
	@PutMapping(value = "produto/update/{id}")
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
	@DeleteMapping(value = "produto/delete/{id}")
	public ResponseEntity<?> deletarProduto(@PathVariable int id) {
		Optional<Produto> ProdutoExistente = produtorepository.findById(id);

		if (ProdutoExistente.isPresent()) {
			produtorepository.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK).body("O produto foi deletado.");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Esse ID não existe");
		}
	}
}
