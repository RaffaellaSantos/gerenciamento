package br.com.estoque.gerenciamento.dto;

import java.math.BigDecimal;

public record ProdutoRequestDto(String nome, String descricao, BigDecimal preco, String codigo, String fornecedor, String categoria, String validade, String image) {
}
