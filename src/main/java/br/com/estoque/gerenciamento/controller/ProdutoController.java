package br.com.estoque.gerenciamento.controller;

import br.com.estoque.gerenciamento.dto.ProdutoRequestDto;
import br.com.estoque.gerenciamento.dto.ProdutoResponseDto;
import br.com.estoque.gerenciamento.model.Produto;
import br.com.estoque.gerenciamento.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository repository;

    @PostMapping
    public ResponseEntity<Void> criarProduto(@RequestBody ProdutoRequestDto dto) {
        Produto produto = new Produto(dto);
        repository.save(produto);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<ProdutoResponseDto>> listarProdutos() {
        List<ProdutoResponseDto> produtos = repository.findAll().stream().map(ProdutoResponseDto::new).collect(Collectors.toList());
        return ResponseEntity.ok(produtos);
    }//olha o video de "SpringBoot" tinha uma função de link entre o getId e o getAll, implementar

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponseDto> procurarProdutoPorId(@PathVariable Long id) {
        Optional<Produto> produtoOptional = repository.findById(id);
        if (produtoOptional.isPresent()) {
            ProdutoResponseDto produtoResponseDto = new ProdutoResponseDto(produtoOptional.get());
            return ResponseEntity.ok(produtoResponseDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizarProduto(@PathVariable Long id, @RequestBody ProdutoRequestDto dto) {
        Optional<Produto> produtoOptional = repository.findById(id);
        if (produtoOptional.isPresent()) {
            Produto produto = produtoOptional.get();
            produto.atualizarDados(dto); // Método que você deve implementar na classe Produto para atualizar os dados
            repository.save(produto);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
