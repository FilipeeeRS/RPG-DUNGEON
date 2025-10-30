package utilidades;

import java.util.Random;

// classe para rolar dados de combate, fuga e escolha de mapa
public class Dado {

    private static Random gerador = new Random();

    // rola um dado com um número específico de faces. Ex: rolar(20) retorna um valor entre 1 e 20
    public static int rolar(int faces) {
        return gerador.nextInt(faces) + 1;
    }
}