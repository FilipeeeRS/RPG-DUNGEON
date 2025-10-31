package itens;

import entidades.Personagem;

public class Granada extends Item {

    private int dano = 25;

    public Granada(int quantidade) {
        super("Granada", "Explode causando 25 de dano direto.", quantidade);
    }

    @Override
    public void usar(Personagem alvo) {
        alvo.receberDano(dano);
        removerQuantidade(1);
        System.out.println("A granada explodiu causando " + dano + " de dano!");
    }

    @Override
    public Item clone() {

        return new Granada(this.quantidade);
    }
}
