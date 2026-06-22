package com.hamburgueria.pessoa;

public class Fornecedor implements Pessoa {

    private final String razaoSocial;
    private final String cnpj;
    private final String insumoFornecido;

    public Fornecedor(String razaoSocial, String cnpj, String insumoFornecido) {
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
        this.insumoFornecido = insumoFornecido;
    }

    @Override
    public String getNome() {
        return razaoSocial;
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getInsumoFornecido() {
        return insumoFornecido;
    }

    @Override
    public void aceitar(VisitantePessoa visitante) {
        visitante.visitar(this);
    }
}
