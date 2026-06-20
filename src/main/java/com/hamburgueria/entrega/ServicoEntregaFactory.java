package com.hamburgueria.entrega;

import com.hamburgueria.entrega.frete.FreteGratisAcimaDe;
import com.hamburgueria.entrega.frete.FretePorDistancia;
import com.hamburgueria.pedido.TipoEntrega;

public class ServicoEntregaFactory {

    public ServicoEntrega criar(TipoEntrega tipo) {
        switch (tipo) {
            case DELIVERY:
                return new ServicoDelivery(new FreteGratisAcimaDe(new FretePorDistancia()));
            case RETIRADA:
                return new ServicoRetirada();
            case DRIVE_THRU:
                return new ServicoDriveThru();
            case BALCAO:
                return new ServicoBalcao();
            default:
                throw new IllegalArgumentException("Tipo de entrega nao suportado: " + tipo);
        }
    }
}
