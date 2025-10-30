package itens;

/**
 * A classe Item, conforme o PDF.
 * Ela deve implementar Comparable para ser ordenada.
 */
public class Item implements Comparable<Item> {

    private String nome;
    private String descricao;
    private String efeito;
    private int quantidade;

    // Construtor
    public Item(String nome, String descricao, String efeito, int quantidade) {
        this.nome = nome;
        this.descricao = descricao;
        this.efeito = efeito;
        this.quantidade = quantidade;
    }

    // --- Getters ---
    public String getNome() { return nome; }
    public int getQuantidade() { return quantidade; }
    public String getDescricao() { return descricao; } // <-- CORRIGIDO (Adicionado)
    public String getEfeito() { return efeito; }       // <-- CORRIGIDO (Adicionado)

    // --- Setters ---
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    /**
     * O PDF exige o uso de equals para verificar se um item já existe.
     * Vamos considerar que dois itens são "iguais" se tiverem o mesmo nome.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Item item = (Item) obj;
        return this.nome.equals(item.nome);
    }

    /**
     * O PDF exige o uso de compareTo para ordenar os itens.
     * Vamos ordenar pelo nome, em ordem alfabética.
     */
    @Override
    public int compareTo(Item outroItem) {
        return this.nome.compareTo(outroItem.nome);
    }

    /**
     * O PDF exige o uso de hashCode onde 'equals' é feito.
     */
    @Override
    public int hashCode() {
        return this.nome.hashCode();
    }

    /**
     * O PDF exige o uso de toString.
     */
    @Override
    public String toString() {
        return this.nome + " (x" + this.quantidade + "): " + this.descricao;
    }
}