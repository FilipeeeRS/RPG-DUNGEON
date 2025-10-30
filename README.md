# RPG DUNGEON

Primeiro trabalho da disciplina "PARADIGMA E PROGRAMAÇÃO ORIENTADA À OBJETOS".

**Tema:** Jogo de RPG com interface em texto em JAVA.

-------------------------------

## Integrantes
* Filipe Ribeiro Simões - RA: 24007657
* Rafael Roveri Pires - RA: 24007131

## Orientador
* Prof. André Luis dos Reis de Carvalho

-------------------------------

## Sobre o Jogo

Este projeto é um RPG de console (text-based) que implementa os conceitos fundamentais de Programação Orientada a Objetos, como Herança, Polimorfismo e Encapsulamento.

O jogador embarca em uma jornada, enfrentando inimigos em um sistema de batalha por turnos.

## Funcionalidades Implementadas

* **Sistema de Batalha por Turnos:** O jogador pode escolher entre Atacar, Usar Item ou Tentar Fugir.
* **Classes e Herança:** A classe abstrata `Personagem` serve como base para as classes jogáveis (`Guerreiro`, `Mago`, `Arqueiro`) e para os `Inimigos` (incluindo a subclasse `Boss`).
* **Lógica de Ataque (d20):** Os ataques são decididos rolando um d20.
    * **Rolagem 1:** Falha Crítica (erra o ataque).
    * **Rolagem 20:** Acerto Crítico (dano aumentado).
* **Sistema de Experiência (XP):**
    * Vitória contra Inimigo Normal: **35 XP**
    * Vitória contra Boss: **100 XP**
    * Fuga bem-sucedida: **35 XP**
* **Sistema de Level Up (Fixo):**
    * A cada **100 XP**, o jogador sobe de nível.
    * Ao subir de nível, o jogador ganha bônus em `maxPontosVida`, `ataque`, `Defesa` e é curado em **50 HP**.
* **Mecânica de Fuga (Alto Risco):**
    * O jogador precisa rolar **18 ou mais** em um d20 para escapar.
    * Se falhar, perde o turno.
    * Não é possível fugir de um `Boss`.

## Como Executar

1.  Clone o repositório.
2.  Abra o projeto em uma IDE Java (como IntelliJ ou Eclipse).
3.  Localize a classe `Jogo.java` (dentro do pacote `jogo`).
4.  Execute o método `main()` para iniciar o jogo no console.

## Estrutura dos Pacotes

* `entidades`: Contém as classes de todos os seres do jogo (Personagem, Guerreiro, Inimigo, Boss).
* `itens`: Contém as classes `Item` e `Inventario` (funcionalidade futura).
* `jogo`: Contém a classe `Jogo`, responsável pelo loop principal.
* `utilidades`: Cont
