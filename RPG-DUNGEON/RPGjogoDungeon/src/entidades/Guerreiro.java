package entidades;

// subclasse Guerreiro, herda de Personagem
public class Guerreiro extends Personagem {

    // Construtor padrão
    public Guerreiro(String nome) {
        super(); // construtor do Personagem

        // atributos guerreiro
        this.nome = nome;
        this.nivel = 1;
        this.maxPontosVida = 150;
        this.pontosVida = 150;
        this.ataque = 10;
        this.Defesa = 8;
    }

    // construtor de cópia
    public Guerreiro(Guerreiro original) {
        super(original);
    }
}