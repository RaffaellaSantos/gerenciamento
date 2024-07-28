package br.com.estoque.gerenciamento.controller;

import br.com.estoque.gerenciamento.dto.ProdutoRequestDto;
import br.com.estoque.gerenciamento.dto.ProdutoResponseDto;
import br.com.estoque.gerenciamento.model.Produto;
import br.com.estoque.gerenciamento.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    public ResponseEntity<String> criarProduto(@RequestBody ProdutoRequestDto dto) {
        Produto produto = new Produto(dto);
        repository.save(produto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Produto criado");
    }

    @GetMapping
    public ResponseEntity<List<ProdutoResponseDto>> listarProdutos() {
        List<ProdutoResponseDto> produtos = repository.findAll().stream().map(ProdutoResponseDto::new).collect(Collectors.toList());
        return ResponseEntity.ok(produtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> procurarProdutoPorId(@PathVariable Long id) {
        Optional<Produto> produtoOptional = repository.findById(id);
        if (produtoOptional.isPresent()) {
            ProdutoResponseDto produtoResponseDto = new ProdutoResponseDto(produtoOptional.get());
            return ResponseEntity.ok(produtoResponseDto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> atualizarProduto(@PathVariable Long id, @RequestBody ProdutoRequestDto dto) {
        Optional<Produto> produtoOptional = repository.findById(id);
        if (produtoOptional.isPresent()) {
            Produto produto = produtoOptional.get();
            produto.atualizarDados(dto);
            repository.save(produto);
            return ResponseEntity.ok("Produto atualizado com sucesso");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarProduto(@PathVariable Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.ok("Produto deletado com sucesso");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não econtrado");
        }
    }
}
