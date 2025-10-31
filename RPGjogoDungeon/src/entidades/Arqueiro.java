package entidades;

// subclasse Arqueiro, herda de Personagem
public class Arqueiro extends Personagem {

    public Arqueiro(String nome) {
        super(); // construtor do Personagem

        this.nome = nome;
        this.nivel = 1;
        this.maxPontosVida = 100;
        this.pontosVida = 100;
        this.ataque = 15;
        this.Defesa = 6;
    }

    // construtor de cópia
    public Arqueiro(Arqueiro original) {
        super(original);
    }

    // habilidade especial
    @Override
    public void usarHabilidadeEspecial(Inimigo inimigo) {
        System.out.println(this.nome + " usa [TIRO DUPLO]!");
        System.out.println();

        System.out.println("1° flecha.");
        this.atacar(inimigo);

        // ainda vivo?
        if (inimigo.isEstaVivo()) {
            System.out.println("\n2° flecha.");
            this.atacar(inimigo);
        }
    }
}