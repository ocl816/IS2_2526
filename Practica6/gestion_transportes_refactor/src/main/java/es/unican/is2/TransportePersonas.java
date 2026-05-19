package es.unican.is2;

/*
WMC = 5
WMCn 5/ 3 = 1.66
CCog = 2
CCogn = 2 / 3 = 0.66
*/
public class TransportePersonas extends Transporte {
    private int personas;

    public TransportePersonas(double horas, int personas) throws IllegalArgumentException { // CC + 1
        super(horas);

        if (personas <= 0) { // CC +1
            throw new IllegalArgumentException();
        }

        this.personas = personas;
    }

    public int getPersonas() { // CC +1
        return personas;
    }

    @Override
    public double getSueldoExtra() { // CC +1 || CCog +0
        if (personas < 10) { // CC +1 || CCog +1
            return getHoras() * 0.5;
        } else { // CCog +1
            return getHoras();
        }

    }

}
