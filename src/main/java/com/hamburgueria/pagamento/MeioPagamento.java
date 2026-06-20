package com.hamburgueria.pagamento;

import java.math.BigDecimal;

public interface MeioPagamento {

    ComprovantePagamento cobrar(BigDecimal valor);
}
