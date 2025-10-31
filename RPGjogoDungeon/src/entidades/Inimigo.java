package entidades;

// classe Inimigo, derivada de Personagem.
public class Inimigo extends Personagem {

    public Inimigo(String nome, int nivel, int hp, int ataque, int defesa) {
        super(); // construtor do Personagem

        this.nome = nome;
        this.nivel = nivel;
        this.maxPontosVida = hp;
        this.pontosVida = hp;
        this.ataque = ataque;
        this.Defesa = defesa;
    }

    // Construtor de cópia
    public Inimigo(Inimigo original) {
        super(original);
    }
    @Override
    public void usarHabilidadeEspecial(Inimigo inimigo) {
    }
}