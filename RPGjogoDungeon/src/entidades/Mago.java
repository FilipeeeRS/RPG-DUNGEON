package entidades;

// subclasse Mago, herda de Personagem
public class Mago extends Personagem {

    public Mago(String nome) {
        super(); // construtor do Personagem

        this.nome = nome;
        this.nivel = 1;
        this.maxPontosVida = 80;
        this.pontosVida = 80;
        this.ataque = 17;
        this.Defesa = 5;
    }

    // Construtor de cópia
    public Mago(Mago original) {
        super(original);
    }
}