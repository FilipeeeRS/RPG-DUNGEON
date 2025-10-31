package itens;

import java.util.ArrayList;

public class Inventario {

    private ArrayList<Item> itens;

    public Inventario() {
        this.itens = new ArrayList<>();
    }

    // Construtor de copia
    public Inventario(Inventario inicial) {
        this.itens = new ArrayList<>();
        for (Item item : inicial.itens) {
            this.itens.add(item.clone());
        }
    }

    public void adicionarItem(Item novoItem) {
        for (Item item : itens) {
            if (item.equals(novoItem)) {    // verifica se ja tem o item no inventario
                item.adicionarQuantidade(novoItem.getQuantidade());
                return;
            }
        }
        itens.add(novoItem); // se nao tinha, adiciona o novo item no inventario
    }

    // Remove quantidade. Se zerar, remove o item da lista
    public void removerItem(Item item, int quantidade) {
        for (int i = 0; i < itens.size(); i++) {
            if (itens.get(i).equals(item)) {
                itens.get(i).removerQuantidade(quantidade);
                if (itens.get(i).getQuantidade() <= 0) {
                    itens.remove(i);
                }
                return;
            }
        }
    }

    public Item get(int indice) {
        if (indice < 0 || indice >= itens.size()) return null;
        return itens.get(indice);
    }

    public String listarItens() {
        if(itens.isEmpty()) {
            return "\nInventário vazio.";
        }
        StringBuilder sb = new StringBuilder("\n--- INVENTÁRIO ---");
        for (int i = 0; i < itens.size(); i++) {
            sb.append("(").append(i + 1).append(") ").append(itens.get(i).toString()).append("\n");
        }
        return sb.toString();
    }

}