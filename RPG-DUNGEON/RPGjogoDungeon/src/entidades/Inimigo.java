package entidades;

public class Inimigo extends Personagem {

    public Inimigo(String nome, int nivel, int hp, int ataque, int defesa) {
        this.nome = nome;
        this.nivel = nivel;
        this.pontosVida = hp;
        this.ataque = ataque;
        this.Defesa = defesa;
    }
    
    public Inimigo(Inimigo original) {
        super(original); 
    }
}