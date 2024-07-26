package br.com.estoque.gerenciamento.dto;

import br.com.estoque.gerenciamento.model.Produto;

import java.math.BigDecimal;

public record ProdutoResponseDto(Long id, String nome, String descricao, BigDecimal preco, String codigo, String fornecedor, String categoria, String validade, String image) {
    public ProdutoResponseDto(Produto produto) {
        this(produto.getId(), produto.getNome(), produto.getDescricao(), produto.getPreco(), produto.getCodigo(), produto.getFornecedor(), produto.getCategoria(), produto.getValidade(), produto.getImage()) ;
    }
}
