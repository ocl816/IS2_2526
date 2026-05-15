package es.unican.is2;

public class TransportePersonas extends Transporte {
    private int personas;

    public TransportePersonas(double horas, int personas) throws IllegalArgumentException {
        super(horas);

        if (personas <= 0) {
            throw new IllegalArgumentException();
        }

        this.personas = personas;
    }

    public int getPersonas() {
        return personas;
    }

    @Override
    public double getSueldoExtra() {
        if (personas < 10) {
            return getHoras() * 0.5;
        } else {
            return getHoras();
        }

    }

}
