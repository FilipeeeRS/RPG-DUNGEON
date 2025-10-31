package itens;

import entidades.Personagem;

public class HealPot extends Item {

    private int cura = 40;

    public HealPot(int quantidade) {
        super("Poção de Cura", "Recupera 40 de HP.", quantidade);
    }

    @Override
    public void usar(Personagem alvo) {
        alvo.curar(cura);
        removerQuantidade(1);
    }

    @Override
    public Item clone() {
        return new HealPot(this.quantidade);
    }
}
