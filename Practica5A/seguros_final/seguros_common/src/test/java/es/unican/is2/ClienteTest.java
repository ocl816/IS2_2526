package es.unican.is2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Clase de prueba para la clase cliente
 */
public class ClienteTest {

    private Cliente cliente;
    private Seguro s1;
    private Seguro s2;


    @BeforeEach
    public void preparacionTests() {
        cliente = new Cliente("Ana", "12345678A", true);
        LocalDate hoy = LocalDate.now();

        s1 = new Seguro(1, "1111AAA", 89, Cobertura.TERCEROS, hoy, null);
        s2 = new Seguro(2, "2222AAA", 90, Cobertura.TODO_RIESGO, hoy.minusYears(1).plusDays(1), null);
    }

    @Test
    public void testTotalSegurosValidos() {
        // Caso 1: Lista vacía
        assertEquals(0.0, cliente.totalSeguros(), 0.01);

        // Caso 2: Lista con 1 seguro 
        cliente.getSeguros().add(s1);
        assertEquals(240.0, cliente.totalSeguros(), 0.01);

        // Caso 3: Lista con varios seguros 
        cliente.getSeguros().add(s2);
        assertEquals(870.0, cliente.totalSeguros(), 0.01);
    }

    @Test
    public void testTotalSegurosNoValidos() {
        // Caso 4: La lista de seguros es nula -> lanza error
        cliente.setSeguros(null);
        assertThrows(NullPointerException.class,() -> {cliente.totalSeguros();});

        // Caso 5: La lista tiene un elemento nulo
        List<Seguro> listaSeguros = new ArrayList<>();
        listaSeguros.add(null);
        cliente.setSeguros(listaSeguros);
        assertThrows(NullPointerException.class,() -> {cliente.totalSeguros();});
    }
}
