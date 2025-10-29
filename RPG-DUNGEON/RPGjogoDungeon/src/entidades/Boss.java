package entidades;

public class Boss extends Inimigo {

    // Construtor do Boss
    public Boss(String nome, int nivel, int hp, int ataque, int defesa) {
        // Chama o construtor da classe pai (Inimigo)
        super(nome, nivel, hp, ataque, defesa);
    }
}