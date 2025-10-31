package entidades;

import itens.Inventario;
import itens.Item;
import utilidades.Dado;
import java.util.Scanner;

// classe abstrata Personagem com atributos base
public abstract class Personagem {

    // atributos
    protected String nome;
    protected int pontosVida;
    protected int maxPontosVida;
    protected int ataque;
    public int Defesa;
    protected int nivel;
    protected Inventario inventario;
    protected boolean estaVivo;
    protected int xpAtual;
    protected int xpParaProximoNivel;

    // construtores
    public Personagem() {
        this.inventario = new Inventario();
        this.estaVivo = true;
        this.xpAtual = 0;
        this.xpParaProximoNivel = 100;
    }

    // construtor de cópia
    public Personagem(Personagem original) {
        this.nome = original.nome;
        this.pontosVida = original.pontosVida;
        this.maxPontosVida = original.maxPontosVida;
        this.ataque = original.ataque;
        this.Defesa = original.Defesa;
        this.nivel = original.nivel;
        this.estaVivo = original.estaVivo;
        this.inventario = new Inventario(original.inventario);
        this.xpAtual = original.xpAtual;
        this.xpParaProximoNivel = original.xpParaProximoNivel;
    }

    // getters e setters
    public String getNome() { return nome; }
    public int getPontosVida() { return pontosVida; }
    public int getMaxPontosVida() { return maxPontosVida; }

    public void setPontosVida(int pontosVida) {
        this.pontosVida = pontosVida;
        if (this.pontosVida > this.maxPontosVida) {
            this.pontosVida = this.maxPontosVida;
        }
    }

    public int getNivel() { return nivel; }
    public Inventario getInventario() { return inventario; }
    public boolean isEstaVivo() { return estaVivo; }
    public int getXpAtual() { return xpAtual; }
    public int getXpParaProximoNivel() { return xpParaProximoNivel; }


    // métod0 batalha
    public boolean batalhar(Inimigo inimigo) {
        Scanner leitor = new Scanner(System.in);
        System.out.println("--- BATALHA INICIADA ---");

        while (this.estaVivo && inimigo.estaVivo) {

            // turno do jogador
            System.out.println("--- Seu Turno ---");
            System.out.println(this.nome + " [HP: " + this.pontosVida + "/" + this.maxPontosVida + "] vs " + inimigo.nome + " [HP: " + inimigo.pontosVida + "/" + inimigo.maxPontosVida + "]");
            System.out.println("(1) Atacar");
            System.out.println("(2) Usar Item");
            System.out.println("(3) Tentar Fugir");

            int escolha = 0;
            while (escolha < 1 || escolha > 3) {
                System.out.print("Digite 1, 2 ou 3: ");
                try {
                    escolha = Integer.parseInt(leitor.nextLine().trim());
                } catch (Exception ignored) {}
            }

            if (escolha == 1) {
                System.out.println();
                this.atacar(inimigo);

            } else if (escolha == 2) {
                System.out.println(this.inventario.listarItens());


            } else if (escolha == 3) {
                // lógica de fuga
                if (inimigo instanceof Boss) {
                    System.out.println("\nVocê não pode fugir de um Boss.");
                    System.out.println();
                    continue;
                }

                // Vamos ajustar o texto:
                System.out.println("\n[AVISO] Tentar fugir é arriscado!");
                System.out.println("   Você precisa tirar 18 ou mais em um d20 para conseguir.");
                System.out.println("   • Sucesso = escapa e ganha 35 XP.");
                System.out.println("   • Falhar = perde o turno.");

                String confirmaFuga;
                while (true) {
                    System.out.print("Deseja tentar fugir? (s/n): ");
                    confirmaFuga = leitor.nextLine().trim().toLowerCase();
                    if (confirmaFuga.equals("s") || confirmaFuga.equals("n")) break;
                }

                if (confirmaFuga.equals("n")) {
                    System.out.println("Você não se arrisca.\n");
                    continue;
                }

                // tentativa de fuga
                System.out.println();
                if (tentarFugir()) {
                    System.out.println("Parabéns, você conseguiu fugir!");

                    int xpGanho = 35;
                    System.out.println(this.nome + " ganhou " + xpGanho + " XP!");
                    this.ganharXp(xpGanho);
                    return true; // fim da a batalha
                } else {
                    System.out.println("A fuga falhou! Você perdeu o turno.");
                }
            }

            if (!inimigo.estaVivo) break;

            // turno do inimigo
            if (inimigo.estaVivo) {
                System.out.println("\n--- Turno do " + inimigo.nome + " ---");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    // ignora
                }
                inimigo.atacar(this);
                System.out.println();
            }
        }

