package jogo;

import entidades.*;
import itens.Item;
import java.util.Scanner;
import utilidades.Dado;
import itens.HealPot;
import itens.Granada;
import itens.Escudo;


public class Jogo {

    private static Scanner leitor = new Scanner(System.in);
    private static Personagem jogador;

    public static void main(String[] args) {

        // 1 introdução
        iniciarHistoria();

        // 2 criação do Personagem
        criarPersonagem();

        // 3 jogo principal (loop dos andares)
        iniciarDungeon();

        // 4. Fim de Jogo
        if (jogador.isEstaVivo()) {
            System.out.println("PARABÉNS! Você conquistou a Dungeon de 10 andares!");
            System.out.println("          ___________");
            System.out.println("         '._==_==_=_.'");
            System.out.println("         .-\\:      /-.");
            System.out.println("        | (|:.     |) |");
            System.out.println("         '-|:.     |-'");
            System.out.println("           \\::.    /");
            System.out.println("            '::. .'");
            System.out.println("              ) (");
            System.out.println("            _.' '._");
            System.out.println("           `\"\"\"\"\"\"\"`");
        } else {
            System.out.println("\nFIM DE JOGO.");
        }
        leitor.close();
    }

    // história
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

    // nome e a classe
    public static void criarPersonagem() {
        System.out.println("\n\"Esqueci de perguntar, qual é o seu nome?\"");
        String nomeJogador = "";
        // loop para ficar vazio se apertar enter
        while(nomeJogador.trim().isEmpty()) {
            System.out.print("Digite seu nome: ");
            nomeJogador = leitor.nextLine();
        }

        System.out.println("\n\"E qual é a sua classe?\"");
        System.out.println("(1) Guerreiro -> (Habilidade: escudo impenetrável) (HP Alto, Defesa Alta, Ataque Baixo)");
        System.out.println("(2) Mago -> (Habilidade: bola de fogo) (HP Baixo, Defesa Baixa, Ataque Alto)");
        System.out.println("(3) Arqueiro -> (Habilidade: tiro duplo) (HP Médio, Defesa Médio, Ataque Médio)");

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
        int xpPorEvento = 35;
        // loop principal da dungeon (10 andares)
        for (int andar = 1; andar <= 10; andar++) {
            System.out.println("\n----------------------------------------");
            System.out.println("Você está no ANDAR " + andar);
            System.out.println(jogador.getNome() + " | HP: " + jogador.getPontosVida());
            System.out.println("----------------------------------------");

            // lógica do Boss
            if (andar == 5 || andar == 10) {
                System.out.println("...");
                delay(1000);
                System.out.println("...");
                delay(1000);
                System.out.println("...");
                delay(1000);
                System.out.println("!!! UMA PRESENÇA ATERRORIZANTE BLOQUEIA O CAMINHO !!!");
                Boss boss;
                if (andar == 5) {
                    System.out.println("É o Guardião da Dungeon, o soldado mais poderoso e leal ao Mestre!");
                    pausar();
                    System.out.println();
                    boss = new Boss("Guardião da Dungeon", 5, 250, 15, 10);
                } else {
                    System.out.println("É ele... finalmente você encontra o temido \"SENHOR DA DUNGEON\"!");
                    pausar();
                    System.out.println();
                    boss = new Boss("SENHOR DA DUNGEON", 10, 500, 25, 15);
                }

                boolean vitoria = jogador.batalhar(boss);
                if (!vitoria) {
                    break; // morreu
                }

            } else if (andar == 9) {
                // andar 9 descanso
                System.out.println("Você entra em uma sala silenciosa...");
                delay(2000);
                System.out.println("Ufa, é uma fonte de descanso! Você foi totalmente curado. +" + xpPorEvento + " XP!");
                jogador.ganharXp(xpPorEvento);

                // cura máxima
                jogador.setPontosVida(jogador.getMaxPontosVida());

                System.out.println("Você também encontra uma Poção de Cura.");
                //jogador.getInventario().adicionar(new Item("Poção de Cura", "Cura 50 HP", "cura_50", 1));
                //TODO TODO TODO

            } else {
                // 2 caminhos lógica
                System.out.println("Você vê dois corredores idênticos. Qual você escolhe?");
                System.out.println("(1) corredor n° 1");
                System.out.println("(2) corredor n° 2");

                int escolha = 0;
                while (escolha < 1 || escolha > 2) {
                    System.out.print("Digite 1 ou 2: ");
                    try {escolha = Integer.parseInt(leitor.nextLine().trim());
                    } catch (Exception ignored) {}
                }

                // rola um d100 / 80% chance de inimigo / 20% chance de descanso
                int rolagemEvento = Dado.rolar(100);

                if (rolagemEvento <= 80) {
                    // inimigo 80%
                    System.out.println("\nUm Inimigo aparece das sombras!");
                    Inimigo goblin = new Inimigo("Goblin", andar, 50 + (andar * 5), 8, 5);
                    boolean venceu = jogador.batalhar(goblin);
                    if (!venceu) break; // morreu → fim do jogo

                    // 35% de chance de vir baú após vitoria
                    int chanceBau = Dado.rolar(100); // 1 a 100

                    if (chanceBau <= 35) { // 35% de chance
                        System.out.println("\n O inimigo deixou cair um BAÚ!");

                        int sorteioItem = Dado.rolar(3); // 1,2,3

                        if (sorteioItem == 1) {
                            jogador.getInventario().adicionar(new HealPot(1));
                            System.out.println("Você encontrou: Poção de Cura x1!");
                        } else if (sorteioItem == 2) {
                            jogador.getInventario().adicionar(new Granada(1));
                            System.out.println("Você encontrou: Granada x1!");
                        } else {
                            jogador.getInventario().adicionar(new Escudo(1));
                            System.out.println("Você encontrou: Escudo x1!");
                        }

                        System.out.println("Item adicionado ao seu inventário!\n");
                    } else {
                        System.out.println("\nO inimigo não deixou nada para trás.\n");
                    }


                } else (rolagemEvento <= 100) { // 80 + 20 = 100
                    // descanso 20%
                    System.out.println("\nÉ uma fonte de descanso! Você foi curado. +100 HP +" + xpPorEvento + " XP!");
                    int cura = 100;
                    jogador.setPontosVida(jogador.getPontosVida() + cura);
                    jogador.ganharXp(xpPorEvento);

                }
            }

            // verifica se o jogador morreu
            if (!jogador.isEstaVivo()) {
                System.out.println("Sua jornada termina aqui...");
                break; // Sai do loop 'for'
            }

            // não executa se zerou
            if (andar != 10) {
                System.out.print("(ENTER para avançar)");
                leitor.nextLine();
            }

        }
    }

    // função para pausar o diálogo
    private static void pausar() {
        System.out.print("(ENTER para continuar)");
        leitor.nextLine();
    }

    // função para pausar por x segundos
    private static void delay(int milissegundos) {
        try {
            Thread.sleep(milissegundos);
        } catch (InterruptedException e) {
            // ignora
        }
    }
}