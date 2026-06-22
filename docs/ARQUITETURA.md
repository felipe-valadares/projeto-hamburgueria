# Documentacao Tecnica — Sistema de Gestao da Hamburgueria

## 1. Visao geral

O sistema modela a operacao de uma hamburgueria: montagem de produtos, cardapio,
combos, carrinho, pedidos, producao na cozinha, pagamento, entrega, promocoes,
notificacoes e relatorios gerenciais. O codigo esta sob `com.hamburgueria` e e
organizado **por modulos de negocio**, nao por camadas tecnicas nem por padroes de
projeto. Cada padrao surge de uma necessidade real do dominio e convive de forma
integrada com os demais.

## 2. Arquitetura adotada

A aplicacao segue uma organizacao modular de dominio. Cada pacote concentra um
conjunto coeso de responsabilidades e expoe interfaces estaveis para os demais:

| Modulo | Responsabilidade |
| ------ | ---------------- |
| `infraestrutura` | Parametros globais da loja e geracao de identificadores. |
| `pessoa` | Clientes, funcionarios, fornecedores e enderecos. |
| `cardapio` | Produtos, ingredientes, categorias e regras de composicao. |
| `cardapio.montagem` | Construcao de hamburgueres personalizados e adicionais. |
| `cardapio.combo` | Montagem de combos por perfil de cliente. |
| `pedido` | Carrinho, itens, ciclo de vida do pedido, comandos e historico. |
| `producao` | Fila da cozinha e roteiro de preparo por tipo de produto. |
| `promocao` | Cupons, regras de elegibilidade e aprovacao de descontos. |
| `pagamento` | Meios de pagamento e integracao com gateway externo. |
| `entrega` | Servicos de entrega e calculo de frete. |
| `notificacao` | Envio de avisos ao cliente em multiplos canais. |
| `operacao` | Coordenacao operacional e fachada de atendimento. |
| `relatorio` | Relatorios gerenciais e cadastrais. |

As dependencias fluem dos modulos de orquestracao (`operacao`) para os modulos de
dominio (`cardapio`, `pedido`, `pagamento`, `entrega`, ...), que por sua vez
dependem apenas de `pessoa` e `infraestrutura`. Nao ha dependencias ciclicas entre
modulos.

## 3. Principais decisoes de projeto

- **Valores monetarios em `BigDecimal`** com arredondamento `HALF_UP`, evitando erros
  de ponto flutuante em precos, frete e faturamento.
- **Estado imutavel sempre que possivel**: ingredientes, produtos e comprovantes sao
  imutaveis; o `Pedido` copia seus itens na construcao, garantindo isolamento entre
  pedidos (essencial para a repeticao de pedido).
- **Interfaces estreitas e coesas** (`MeioPagamento`, `CalculoFrete`, `CanalEnvio`,
  `ServicoEntrega`), permitindo trocar implementacoes sem afetar os clientes
  (princípios de inversao de dependencia e aberto/fechado).
- **Validacao apenas nas fronteiras** do dominio (montagem do hamburguer, fechamento
  do carrinho, transicoes de estado, autorizacao de desconto), com excecoes especificas.
- **Sem dependencias externas alem do JUnit 5**, mantendo o nucleo do dominio puro e
  facilmente testavel.

## 4. Responsabilidades por padrao e localizacao

Os 23 padroes GoF estao presentes. A tabela indica onde cada um vive e por que foi
empregado.

### Padroes de criacao

| Padrao | Localizacao | Justificativa |
| ------ | ----------- | ------------- |
| Singleton | `infraestrutura.Configuracao` | Parametros da loja (frete, raio de entrega, horario) precisam de um ponto unico e consistente de acesso. |
| Builder | `cardapio.montagem.HamburguerBuilder` | A montagem de um lanche personalizado tem muitos passos opcionais e regras de validacao (exige pao e proteina). |
| Abstract Factory | `cardapio.combo.FabricaCombo` e variacoes | Cada perfil de combo (infantil, adulto, vegano) produz uma familia coerente de lanche, bebida e acompanhamento. |
| Factory Method | `entrega.ServicoEntregaFactory` | O servico de entrega correto depende do tipo do pedido, isolando o cliente da escolha da classe concreta. |
| Prototype | `pedido.Pedido#repetir/clone` | "Repetir pedido" gera uma nova instancia com os mesmos itens, mas identidade e ciclo de vida proprios. |

### Padroes estruturais