        // fim da Batalha
        if (this.estaVivo) {
            System.out.println("\nVOCÊ VENCEU A BATALHA!");

            int xpGanho;

            if (inimigo instanceof Boss) {
                xpGanho = 100;
            } else {
                xpGanho = 35;
            }

            System.out.println(this.nome + " ganhou " + xpGanho + " XP!");
            this.ganharXp(xpGanho);
            return true;
        } else {
            System.out.println("\nVOCÊ FOI DERROTADO.");
            return false;
        }
    }

    //Lógica de ataque
    public void atacar(Personagem oponente) {
        int rolagem = Dado.rolar(20);
        System.out.println("--- Ataque ---");
        System.out.println(this.nome + " rolou d" + rolagem + "!");

        if (rolagem == 1) {
            System.out.println("FALHA CRÍTICA!!! " + this.nome + " errou o ataque completamente.");
            return;
        }

        if (rolagem == 20) {
            System.out.println("ACERTO CRÍTICO!");
            int danoCritico = this.ataque + 10;
            oponente.receberDano(danoCritico);
            System.out.println(this.nome + " causou " + danoCritico + " de dano crítico!");
            return;
        }

        int forcaAtaque = this.ataque + rolagem;
        System.out.println(this.nome + " teve força de ataque: " + forcaAtaque);

        if (forcaAtaque > oponente.Defesa) {
            int dano = forcaAtaque - oponente.Defesa;
            oponente.receberDano(dano);
            System.out.println(this.nome + " causou " + dano + " de dano!");
        } else {
            System.out.println(this.nome + " errou o ataque (Defesa do oponente: " + oponente.Defesa + ")");
        }
    }

    // lógica para tentar fugir
    private boolean tentarFugir() {
        int rolagem = Dado.rolar(20);
        System.out.println(this.nome + " rolou " + rolagem + " para tentar fugir (precisa de 18+).");
        return (rolagem >= 18);
    }

    // lógica para receber dano
    public void receberDano(int dano) {
        this.pontosVida -= dano;
        if (this.pontosVida <= 0) {
            this.pontosVida = 0;
            this.estaVivo = false;
            System.out.println(this.nome + " foi derrotado.");
        }
    }

    // lógica para curar vida
        public void curar(int valor) {
        this.setPontosVida(this.pontosVida + valor);
            System.out.println(this.nome + " recuperou " + valor + " de HP! [HP: " + this.pontosVida + "/" + this.maxPontosVida + "]");
        }

    // adiciona XP ao personagem e verifica se ele subiu de nível. param quantidade A quantidade de XP ganho
    public void ganharXp(int quantidade) {
        this.xpAtual += quantidade;
        System.out.println(this.nome + " está com " + this.xpAtual + "/" + this.xpParaProximoNivel + " XP.");
        checarSeSubiuDeNivel();
    }

    // XP atual e o xp necessário para subir de nível
    private void checarSeSubiuDeNivel() {
        while (this.xpAtual >= this.xpParaProximoNivel) {
            this.xpAtual -= this.xpParaProximoNivel;
            subirNivel();
        }
    }


    // aplica os bônus de status e cura ao subir de nível
    public void subirNivel() {
        this.nivel++;
        System.out.println("\n--- LEVEL UP! ---");
        System.out.println(this.nome + " subiu para o nível " + this.nivel + "!");

        // aumentar atributos
        int bonusVida = 40;
        int bonusAtaque = 10;
        int bonusDefesa = 8;

        this.maxPontosVida += bonusVida;
        this.ataque += bonusAtaque;
        this.Defesa += bonusDefesa;

        System.out.println("Vida Máxima: +" + bonusVida + " (Total: " + this.maxPontosVida + ")");
        System.out.println("Ataque: +" + bonusAtaque + " (Total: " + this.ataque + ")");
        System.out.println("Defesa: +" + bonusDefesa + " (Total: " + this.Defesa + ")");

        // curar
        this.curar(50);
        System.out.println("-----------------\n");
    }

    @Override
    public String toString() {
        return this.nome + " (Nível: " + this.nivel + ", HP: " + this.pontosVida + "/" + this.maxPontosVida + ", XP: " + this.xpAtual + "/" + this.xpParaProximoNivel + ")";
    }
}