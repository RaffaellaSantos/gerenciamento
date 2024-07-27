package br.com.estoque.gerenciamento.dto;

import br.com.estoque.gerenciamento.user.UserCargos;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuariosRequestDto {
    private String login;
    private String senha;
    private UserCargos cargo;

    public UsuariosRequestDto(String login, String senha, UserCargos cargo) {
        this.login = login;
        this.senha = senha;
        this.cargo = cargo;
    }
}

