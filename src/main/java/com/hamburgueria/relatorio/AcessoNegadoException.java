package com.hamburgueria.relatorio;

public class AcessoNegadoException extends RuntimeException {

    public AcessoNegadoException(String cargo) {
        super("O cargo '" + cargo + "' nao tem permissao para acessar relatorios gerenciais.");
    }
}
