package br.com.estoque.gerenciamento.model;

import br.com.estoque.gerenciamento.dto.ProdutoRequestDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Table(name = "produtos")
@Entity(name = "produtos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Produto {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private String codigo;
    private String fornecedor;
    private String categoria;
    private String validade;
    private String image;

    public Produto(ProdutoRequestDto dto) {
        this.nome = dto.nome();
        this.descricao = dto.descricao();
        this.preco = dto.preco();
        this.codigo = dto.codigo();
        this.fornecedor = dto.fornecedor();
        this.categoria = dto.categoria();
        this.validade = dto.validade();
        this.image = dto.image();
    }

    public void atualizarDados(ProdutoRequestDto dto) {
        this.nome = dto.nome();
        this.preco = dto.preco();
        this.descricao = dto.descricao();
        this.codigo = dto.codigo();
        this.fornecedor = dto.fornecedor();
        this.categoria = dto.categoria();
        this.validade = dto.validade();
        this.image = dto.image();
    }

}
