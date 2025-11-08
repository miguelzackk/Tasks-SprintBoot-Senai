package com.login.exemplo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.login.exemplo.entity.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

	Produto findByNome(String nome);

}
