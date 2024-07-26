package br.com.estoque.gerenciamento.controller;

import br.com.estoque.gerenciamento.dto.AutenticacaoDto;
import br.com.estoque.gerenciamento.dto.RegistroDto;
import br.com.estoque.gerenciamento.repository.UserRepository;
import jakarta.validation.Valid;
import br.com.estoque.gerenciamento.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager authenticationManager;
//    @Autowired
//    private TokenService tokenService;

    @Autowired
    private UserRepository repository;


    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AutenticacaoDto dto){
        var usernameSenha = new UsernamePasswordAuthenticationToken(dto.login(), dto.senha());
        var auth = this.authenticationManager.authenticate(usernameSenha);
        return ResponseEntity.ok().build();
//        var token = tokenService.gerarToken((User)auth.getPrincipal());
//
//        return ResponseEntity.ok(new LoginResponseDto(token));
    }

    @PostMapping("/registro")
    public ResponseEntity<Void> registro(@RequestBody @Valid RegistroDto dto) {
        if (this.repository.findByLogin(dto.login()) != null) {
            return ResponseEntity.badRequest().build();
        }

        String senhaCrypto = new BCryptPasswordEncoder().encode(dto.senha());
        User novoUser = new User(dto.login(), senhaCrypto, dto.cargo());

        this.repository.save(novoUser);
        return ResponseEntity.ok().build();
    }




}
