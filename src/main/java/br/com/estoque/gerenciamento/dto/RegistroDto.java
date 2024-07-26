package br.com.estoque.gerenciamento.dto;

import br.com.estoque.gerenciamento.user.UserCargos;

public record RegistroDto (String login, String senha, UserCargos cargo){
}
