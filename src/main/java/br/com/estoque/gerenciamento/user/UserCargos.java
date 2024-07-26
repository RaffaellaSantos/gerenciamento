package br.com.estoque.gerenciamento.user;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public enum UserCargos{
    ADMIN("admin"),
    RH("rh"),
    //funções RH -> CRUD os usuários, gerencia a folha de pagamento (Salários, tempo de empresa, cargo), permissões (CRUD)
    ESTOQUE("estoque");
    //funções estoque -> Permissão somente ao CRUD dos produtos, não pode registrar, nem criar novos funcionários
    //financeiro
    //ADMIN

    private final String cargo;

    UserCargos(String cargo) {
        this.cargo = cargo;
    }

    public String getCargo() {
        return cargo;
    }
}
