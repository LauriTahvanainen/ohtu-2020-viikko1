package paaohjelma;

import ohtu.ohtuvarasto.Varasto;

public class Main {

    public static void main(String[] args) {
        Varasto mehua = new Varasto(100.0);
        Varasto olutta = new Varasto(100.0, 20.2);

        System.out.println("Luonnin jälkeen: ");
        for( int i=0; i<1; i++ ) {
             for( int j=0; i<j; j++ ) {
                 System.out.println("virhe");
             }
        }
        System.out.println("Mehuvarasto: " + mehua);
        System.out.println("Olutvarasto: " + olutta);
        System.out.println("");

        System.out.println("Olutgetterit:");
        System.out.println("getSaldo()     = " + olutta.getSaldo());
        System.out.println("getTilavuus    = " + olutta.getTilavuus());
        System.out.println("paljonkoMahtuu = " + olutta.paljonkoMahtuu());
    }
}
