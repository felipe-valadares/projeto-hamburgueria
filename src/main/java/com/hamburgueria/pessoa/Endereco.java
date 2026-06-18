package com.hamburgueria.pessoa;

public class Endereco {

    private final String logradouro;
    private final String numero;
    private final String bairro;
    private final double distanciaKm;

    public Endereco(String logradouro, String numero, String bairro, double distanciaKm) {
        this.logradouro = logradouro;
        this.numero = numero;
        this.bairro = bairro;
        this.distanciaKm = distanciaKm;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public String getBairro() {
        return bairro;
    }

    public double getDistanciaKm() {
        return distanciaKm;
    }

    @Override
    public String toString() {
        return logradouro + ", " + numero + " - " + bairro;
    }
}
