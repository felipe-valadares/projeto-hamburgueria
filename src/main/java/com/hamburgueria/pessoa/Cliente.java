package com.hamburgueria.pessoa;

public class Cliente implements Pessoa {

    private final String nome;
    private final String telefone;
    private final String email;
    private Endereco endereco;

    public Cliente(String nome, String telefone, String email) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
    }

    @Override
    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    @Override
    public void aceitar(VisitantePessoa visitante) {
        visitante.visitar(this);
    }
}
