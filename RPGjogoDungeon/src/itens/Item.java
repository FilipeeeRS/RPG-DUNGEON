package itens;

public class Item implements Comparable<Item> {

    private String nome;
    private String descricao;
    private String efeito;
    private int quantidade;

    public Item(String nome, String descricao, String efeito, int quantidade) {
        // Implementar
    }

    // --- Getters ---
    public String getNome() {
        return null;
    }

    public String getDescricao() {
        return null;
    }

    public String getEfeito() {
        return null;
    }

    public int getQuantidade() {
        return 0;
    }

    // --- Setters ---
    public void setQuantidade(int quantidade) {
        // Implementar
    }

    @Override
    public boolean equals(Object obj) {
        // Implementar
        return false;
    }

    @Override
    public int compareTo(Item outroItem) {
        // Implementar
        return 0;
    }

    @Override
    public int hashCode() {
        // Implementar
        return 0;
    }

    @Override
    public String toString() {
        // Implementar
        return "Item";
    }
}