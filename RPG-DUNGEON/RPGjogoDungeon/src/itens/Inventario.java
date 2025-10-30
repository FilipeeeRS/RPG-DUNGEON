package itens;

import java.util.ArrayList;
import java.util.Collections; // Para ordenar a lista

/**
 * A classe Inventario, conforme o PDF.
 * [cite_start]Ela também deve implementar 'clone' e ter um construtor de cópia. [cite: 28, 33, 34]
 */
public class Inventario implements Cloneable { // Implementa Cloneable

    private ArrayList<Item> itens;

    /**
     * [cite_start]Construtor padrão [cite: 34]
     */
    public Inventario() {
        this.itens = new ArrayList<>();
    }

    /**
     * [cite_start]Construtor de cópia (Obrigatório pelo PDF) [cite: 34]
     * Usado para criar uma cópia independente de um inventário.
     */
    public Inventario(Inventario original) {
        this.itens = new ArrayList<>();
        // Precisamos copiar cada item, não apenas a lista
        for (Item itemOriginal : original.itens) {
            // Cria um *novo* objeto Item com os mesmos dados
            //
            // LINHA CORRIGIDA ABAIXO:
            this.itens.add(new Item(itemOriginal.getNome(), itemOriginal.getDescricao(), itemOriginal.getEfeito(), itemOriginal.getQuantidade()));
        }
    }

    /**
     * [cite_start]Método clone() (Obrigatório pelo PDF) [cite: 33, 55]
     * Usado para saquear baús/inimigos.
     */
    @Override
    public Inventario clone() {
        // A maneira "correta" de fazer clone é usando o construtor de cópia
        return new Inventario(this);
    }

    /**
     * Adiciona um item. [cite_start]Se já existir, aumenta a quantidade. [cite: 30]
     */
    public void adicionar(Item novoItem) {
        int index = this.itens.indexOf(novoItem); // Usa o .equals() do Item para procurar

        if (index != -1) {
            // --- Item já existe ---
            Item itemExistente = this.itens.get(index);
            // Soma a quantidade
            itemExistente.setQuantidade(itemExistente.getQuantidade() + novoItem.getQuantidade());
        } else {
            // --- Item é novo ---
            this.itens.add(novoItem);
        }
    }

    /**
     * [cite_start]Remove um item (diminuindo a quantidade) [cite: 30]
     */
    public void usar(Item itemParaUsar) {
        int index = this.itens.indexOf(itemParaUsar);

        if (index != -1) {
            Item itemNoInventario = this.itens.get(index);
            // Decrementa a quantidade
            //
            // LINHA CORRIGIDA ABAIXO:
            itemNoInventario.setQuantidade(itemNoInventario.getQuantidade() - 1);

            // Se a quantidade chegar a 0, remove o item da lista
            if (itemNoInventario.getQuantidade() <= 0) {
                this.itens.remove(index);
            }
        }
    }

    /**
     * [cite_start]Lista todos os itens ordenados [cite: 31]
     */
    public String listarItens() {
        if (this.itens.isEmpty()) {
            return "O inventário está vazio.";
        }

        Collections.sort(this.itens);

        StringBuilder lista = new StringBuilder();
        lista.append("--- INVENTÁRIO ---\n");
        for (Item item : this.itens) {
            lista.append(item.toString()).append("\n"); // Usa o toString() do Item
        }
        lista.append("------------------");

        return lista.toString();
    }
}