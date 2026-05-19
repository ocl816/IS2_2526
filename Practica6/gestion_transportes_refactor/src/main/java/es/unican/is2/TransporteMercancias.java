package es.unican.is2;

/* 
WMC = 4
WMCnn = 4 / 3 = 1.33
CCog = 1
CCogn = 1 / 3 = 0.33
*/
public class TransporteMercancias extends Transporte {

    private int toneladas;
    public static final double PRECIO_TONELADA = 2.0;

    public TransporteMercancias(double horas, int toneladas) throws IllegalArgumentException { // CC + 1
        super(horas);

        if (toneladas <= 0) { // CC + 1 || CCog + 1
            throw new IllegalArgumentException();
        }

        this.toneladas = toneladas;
    }

    public int getToneladas() { // CC + 1 || CCog + 0
        return this.toneladas;
    }

    @Override
    public double getSueldoExtra() { // CC + 1 || CCog + 0
        return toneladas * PRECIO_TONELADA;
    }

}
