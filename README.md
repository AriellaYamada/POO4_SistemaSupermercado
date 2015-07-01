# Sistema Supermercado

- Utilizamos o InteliJ para o desenvolvimento da aplicacao, baseado na versão 1.8.0_45 do Java.
- Por se tratar de uma aplicação com interface gráfica, para executar basta dar 2 cliques no arquivo `.jar` que está incluído na tag [`v1.0`](https://github.com/AriellaYamada/POO4_SistemaSupermercado/releases/tag/v1.0).
  - Se isso não for o suficiente, tente abrir o arquivo pela linha de comando, digitando
  
    `java -jar .\SistemaSupermercado.jar`

- Para gerar arquivos PDF, utilizamos a biblioteca iText (o `.jar` da biblioteca está presente na pasta do repositório).
- Todos os arquivos de registros .csv se encontrarão na pasta raiz do projeto (a mesma pasta deste README, se você estiver no repositório do Github). Se eles não existirem no início do programa, então estes serão criados automaticamente.
- Para a execução da aplicação do Cliente, é preciso que o Servidor esteja ligado.
- Ao iniciar a aplicação como Servidor, escolha uma porta válida para que o Cliente possa se conectar.
- Na aplicação do Cliente, digite o IP do servidor e selecione a mesma porta escolhida pela aplicação do Servidor.
- Todas as aplicações de Cliente devem estar conectadas à mesma rede da aplicacao do Servidor que estarão utilizando. (A não ser que você seja o Ades e consiga criar um IP Externo para a sua máquina)

#### Observações
- Todos os campos são validados, então se você digitar alguma informação inesperada o campo ficará marcado de vermelho. Posicione o mouse sobre o campo e uma mensagem irá aparecer, indicando o que está errado no campo.
- Um dos padrões de projeto utilizados no desenvolvimento foi o Singleton, usado principalmente para os arquivos `database` (que fazem a leitura e escrita nos arquivos) ou nos arquivos com o nome em plural (`Products`, `Users`...), para ser de fácil acesso fora da classe.
- Outro padrão brevemente utilizado foi o Observable, que é utilizado para a construção das tabelas de produtos, usuários e vendas, através da classe `ObservableList`.
- É possível aproveitar todas as funcionalidades do sistema através dos botões disponíveis nas telas, mas para tornar a navegação mais rápida, foi implementado o Duplo Clique nas tabelas, com uma operação padrão (Abrir detalhes da venda, adicionar ao carrinho, editar produto...).