| Padrao | Localizacao | Justificativa |
| ------ | ----------- | ------------- |
| Composite | `cardapio.Categoria` / `cardapio.ItemCardapio` | O cardapio e uma arvore de categorias e produtos; preco e calorias sao agregados recursivamente. |
| Decorator | `cardapio.montagem.AdicionalDecorator` e concretos | Adicionais (bacon, cheddar, ovo) acrescentam preco e descricao a um item em tempo de execucao e podem ser empilhados. |
| Flyweight | `cardapio.Ingrediente` + `IngredienteFactory` | Ingredientes sao compartilhados entre milhares de itens, evitando duplicacao de estado intrinseco. |
| Adapter | `pagamento.gateway.GatewayPagamentoAdapter` | Adapta a API do gateway externo (`autorizar`/`RetornoTransacao`) ao contrato interno `MeioPagamento`. |
| Bridge | `notificacao.Notificacao` x `notificacao.CanalEnvio` | O tipo de aviso (confirmado, pronto, saiu para entrega) varia independentemente do canal (SMS, e-mail, WhatsApp, push). |
| Facade | `operacao.AtendimentoFacade` | Oferece uma unica operacao de alto nivel que orquestra cardapio, pagamento, producao, entrega e notificacao. |
| Proxy | `relatorio.RelatorioGerencialProxy` | Controla o acesso a relatorios gerenciais (apenas gerencia) e armazena o resultado em cache. |

### Padroes comportamentais

| Padrao | Localizacao | Justificativa |
| ------ | ----------- | ------------- |
| Chain of Responsibility | `promocao.AprovadorDesconto` e concretos | Descontos sao aprovados de forma escalonada por valor (atendente, supervisor, gerente, dono). |
| Command | `pedido.comando.Comando` e concretos | Operacoes de cozinha sao encapsuladas, enfileiraveis e reversiveis (desfazer). |
| Interpreter | `promocao.regra.ExpressaoRegra` e concretos | Regras de elegibilidade de cupom sao expressas como uma pequena linguagem (subtotal e quantidade combinados por E/OU). |
| Iterator | `producao.FilaProducao` + `IteradorPorPrioridade` | A fila da cozinha e percorrida por prioridade (pedidos presenciais antes de delivery), com travessia propria. |
| Mediator | `operacao.CentralOperacoes` | Coordena cozinha, expedicao e atendimento sem que esses setores se conhecam diretamente. |
| Memento | `pedido.MementoCarrinho` + `HistoricoCarrinho` | Permite desfazer edicoes do carrinho preservando o encapsulamento do estado. |
| Observer | `pedido.evento.ObservadorPedido` / `Pedido` | Assinantes (historico, notificador) reagem automaticamente a cada mudanca de status. |
| State | `pedido.estado.EstadoPedido` e concretos | O ciclo de vida do pedido controla quais transicoes sao validas em cada situacao. |
| Strategy | `entrega.frete.CalculoFrete` e concretos | O calculo de frete possui varios algoritmos intercambiaveis (fixo, por distancia, cortesia, gratis acima de um valor). |
| Template Method | `producao.preparo.PreparoProduto` e concretos | O preparo segue um esqueleto fixo (separar, processar, montar, finalizar) especializado por tipo de produto. |
| Visitor | `relatorio.RelatorioContatos` / `RelatorioFolhaPagamento` | Operacoes de relatorio percorrem pessoas heterogeneas (cliente, funcionario, fornecedor) sem alterar essas classes. |

## 5. Integracao entre padroes

Os padroes nao vivem isolados; eles se combinam para resolver fluxos reais:

- **Ciclo de vida do pedido**: `State` controla as transicoes; `Observer` propaga as
  mudancas; `Command` torna as operacoes da cozinha reversiveis; `Memento` desfaz a
  edicao do carrinho; `Mediator` coordena os setores envolvidos.
- **Montagem do produto**: `Builder` constroi o lanche base; `Decorator` agrega
  adicionais; `Flyweight` compartilha os ingredientes; o resultado entra como item na
  arvore `Composite` do cardapio/pedido.
- **Promocoes**: `Interpreter` avalia a elegibilidade do cupom; `Chain of
  Responsibility` autoriza o desconto; `Strategy` define o frete aplicado.
- **Atendimento ponta a ponta**: a `Facade` aciona o `Factory Method` da entrega, o
  `Adapter`/`Strategy` do pagamento e frete, e delega ao `Mediator`, que usa o
  `Bridge` de notificacoes para avisar o cliente.

## 6. Qualidade e testes

A regras de negocio e o comportamento de cada padrao sao cobertos por testes JUnit 5
em `src/test/java`, espelhando a estrutura de pacotes do dominio. Os testes validam,
entre outros: validacao de montagem, agregacao recursiva de preco, compartilhamento de
flyweights, transicoes de estado validas e invalidas, desfazer de carrinho e comandos,
ordenacao por prioridade da fila, avaliacao de regras de cupom, escalonamento de
aprovacao, traducao do gateway de pagamento, calculo de frete, notificacao multicanal,
coordenacao pelo mediador, atendimento completo pela fachada e controle de acesso aos
relatorios.

Execucao:

```bash
mvn test
```

## 7. Diagramas

- `diagramas/diagrama-classes.puml` — diagrama de classes consolidado de todo o sistema.
- `diagramas/estado-pedido.puml` — ciclo de vida do pedido.
- `diagramas/estado-pagamento.puml` — ciclo de vida do pagamento.
- `diagramas/estado-producao.puml` — fluxo de preparo na cozinha.
- `diagramas/estado-entrega.puml` — fluxo de expedicao e entrega.
