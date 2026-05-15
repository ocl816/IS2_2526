package es.unican.is2;

public class TransporteMercanciasPeligrosas extends TransporteMercancias {

    private static final double PLUS_PELIGROSIDAD = 50.0;

    public TransporteMercanciasPeligrosas(double horas, int toneladas) throws IllegalArgumentException {
        super(horas, toneladas);
    }

    public int getToneladas() {
        return super.getToneladas();
    }

    @Override
    public double getSueldoExtra() {
        return super.getSueldoExtra() + PLUS_PELIGROSIDAD;
    }

}
