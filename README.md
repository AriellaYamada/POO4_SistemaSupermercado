# Sistema Supermercado

## TODO
- [ ] Validar se os campos preenchidos pelo usuário contém vírgulas (por ser um arquivo CSV é importante que o valor do campo NÃO tenha vírgulas). Opção 1: Não permitir vírgulas. Opção 2: Exibir como vírgulas para o usuário, porém gravar com algum outro caractere.
- [ ] Na aplicação cliente, fazer com que o "usuário" seja um singleton.
- [ ] Ao fazer login, o servidor deve retornar PELO MENOS o nome do usuário também. Ele será incluído no "menu.fxml". Já está indicado onde deve ser colocado o nome no arquivo "menuController.java"
- [ ] Ao fazer login, o servidor deve retornar os produtos reservados do cliente. Opção: Os produtos só ficam reservados enquanto o cliente está conectado.

## A decidir ainda
- Como deve ser guardada a reserva de produtos?
  - [ ] Todos em um arquivo só
  - [ ] Cada cliente com seu arquivo

## Se der tempo
- [ ] Excluir a "reserva" do produto se o cliente demorar mais do que "X Tempo" para concluir a compra (12h, 24h, 48h, 1 semana...)
- [ ] Permitir pesquisar produtos
- [x] Ordenar produtos por uma das colunas ao clicar no cabeçalho da coluna
- [ ] Na tela "productList.fxml", fazer o botão do carrinho exibir a quantidade de itens no carrinho.
