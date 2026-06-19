package com.hamburgueria.promocao.regra;

import com.hamburgueria.promocao.ContextoPromocao;

public interface ExpressaoRegra {

    boolean interpretar(ContextoPromocao contexto);
}
