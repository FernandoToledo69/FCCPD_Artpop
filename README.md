# Marketplace de Artesanato de Pernambuco (Artpop) — Projeto integrador

Essa é só uma parte teste para os requisitos da entrega da disciplina FCCPD de Jorginho. 

## Integrantes

| Integrante | Perfil | Foto |
| --- | --- | --- |
| Davi Lucas | [github.com/davi081dev](https://github.com/davi081dev) | [![Davi Lucas](https://github.com/davi081dev.png?size=96)](https://github.com/davi081dev) |
| Hugo Mendonça | [github.com/BRKHugz](https://github.com/BRKHugz) | [![Hugo Mendonça](https://github.com/BRKHugz.png?size=96)](https://github.com/BRKHugz) |
| Luiz Fernando Ramos de Toledo | [github.com/FernandoToledo69](https://github.com/FernandoToledo69) | [![Luiz Fernando Ramos de Toledo](https://github.com/FernandoToledo69.png?size=96)](https://github.com/FernandoToledo69) |
| Michel dos Santos Serpa | [github.com/serpamichel](https://github.com/serpamichel) | [![Michel dos Santos Serpa](https://github.com/serpamichel.png?size=96)](https://github.com/serpamichel) |

## Estrutura do projeto

```
marketplace-pernambuco/
├── src/marketplace/
│   ├── Estoque.java              -> Requisitos 1 e 2
│   ├── Pedido.java                -> Requisito 4
│   ├── FilaDePedidos.java         -> Requisito 3
│   ├── ProcessadorDePedidos.java  -> Requisitos 3, 4 e 5
│   ├── LogConfiavel.java          -> Requisito 5
│   └── Main.java                  -> Junta tudo em uma simulação
├── DECLARACAO_USO_IA.md          -> Requisito 6
└── README.md
```

## Como compilar e executar

Você precisa ter o **JDK** instalado (versão 11 ou superior). Para conferir:

```bash
java -version
javac -version
```

Depois, dentro da pasta `marketplace-pernambuco`:

```bash
mkdir -p bin
javac -d bin src/marketplace/*.java
java -cp bin marketplace.Main
```

Ao rodar, o programa vai:
1. Cadastrar 3 produtos artesanais com estoque pequeno de propósito;
2. Simular 20 "clientes" (threads) comprando ao mesmo tempo, disputando os
   mesmos produtos;
3. Processar os pedidos de forma assíncrona, um por vez, atualizando o
   estoque com segurança;
4. Imprimir o estoque final no console;
5. Gerar um arquivo `marketplace.log` na pasta onde o comando foi executado,
   com o histórico completo de tudo o que aconteceu.

