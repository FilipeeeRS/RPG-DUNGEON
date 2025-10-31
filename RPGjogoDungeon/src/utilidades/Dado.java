package utilidades;

import java.util.Random;

// classe para rolar dados (combate, fuga e mapa)
public class Dado {

    private static Random gerador = new Random();

    // rola um dado com um número x de faces ex: rolar(20) retorna entre 1 e 20
    public static int rolar(int faces) {
        return gerador.nextInt(faces) + 1;
    }
}