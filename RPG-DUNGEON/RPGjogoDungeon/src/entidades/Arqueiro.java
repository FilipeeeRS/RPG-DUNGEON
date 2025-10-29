package entidades;

public class Arqueiro extends Personagem {

    public Arqueiro(String nome) {
        super(); // <-- IMPORTANTE: Chama o construtor do Personagem
        this.nome = nome;
        this.nivel = 1;
        this.pontosVida = 100; 
        this.ataque = 12;      
        this.Defesa = 6;
    }

    public Arqueiro(Arqueiro original) {
        super(original);
    }
}