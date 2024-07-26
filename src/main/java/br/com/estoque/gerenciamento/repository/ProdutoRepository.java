package br.com.estoque.gerenciamento.repository;


import br.com.estoque.gerenciamento.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
