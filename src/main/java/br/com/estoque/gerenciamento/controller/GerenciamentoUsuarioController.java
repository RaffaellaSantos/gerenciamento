package br.com.estoque.gerenciamento.controller;


import br.com.estoque.gerenciamento.dto.RegistroDto;
import br.com.estoque.gerenciamento.dto.UsuariosRequestDto;
import br.com.estoque.gerenciamento.dto.UsuariosResponseDto;
import br.com.estoque.gerenciamento.repository.UserRepository;
import br.com.estoque.gerenciamento.user.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/controleUsuarios")
public class GerenciamentoUsuarioController {

    @Autowired
    private UserRepository repository;

    @PostMapping("/registro")
    public ResponseEntity<String> registro(@RequestBody @Valid RegistroDto dto) {
        if (this.repository.findByLogin(dto.login()) != null) {
            return ResponseEntity.badRequest().build();
        }

        String senhaCrypto = new BCryptPasswordEncoder().encode(dto.senha());
        User novoUser = new User(dto.login(), senhaCrypto, dto.cargo());

        this.repository.save(novoUser);
        return ResponseEntity.status(HttpStatus.CREATED).body("Usuário registrado");
    }

    @GetMapping
    public ResponseEntity<List<UsuariosResponseDto>> listarUsuarios(){
        List<UsuariosResponseDto> usuarios = repository.findAll().stream().map(UsuariosResponseDto::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> procurarUsuario(@PathVariable String id){
        Optional<User> usuario = repository.findById(id);
        if (usuario.isPresent()) {
            UsuariosResponseDto usuarioDto = new UsuariosResponseDto(usuario.get());
            return ResponseEntity.ok().body(usuarioDto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> atualizarUsuário(@PathVariable String id, @RequestBody UsuariosRequestDto dto){
        Optional<User> usuario = repository.findById(id);
        if (usuario.isPresent()) {
            User user = usuario.get();
            user.setLogin(dto.getLogin());
            if (dto.getSenha() != null && !dto.getSenha().isEmpty()) {
                String senhaCrypto = new BCryptPasswordEncoder().encode(dto.getSenha());
                user.setSenha(senhaCrypto);
            }
            if (dto.getCargo() != null) {
                user.setCargo(dto.getCargo());
            }
            repository.save(user);
            return ResponseEntity.ok("Usuário Atualizado");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarUsuario(@PathVariable String id) {
        Optional<User> usuario = repository.findById(id);
        if (usuario.isPresent()) {
            repository.delete(usuario.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
        }
    }
}