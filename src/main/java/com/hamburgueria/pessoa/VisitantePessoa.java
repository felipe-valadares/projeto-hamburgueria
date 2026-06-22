package com.hamburgueria.pessoa;

public interface VisitantePessoa {

    void visitar(Cliente cliente);

    void visitar(Funcionario funcionario);

    void visitar(Fornecedor fornecedor);
}
