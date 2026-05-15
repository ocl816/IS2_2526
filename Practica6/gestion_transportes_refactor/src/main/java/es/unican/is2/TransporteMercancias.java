package es.unican.is2;

public class TransporteMercancias extends Transporte {

    private int toneladas;
    public static final double PRECIO_TONELADA = 2.0;

    public TransporteMercancias(double horas, int toneladas) throws IllegalArgumentException {
        super(horas);

        if (toneladas <= 0) {
            throw new IllegalArgumentException();
        }

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
