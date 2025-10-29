package entidades;

public class Guerreiro extends Personagem {

    public Guerreiro(String nome) {
        super();
        
        this.nome = nome;
        this.nivel = 1;
        this.pontosVida = 150; 
        this.ataque = 10;
        this.Defesa = 8;
    }

    public Guerreiro(Guerreiro original) {
        super(original); 
    }
}