package entidades;

public class Mago extends Personagem {

    public Mago(String nome) {
        super();
        
        this.nome = nome;
        this.nivel = 1;
        this.pontosVida = 80;  
        this.ataque = 17;      
        this.Defesa = 5;
    }
        public Mago(Mago original) {
        super(original); 
    }
}