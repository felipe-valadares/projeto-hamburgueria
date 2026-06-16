# Sistema de Gestao da Hamburgueria

Aplicacao de dominio para a gestao de uma hamburgueria: montagem de produtos,
cardapio, combos, carrinho, pedidos, producao, pagamento, entrega, promocoes,
notificacoes e relatorios gerenciais.

O projeto e organizado por **modulos de negocio** (cardapio, pedido, producao,
pagamento, entrega, promocao, notificacao, operacao, relatorio), e nao por
camadas tecnicas ou por padroes de projeto. Os padroes de projeto aparecem como
consequencia natural das regras do dominio. A descricao da arquitetura e a
localizacao de cada padrao estao em [`docs/ARQUITETURA.md`](docs/ARQUITETURA.md).

## Requisitos

- Java 17+
- Maven 3.8+

## Compilar e testar

```bash
mvn test
```

## Estrutura

```
src/main/java/com/hamburgueria
  infraestrutura/   configuracao e utilitarios compartilhados
  pessoa/           clientes, funcionarios e fornecedores
  cardapio/         produtos, ingredientes, montagem de lanches e combos
  pedido/           carrinho, ciclo de vida do pedido e comandos
  producao/         fila e preparo na cozinha
  promocao/         cupons, regras de elegibilidade e aprovacao de descontos
  pagamento/        meios de pagamento e integracao com gateway externo
  entrega/          servicos de entrega e calculo de frete
  notificacao/      envio de avisos ao cliente em multiplos canais
  operacao/         coordenacao operacional e atendimento
  relatorio/        relatorios gerenciais

diagramas/          diagrama de classes consolidado e diagramas de estado
docs/               documentacao tecnica
```
