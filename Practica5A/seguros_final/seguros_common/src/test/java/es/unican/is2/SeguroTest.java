package es.unican.is2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests para la clase Seguro
 */
public class SeguroTest {
    
    private Cliente clienteMinusvalido;
    private Cliente clienteNormal;
    private LocalDate hoy;

    @BeforeEach
    public void preparacionTests() {
        // Prepara clientes y establece la fecha para las purebas
        clienteMinusvalido = new Cliente("Luis", "11111111A", true);
        clienteNormal = new Cliente("Juan", "22222222A", false);
        hoy = LocalDate.now();
    }
    
    @Test
    public void testPreciosValidos() {
        // Caso 1: hoy, 89, TERCEROS, minusválido -> 240.0
        Seguro s1 = new Seguro(1, "1111AAA", 89, Cobertura.TERCEROS, hoy, null);
        assertEquals(240.0, s1.precio(clienteMinusvalido), 0.01);

        // Caso 2: hoy - 1 año + 1 día, 90, TODO_RIESGO, normal -> 840.0
        Seguro s2 = new Seguro(2, "2222AAA", 90, Cobertura.TODO_RIESGO, hoy.minusYears(1).plusDays(1),null);
        assertEquals(840.0, s2.precio(clienteNormal), 0.01);
        
        // Caso 3: hoy - 1 año, 110, TERCEROS_LUNAS, minusválido -> 472.5
        Seguro s3 = new Seguro(3, "3333AAA", 110, Cobertura.TERCEROS_LUNAS, hoy.minusYears(1), null);
        assertEquals(472.5, s3.precio(clienteMinusvalido), 0.01);
    
        // Caso 4: hoy - 2 años, 111, TERCEROS, null -> 480.0
        Seguro s4 = new Seguro(4, "4444AAA", 111, Cobertura.TERCEROS, hoy.minusYears(2),null);
        assertEquals(480.0, s4.precio(null));
    }

    @Test
    public void testPreciosNoValidos() {
        // Caso 5: hoy + 1 día, 100, TERCEROS, normal -> 0.0
        Seguro s5 = new Seguro(5, "5555AAA", 100, Cobertura.TERCEROS, hoy.plusDays(1), null);
        assertEquals(0.0, s5.precio(clienteNormal));

        //Caso 6: hoy, 100, null, normal -> Cobertura a null, lanza excepcion 
        Seguro s6 = new Seguro(6, "6666AAA", 100, null, hoy, null);
        assertThrows(IllegalArgumentException.class, () -> {s6.precio(clienteMinusvalido); });

        // Caso 7: null, 100, TERCEROS, normal -> fecha a null, lanza excepcion
        Seguro s7 = new Seguro(7, "7777AAA", 100 , Cobertura.TERCEROS, null, null); 
        assertThrows(IllegalArgumentException.class, () -> {s7.precio(clienteMinusvalido); });
    }
}
