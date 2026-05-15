package es.unican.is2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class TransporteTest {

    @Test
    public void testConstructorTransporteMercancias() {
        // Casos validos
        TransporteMercancias sut = new TransporteMercancias(1, 1);
        assertEquals(1, sut.getHoras());
        assertEquals(1, sut.getToneladas()); // ton() renombrado a getToneladas()

        // Casos no validos (horas <= 0 o toneladas <= 0)
        assertThrows(IllegalArgumentException.class, () -> new TransporteMercancias(0, 1));
        assertThrows(IllegalArgumentException.class, () -> new TransporteMercancias(10, 0));
    }
    
    @Test
    public void testConstructorTransporteMercanciasPeligrosas() {
        // Casos validos
        TransporteMercanciasPeligrosas sut = new TransporteMercanciasPeligrosas(10, 1000);
        assertEquals(10, sut.getHoras());
        assertEquals(1000, sut.getToneladas());

        // Casos no validos
        assertThrows(IllegalArgumentException.class, () -> new TransporteMercanciasPeligrosas(0, 1000));
        assertThrows(IllegalArgumentException.class, () -> new TransporteMercanciasPeligrosas(10, 0));
    }

    @Test
    public void testConstructorTransportePersonas() {
        // Casos validos
        TransportePersonas sut = new TransportePersonas(10, 10);
        assertEquals(10, sut.getHoras());
        assertEquals(10, sut.getPersonas());

        // Casos no validos (horas <= 0 o personas <= 0)
        assertThrows(IllegalArgumentException.class, () -> new TransportePersonas(0, 10));
        assertThrows(IllegalArgumentException.class, () -> new TransportePersonas(10, 0));
    }
}