package com.hamburgueria.relatorio;

import java.util.Set;

public class RelatorioGerencialProxy implements RelatorioGerencial {

    private static final Set<String> CARGOS_AUTORIZADOS = Set.of("gerente", "dono");

    private final String cargo;
    private final RelatorioGerencial relatorioReal;
    private String cache;

    public RelatorioGerencialProxy(String cargo, RelatorioGerencial relatorioReal) {
        this.cargo = cargo;
        this.relatorioReal = relatorioReal;
    }

    @Override
    public String gerar() {
        if (!CARGOS_AUTORIZADOS.contains(cargo.toLowerCase())) {
            throw new AcessoNegadoException(cargo);
        }
        if (cache == null) {
            cache = relatorioReal.gerar();
        }
        return cache;
    }
}
