package es.unican.is2;

public class TransporteMercancia extends Transporte {

    private int toneladas;
    public static final double PRECIO_TONELADA = 2.0;

    public TransporteMercancia(double horas, int toneladas) {
        super(horas);
        if (toneladas < 0)
            throw new IllegalArgumentException();
        this.toneladas = toneladas;
    }

    public int getToneladas() {
        return this.toneladas;
    }

    @Override
    public double getSueldoExtra() {
        return toneladas * PRECIO_TONELADA;
    }

}
