package entidades;

// subclasse Arqueiro, herda de Personagem
public class Arqueiro extends Personagem {

    public Arqueiro(String nome) {
        super(); // construtor do Personagem

        this.nome = nome;
        this.nivel = 1;
        this.maxPontosVida = 100;
        this.pontosVida = 100;
        this.ataque = 12;
        this.Defesa = 6;
    }

    // construtor de cópia
    public Arqueiro(Arqueiro original) {
        super(original);
    }
}