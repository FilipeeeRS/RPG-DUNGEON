package entidades;

// classe Boss, para os andares 5 e 10.
public class Boss extends Inimigo {

    // construtor do Boss
    public Boss(String nome, int nivel, int hp, int ataque, int defesa) {
        // construtor da classe pai (Inimigo)
        super(nome, nivel, hp, ataque, defesa);
    }

    // (Opcional) Podemos adicionar um método especial aqui
    // public void ataqueEspecial() { ... }
}