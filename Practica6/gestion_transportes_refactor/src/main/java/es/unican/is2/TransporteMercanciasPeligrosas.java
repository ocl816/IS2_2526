package es.unican.is2;

/*
WMC = 3
WMCn = 3 / 3 = 1
CCog = 0
CCogn = 0 / 3 = 0
*/

public class TransporteMercanciasPeligrosas extends TransporteMercancias {

    private static final double PLUS_PELIGROSIDAD = 50.0;

    public TransporteMercanciasPeligrosas(double horas, int toneladas) throws IllegalArgumentException { // CC + 1
        super(horas, toneladas);
    }

    public int getToneladas() { // CC + 1 || CCog + 0
        return super.getToneladas();
    }

    @Override
    public double getSueldoExtra() { // CC + 1 || CCog + 0
        return super.getSueldoExtra() + PLUS_PELIGROSIDAD;
    }

}
