package com.hamburgueria.infraestrutura;

import java.math.BigDecimal;
import java.time.LocalTime;

public final class Configuracao {

    private static Configuracao instancia;

    private String nomeLoja;
    private BigDecimal freteBase;
    private BigDecimal valorPorQuilometro;
    private BigDecimal pedidoMinimoFreteGratis;
    private double raioEntregaKm;
    private LocalTime abertura;
    private LocalTime fechamento;

    private Configuracao() {
        this.nomeLoja = "Brasa Burger";
        this.freteBase = new BigDecimal("6.00");
        this.valorPorQuilometro = new BigDecimal("1.50");
        this.pedidoMinimoFreteGratis = new BigDecimal("80.00");
        this.raioEntregaKm = 8.0;
        this.abertura = LocalTime.of(18, 0);
        this.fechamento = LocalTime.of(23, 30);
    }

    public static synchronized Configuracao getInstancia() {
        if (instancia == null) {
            instancia = new Configuracao();
        }
        return instancia;
    }

    public boolean estaAberta(LocalTime horario) {
        return !horario.isBefore(abertura) && !horario.isAfter(fechamento);
    }

    public String getNomeLoja() {
        return nomeLoja;
    }

    public void setNomeLoja(String nomeLoja) {
        this.nomeLoja = nomeLoja;
    }

    public BigDecimal getFreteBase() {
        return freteBase;
    }

    public void setFreteBase(BigDecimal freteBase) {
        this.freteBase = freteBase;
    }

    public BigDecimal getValorPorQuilometro() {
        return valorPorQuilometro;
    }

    public void setValorPorQuilometro(BigDecimal valorPorQuilometro) {
        this.valorPorQuilometro = valorPorQuilometro;
    }

    public BigDecimal getPedidoMinimoFreteGratis() {
        return pedidoMinimoFreteGratis;
    }

    public void setPedidoMinimoFreteGratis(BigDecimal pedidoMinimoFreteGratis) {
        this.pedidoMinimoFreteGratis = pedidoMinimoFreteGratis;
    }

    public double getRaioEntregaKm() {
        return raioEntregaKm;
    }

    public void setRaioEntregaKm(double raioEntregaKm) {
        this.raioEntregaKm = raioEntregaKm;
    }

    public LocalTime getAbertura() {
        return abertura;
    }

    public LocalTime getFechamento() {
        return fechamento;
    }
}
