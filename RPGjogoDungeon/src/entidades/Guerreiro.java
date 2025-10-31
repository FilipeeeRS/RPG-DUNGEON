package entidades;

// subclasse Guerreiro, herda de Personagem
public class Guerreiro extends Personagem {

    // Construtor padrão
    public Guerreiro(String nome) {
        super(); // construtor do Personagem

        this.nome = nome;
        this.nivel = 1;
        this.maxPontosVida = 150;
        this.pontosVida = 150;
        this.ataque = 12;
        this.Defesa = 8;
    }

    // construtor de cópia
    public Guerreiro(Guerreiro original) {
        super(original);
    }

    // habilidade especial
    @Override
    public void usarHabilidadeEspecial(Inimigo inimigo) {
        int bonusDefesa = 10;
        this.Defesa += bonusDefesa;
        System.out.println("\nSua Defesa aumentou em " + bonusDefesa + " pelo resto da batalha.");
    }
}