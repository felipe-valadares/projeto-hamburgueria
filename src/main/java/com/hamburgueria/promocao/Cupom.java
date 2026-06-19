package com.hamburgueria.promocao;

import com.hamburgueria.promocao.regra.ExpressaoRegra;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Cupom {

    private final String codigo;
    private final BigDecimal percentual;
    private final ExpressaoRegra elegibilidade;

    public Cupom(String codigo, BigDecimal percentual, ExpressaoRegra elegibilidade) {
        this.codigo = codigo;
        this.percentual = percentual;
        this.elegibilidade = elegibilidade;
    }

    public String getCodigo() {
        return codigo;
    }

    public boolean isAplicavel(ContextoPromocao contexto) {
        return elegibilidade.interpretar(contexto);
    }

    public BigDecimal calcularDesconto(ContextoPromocao contexto) {
        if (!isAplicavel(contexto)) {
            return BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
        }
        return contexto.getSubtotal().multiply(percentual).setScale(2, RoundingMode.HALF_UP);
    }
}
