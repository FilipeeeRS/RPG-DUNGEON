package entidades;

import itens.Inventario;
import itens.Item;
import utilidades.Dado;
import java.util.Scanner;

// classe abstrata Personagem
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
    protected boolean habilidadeUsadaNestaBatalha;
    public abstract void usarHabilidadeEspecial(Inimigo inimigo);

    // construtores
    public Personagem() {
        this.inventario = new Inventario();
        this.estaVivo = true;
        this.xpAtual = 0;
        this.xpParaProximoNivel = 100;
        this.habilidadeUsadaNestaBatalha = false;
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
        this.habilidadeUsadaNestaBatalha = original.habilidadeUsadaNestaBatalha;
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

        this.habilidadeUsadaNestaBatalha = false;
        int defesaOriginalBatalha = this.Defesa;

        while (this.estaVivo && inimigo.estaVivo) {
            // turno do jogador
            System.out.println("--- Seu Turno ---");
            System.out.println(this.nome + " [HP: " + this.pontosVida + "/" + this.maxPontosVida + "] vs " + inimigo.nome + " [HP: " + inimigo.pontosVida + "/" + inimigo.maxPontosVida + "]");
            System.out.println("(1) Atacar");
            System.out.println("(2) Habilidade Especial");
            System.out.println("(3) Usar Item");
            System.out.println("(4) Tentar Fugir");

            int escolha = 0;
            while (escolha < 1 || escolha > 4) {
                System.out.print("Digite 1, 2, 3 ou 4: ");
                try {
                    escolha = Integer.parseInt(leitor.nextLine().trim());
                } catch (Exception ignored) {}
            }

            if (escolha == 1) {
                System.out.println();
                this.atacar(inimigo);

            } else if (escolha == 2) {
                if (this.habilidadeUsadaNestaBatalha) {
                    System.out.println("\nVocê já usou sua habilidade nesta batalha!\n");
                    continue; // volta sem perder o turno
                }
                System.out.println("\n--- HABILIDADE ESPECIAL ---");

                // descrição
                if (this instanceof Guerreiro) {
                    System.out.println("[ESCUDO DE IMPENETRÁVEL]: Aumenta sua Defesa em 10 nessa batalha.");
                } else if (this instanceof Mago) {
                    System.out.println("[BOLA DE FOGO]: Causa dano elevado.");
                } else if (this instanceof Arqueiro) {
                    System.out.println("[TIRO DUPLO]: Ataca 2 vezes.");
                } else {
                    continue;
                }
                System.out.println("(Só pode ser usada 1 vez por batalha)");

                // confirmação
                String confirmaHabilidade;
                while (true) {
                    System.out.print("Deseja usar esta habilidade? (s/n): ");
                    confirmaHabilidade = leitor.nextLine().trim().toLowerCase();
                    if (confirmaHabilidade.equals("s") || confirmaHabilidade.equals("n")) break;
                }

                // "n" volta e n gasta turno
                if (confirmaHabilidade.equals("n")) {
                    System.out.println();
                    continue;
                }

                // "s" usa
                this.usarHabilidadeEspecial(inimigo);
                this.habilidadeUsadaNestaBatalha = true;

            } else if (escolha == 3) {
                String listaDeItens = this.inventario.listarItens();

                // se estiver vazio volta
                if (listaDeItens.equals("Inventário está vazio.\n")) {
                    continue;
                }

                System.out.println(listaDeItens);
                System.out.print("Digite o número do item para usar (0 para voltar): ");

                int escolhaItem = -1;
                try {
                    escolhaItem = Integer.parseInt(leitor.nextLine().trim());
                } catch (Exception ignored) {}

                if (escolhaItem == 0) {
                    continue;
                }

                Item itemParaUsar = this.inventario.get(escolhaItem - 1);

                if (itemParaUsar == null) {
                    System.out.println("Opção inválida.\n");
                    continue;
                }

                // usa item e perde o turno
                System.out.println("\n" + this.nome + " usou " + itemParaUsar.getNome() + "!");
                // chama o métod0 'usar'
                // Se for granada → usa no inimigo
                if (itemParaUsar instanceof itens.Granada) {
                    itemParaUsar.usar(inimigo);
                }
                // Se for poção ou escudo → usa no jogador
                else {
                    itemParaUsar.usar(this);
                }

                this.inventario.removerItem(itemParaUsar, 1);

                this.inventario.removerItem(itemParaUsar, 1);
                System.out.println();

            } else if (escolha == 4) {
                // lógica de fuga
                if (inimigo instanceof Boss) {
                    System.out.println("\nVocê não pode fugir de um Boss.");
                    System.out.println();
                    continue;
                }

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
                    System.out.println();
                    continue;
                }

                // tentativa de fuga
                System.out.println();
                if (tentarFugir()) {
                    System.out.println("Parabéns, você conseguiu fugir!");
                    int xpGanho = 35;
                    System.out.println(this.nome + " ganhou " + xpGanho + " XP!");
                    this.ganharXp(xpGanho);
                    this.Defesa = defesaOriginalBatalha;
                    return true;
                } else {
                    System.out.println("A fuga falhou! Você perdeu o turno.");
                }
            }

            if (!inimigo.estaVivo) break;

            // turno do inimigo
            if (inimigo.estaVivo) {
                System.out.println("\n--- Turno do " + inimigo.nome + " ---");
                try {
                    Thread.sleep(1200);
                } catch (InterruptedException e) {
                    // ignora
                }
                inimigo.atacar(this);
                System.out.println();
            }
        }
        this.Defesa = defesaOriginalBatalha; // voltar defesa normal (guerreiro habilidade)

        // fim da batalha
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
            System.out.println("VOCÊ FOI DERROTADO.");
            return false;
        }
    }

    // lógica de ataque
    public void atacar(Personagem oponente) {
        int rolagem = Dado.rolar(20);
        System.out.println("--- Ataque ---");
        System.out.println(this.nome + " tirou " + rolagem + " no d20!");

        if (rolagem == 1) {
            System.out.println("FALHA CRÍTICA!!! " + this.nome + " errou completamente.");
            return;
        }

        if (rolagem >= 18) {
            int danoCritico = this.ataque + 10;
            oponente.receberDano(danoCritico);
            System.out.println("ACERTO CRÍTICO!!! " + this.nome + " causou " + danoCritico + " de dano.");
            return;
        }

        int forcaAtaque = this.ataque + rolagem;
        System.out.println(this.nome + " força de ataque: " + forcaAtaque);

        if (forcaAtaque > oponente.Defesa) {
            int dano = forcaAtaque - oponente.Defesa;
            oponente.receberDano(dano);
            System.out.println(this.nome + " causou " + dano + " de dano!");
        } else {
            System.out.println(this.nome + " errou o ataque (Sua defesa é: " + oponente.Defesa + ")");
        }
    }

    // lógica para fugir
    private boolean tentarFugir() {
        int rolagem = Dado.rolar(20);
        System.out.println(this.nome + " tirou " + rolagem + " no d20!");
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

    // lógica para curar
    public void curar(int valor) {
        this.setPontosVida(this.pontosVida + valor);
        System.out.println(this.nome + " curou +" + valor + " [HP: " + this.pontosVida + "/" + this.maxPontosVida + "]");
    }

    // lógica para adicionar xp
    public void ganharXp(int quantidade) {
        this.xpAtual += quantidade;
        checarSeSubiuDeNivel();
    }

    // xp necessário para upar
    private void checarSeSubiuDeNivel() {
        while (this.xpAtual >= this.xpParaProximoNivel) {
            this.xpAtual -= this.xpParaProximoNivel;
            subirNivel();
        }
    }

    // bônus e cura ao upar
    public void subirNivel() {
        this.nivel++;
        System.out.println("\n--- LEVEL UP! ---");
        System.out.println(this.nome + " subiu para o nível " + this.nivel + "!");

        int bonusVida = 30;
        int bonusAtaque = 10;
        int bonusDefesa = 5;

        this.maxPontosVida += bonusVida;
        this.ataque += bonusAtaque;
        this.Defesa += bonusDefesa;

        System.out.println("Vida Máxima: +" + bonusVida);
        System.out.println("Ataque: +" + bonusAtaque);
        System.out.println("Defesa: +" + bonusDefesa);

        this.curar(80);
        System.out.println("-----------------");
    }

    @Override
    public String toString() {
        return this.nome + " (Nível: " + this.nivel + ", HP: " + this.pontosVida + "/" + this.maxPontosVida + ", XP: " + this.xpAtual + "/" + this.xpParaProximoNivel + ")";
    }
}