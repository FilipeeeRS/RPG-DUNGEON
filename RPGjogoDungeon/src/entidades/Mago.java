package entidades;

// subclasse Mago, herda de Personagem
public class Mago extends Personagem {

    public Mago(String nome) {
        super(); // construtor do Personagem

        this.nome = nome;
        this.nivel = 1;
        this.maxPontosVida = 80;
        this.pontosVida = 80;
        this.ataque = 18;
        this.Defesa = 5;
    }

    // Construtor de cópia
    public Mago(Mago original) {
        super(original);
    }

    // habilidade especial
    @Override
    public void usarHabilidadeEspecial(Inimigo inimigo) {
        int danoHabilidade = (this.ataque * 2);
        System.out.println("\nA magia explode sobre " + inimigo.getNome());
        System.out.println(this.nome + " causou " + danoHabilidade + " de dano!");
        inimigo.receberDano(danoHabilidade);
    }
}