package com.hamburgueria.pessoa;

import java.math.BigDecimal;

public class Funcionario implements Pessoa {

    private final String nome;
    private final String cargo;
    private final String matricula;
    private final BigDecimal salario;

    public Funcionario(String nome, String cargo, String matricula, BigDecimal salario) {
        this.nome = nome;
        this.cargo = cargo;
        this.matricula = matricula;
        this.salario = salario;
    }

    @Override
    public String getNome() {
        return nome;
    }

    public String getCargo() {
        return cargo;
    }

    public String getMatricula() {
        return matricula;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    @Override
    public void aceitar(VisitantePessoa visitante) {
        visitante.visitar(this);
    }
}
