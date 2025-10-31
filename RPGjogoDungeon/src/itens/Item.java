package itens;

import entidades.Personagem;

public abstract class Item implements Comparable<Item> {

    private String nome;
    private String descricao;
    protected int quantidade;

    public Item(String nome, String descricao, int quantidade) {
        this.nome = nome;
        this.descricao = descricao;
        this.quantidade = quantidade;
    }

    public String getNome() {
        return nome;
    }
    public int getQuantidade() {
        return quantidade;
    }
    public void adicionarQuantidade(int q) {this.quantidade += q;}
    public void removerQuantidade(int q) {this.quantidade -= q;}

    public abstract void usar(Personagem alvo);

    public abstract Item clone();

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Item)) return false;
        return this.nome.equalsIgnoreCase(((Item)obj).nome);
    }

    @Override
    public int compareTo(Item outroItem) {
        return this.nome.compareToIgnoreCase(outroItem.nome);
    }


    @Override
    public String toString() {
        return nome + " (x" + quantidade + ") - " + descricao;
    }
}