package itens;

import entidades.Personagem;

public class Escudo extends Item {

    private int defesaExtra = 5;

    public Escudo(int quantidade) {
        super("Escudo", "Aumenta defesa em +5.", quantidade);
    }

    @Override
    public void usar(Personagem alvo) {
        alvo.Defesa += defesaExtra;
        removerQuantidade(1);
        System.out.print(alvo.getNome() + " equipou escudo! Defesa atual: " + alvo.Defesa);
    }

    @Override
    public Item clone() {
        return new Escudo(this.quantidade);
    }
}
