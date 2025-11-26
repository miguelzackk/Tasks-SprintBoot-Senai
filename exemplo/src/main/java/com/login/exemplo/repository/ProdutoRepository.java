package com.login.exemplo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.login.exemplo.entity.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

	Produto findByNome(String nome);

}
