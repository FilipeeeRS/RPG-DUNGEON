package jogo;

import entidades.*;
import itens.Item;
import java.util.Scanner;
import utilidades.Dado;

// classe principal que contém o loop do jogo. Gerencia a navegação pela dungeon de 10 andares
public class Jogo {

    // Criamos o 'leitor' aqui para que possa ser usado em toda a classe
    private static Scanner leitor = new Scanner(System.in);
    private static Personagem jogador; // Guarda o jogador

    public static void main(String[] args) {

        // 1. Introdução
        iniciarHistoria();

        // 2. Criação do Personagem
        criarPersonagem();

        // 3. O Jogo Principal (Loop da Dungeon)
        iniciarDungeon();

        // 4. Fim de Jogo
        // mensagem final
        if (jogador.isEstaVivo()) {
            System.out.println("\nPARABÉNS! Você conquistou a Dungeon de 10 andares!");
        } else {
            System.out.println("\nFIM DE JOGO.");
        }

        leitor.close();
    }

    // história inicial
    public static void iniciarHistoria() {
        System.out.println("...");
        delay(800);
        System.out.println("...");
        delay(800);
        System.out.println("...");
        delay(800);
        System.out.println("Você acorda com o som de altas risadas e canecas batendo.");
        System.out.println("Você está na taverna 'O Javali Alado', o lugar mais barulhento da cidade.");
        pausar();
        System.out.println("\nDe repente, um estrondo!!! A porta do seu quarto é arrombada.");
        System.out.println("É o chefe dos aventureiros, ele parece estar estar ofegante.");
        pausar();
        System.out.println("\n\"Soldado! Já se recuperou?\"");
        System.out.println("\"Não temos tempo para descanso. Tenho uma missão para você.\"");
        System.out.println("\"A Dungeon da Perdição foi despertada.\"");
        System.out.println("\"São 10 níveis até o temido Boss, ele precisa ser derrotado.\"");
        System.out.println("\"Não temos tempo a perder, Vamos!!!\"");
        pausar();
        System.out.println("\nSua aventura começou.");
        pausar();
    }

    // pergunta o nome e a classe do jogador
    public static void criarPersonagem() {
        System.out.println("\n\"Esqueci de perguntar, qual é o seu nome?\"");
        String nomeJogador = "";
        // loop para que o nome não fique vazio, caso aperte enter
        while(nomeJogador.trim().isEmpty()) {
            System.out.print("Digite seu nome: ");
            nomeJogador = leitor.nextLine();
        }

        System.out.println("\n\"E qual é a sua classe?\"");
        System.out.println("(1) Guerreiro -> (Habilidade: ataque penetrante) (HP Alto, Defesa Alta, Ataque Baixo)");
        System.out.println("(2) Mago -> (Habilidade: ataque de veneno) (HP Baixo, Defesa Baixa, Ataque Alto)");
        System.out.println("(3) Arqueiro -> (Habilidade: ataque duplo) (HP Médio, Defesa Médio, Ataque Médio)");

        int classeEscolha = 0;
        while (classeEscolha < 1 || classeEscolha > 3) {
            System.out.print("Digite 1, 2 ou 3: ");
            try {classeEscolha = Integer.parseInt(leitor.nextLine().trim());
            } catch (Exception ignored) {}
        }

        if (classeEscolha == 1) {
            jogador = new Guerreiro(nomeJogador);
        } else if (classeEscolha == 2) {
            jogador = new Mago(nomeJogador);
        } else {
            jogador = new Arqueiro(nomeJogador);
        }

        System.out.println("\n" + jogador.getNome() + ", o " + jogador.getClass().getSimpleName() + ", sua jornada começa!");
        System.out.print("(ENTER para entrada na Dungeon)");
        leitor.nextLine();
    }

    public static void iniciarDungeon() {
        // loop Principal da Dungeon (10 andares)
        for (int andar = 1; andar <= 10; andar++) {
            System.out.println("\n----------------------------------------");
            System.out.println("Você está no ANDAR " + andar);
            System.out.println(jogador.getNome() + " | HP: " + jogador.getPontosVida());
            System.out.println("----------------------------------------");

            // lógica do Boss
            if (andar == 5 || andar == 10) {
                System.out.println("!!! UMA PRESENÇA ATERRORIZANTE BLOQUEIA O CAMINHO !!!");
                Boss boss;
                if (andar == 5) {
                    boss = new Boss("Guardião da Dungeon", 5, 200, 15, 10);
                } else {
                    boss = new Boss("SENHOR DA DUNGEON", 10, 400, 25, 15);
                }

                boolean vitoria = jogador.batalhar(boss);
                if (!vitoria) {
                    break; // Sai do loop principal se morrer
                }

            } else if (andar == 9) {
                // andar 9 descanso
                System.out.println("Você entra em uma sala silenciosa...");
                delay(3000);
                System.out.println("É uma fonte de descanso! Você foi totalmente curado.");

                // Cura o jogador ao hp máximo
                jogador.setPontosVida(jogador.getMaxPontosVida());

                System.out.println("Você também encontra uma Poção de Cura.");
                //jogador.getInventario().adicionar(new Item("Poção de Cura", "Cura 50 HP", "cura_50", 1));
                System.out.println("Agora que você recuperou as suas forças, está pronto para enfrentar o Boss final!");

            } else {
                // 3 caminhos lógica
                System.out.println("Você vê dois corredores idênticos. Qual você escolhe?");
                System.out.println("(1) corredor n° 1");
                System.out.println("(2) corredor n° 2");

                int escolha = 0;
                while (escolha < 1 || escolha > 2) {
                    System.out.print("Digite 1 ou 2: ");
                    try {escolha = Integer.parseInt(leitor.nextLine().trim());
                    } catch (Exception ignored) {}
                }

                // rola um d100 / 60% chance de inimigo / 25% chance de baú / 15% chance de descanso
                int rolagemEvento = Dado.rolar(100);

                if (rolagemEvento <= 65) {
                    // evento inimigo 65%
                    System.out.println("\nUm Inimigo aparece das sombras!");
                    Inimigo goblin = new Inimigo("Goblin", andar, 50 + (andar * 5), 8, 5);
                    if (!jogador.batalhar(goblin)) break; // Fim de jogo

                } else if (rolagemEvento <= 85) { // 65 + 20 = 85
                    // evento descanso 20%
                    System.out.println("\nÉ uma fonte de descanso! Você foi curado. +50");
                    int cura = 50;
                    jogador.setPontosVida(jogador.getPontosVida() + cura);

                } else {
                    // evento baú 15%
                    System.out.println("\nVocê tropeça em algo... é um baú!!! Que sortudo.");
                    //encontrarBau();
                }
            }

            // verifica se o jogador morreu
            if (!jogador.isEstaVivo()) {
                System.out.println("Sua jornada termina aqui...");
                break; // Sai do loop 'for'
            }

            // Não executa a pausa se o jogador acabou de vencer o boss final
            if (andar != 10) {
                System.out.print("(ENTER para avançar)");
                leitor.nextLine();
            }

        }
    }

    // função para pausar o diálogo e esperar o ENTER
    private static void pausar() {
        System.out.print("(ENTER para continuar)");
        leitor.nextLine();
    }

    // função para pausar a execução por x milissegundos
    private static void delay(int milissegundos) {
        try {
            Thread.sleep(milissegundos);
        } catch (InterruptedException e) {
            // não faz nada se interrompido
        }
    }
}