# RPG DUNGEON

Primeiro trabalho da disciplina "PARADIGMA E PROGRAMAÇÃO ORIENTADA À OBJETOS".

Tema: Jogo de RPG com interface em texto em JAVA.

-------------------------------

## Integrantes
* Filipe Ribeiro Simões - RA: 24007657
* Rafael Roveri Pires - RA: 24007131

## Orientador
* Prof. André Luis dos Reis de Carvalho

-------------------------------

## Sobre o Jogo

Este projeto é um RPG de console (text-based) que implementa os conceitos fundamentais de Programação Orientada a Objetos, como Herança, Polimorfismo e Encapsulamento.

O jogador funciona enfrentando inimigos em um sistema de turnos.

## Funcionalidades Implementadas

* Sistema de Batalha por Turnos
* Estrutura de Classes com Herança (Personagem, Inimigos, Boss)
* Sistema de Ataque baseado em d20 (com crítico)
* Sistema de Experiência XP
* Sistema de Level Up com aumento de atributos
* Mecânica de Fuga de alto risco
* Sistema de intem
* Sistema de iventário

## Como Executar

1.  Clone o repositório.
2.  Abra o projeto em uma IDE Java (recomendado IntelliJ).
3.  Localize a classe `Jogo.java` (dentro do pacote `jogo`).
4.  Execute o método `main()` para iniciar o jogo no console.

## Estrutura

* `entidades`: Contém as classes de todos os seres do jogo (Personagem, Guerreiro, Inimigo, Boss).
* `itens`: Contém as classes `Item` e `Inventario` (funcionalidade futura).
* `jogo`: Contém a classe `Jogo`, responsável pelo loop principal.
* `utilidades`: Dado
