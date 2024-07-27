package br.com.estoque.gerenciamento.user;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public enum UserCargos {
    ROLE_ADMIN("ADMIN"),
    ROLE_RH("RH"),
    ROLE_ESTOQUE("ESTOQUE");

    private final String cargo;

    UserCargos(String cargo) {
        this.cargo = cargo;
    }

    public String getCargo() {
        return cargo;
    }
}

