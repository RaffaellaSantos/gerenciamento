package br.com.estoque.gerenciamento.dto;


import br.com.estoque.gerenciamento.user.User;

public record UsuariosResponseDto(String id, String login, String cargo) {
    public UsuariosResponseDto(User user){
        this(user.getId(), user.getLogin(), user.getCargo().getCargo());
    }
}
