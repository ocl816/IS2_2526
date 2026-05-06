import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.unican.is2.ConjuntoOrdenado;
import es.unican.is2.adt.IConjuntoOrdenado;

/**
 * Clase de prueba para la clase ConjuntoOrdenado
 */
public class ConjuntoOrdenadoTest {
    private IConjuntoOrdenado<Integer> conjunto;

    @BeforeEach
    public void preparacionTest() {
        conjunto = new ConjuntoOrdenado<>();
    }

    @Test
    public void conjuntoGetValidosTest() {
        conjunto.add(1); conjunto.add(2); conjunto.add(8);

        //Comprueba que se obtenga el elemento correcto
        assertEquals(1, conjunto.get(0));
        assertEquals(2, conjunto.get(1));
        assertEquals(8, conjunto.get(2));
    }

    @Test
    public void conjuntoGetNoValidosTest() {
        //Comprueba que se lance la excepcion si no hay elementos
        assertThrows(IndexOutOfBoundsException.class, () -> conjunto.get(0));

        conjunto.add(1); conjunto.add(2); conjunto.add(8);
        
        //Comprueaba que se lance la excepcion si el indice esta fuera de los limites del conjunto
        assertThrows(IndexOutOfBoundsException.class, () -> conjunto.get(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> conjunto.get(3)); // Igual a size
    }

    @Test
    public void conjuntoAddValidosTest() {
        //Comprueba que se anhade correctamente estando la lista vacia
        assertTrue(conjunto.add(1));
        assertEquals(1, conjunto.size());
        assertEquals(1, conjunto.get(0));
        
        //Comprueba que se anhade correctamente estando la lista con al menos un elem
        assertTrue(conjunto.add(2));
        assertTrue(conjunto.add(8));

        assertEquals(3, conjunto.size());
        assertEquals(1, conjunto.get(0));
        assertEquals(2, conjunto.get(1));
        assertEquals(8, conjunto.get(2));

        //Comprueba que no se pueda anhadir un elemento repetido
        assertFalse(conjunto.add(2));
        assertEquals(3, conjunto.size());
    }

    @Test
    public void conjuntoAddNoValidosTest() {
        // Comprueba que lance la excepcion si el elemento es nulo en un conjunto vacio
        assertThrows(NullPointerException.class, () -> conjunto.add(null));

        assertTrue(conjunto.add(1));
        assertTrue(conjunto.add(2));
        assertTrue(conjunto.add(8));

        // Comprueba que lance la excepcion si el elemento es nulo en un conjunto con n elem
        assertThrows(NullPointerException.class, () -> conjunto.add(null));
    }

    @Test
    public void conjuntoRemoveValidosTest() {

        conjunto.add(1); conjunto.add(2); conjunto.add(8);

        assertEquals(1, conjunto.remove(0));
        assertEquals(2, conjunto.size());
        assertEquals(2, conjunto.get(0)); // El 2 pasa a ser el primero


        preparacionTest(); 
        conjunto.add(1); conjunto.add(2); conjunto.add(8);
        assertEquals(2, conjunto.remove(1));
        assertEquals(2, conjunto.size());
        assertEquals(8, conjunto.get(1)); // El 8 ocupa la posición del 2


        preparacionTest();
        conjunto.add(1); conjunto.add(2); conjunto.add(8);
        assertEquals(8, conjunto.remove(2));
        assertEquals(2, conjunto.size()); 
        assertEquals(2, conjunto.get(1)); // El último ahora es el 2
    }

    @Test
    public void conjuntoRemoveNoValidosTest() {
        // Comprueba que lance la excepcion si es un conjunto vacio
        assertThrows(IndexOutOfBoundsException.class, () -> conjunto.remove(0));

        conjunto.add(1); conjunto.add(2); conjunto.add(8);

        // Comprueba que lance la excepcion si se borra fuera de los limites
        assertThrows(IndexOutOfBoundsException.class, () -> conjunto.remove(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> conjunto.remove(3));
    }

    @Test
    public void conjuntoSizeYClear() {
        // Compueba que el tamaño de un conjunto vacio sea 0
        assertEquals(0, conjunto.size());

        // Compueba que el tamaño del conjunto sea 3
        conjunto.add(3); conjunto.add(5); conjunto.add(8);
        assertEquals(3, conjunto.size());

        // Comprueba que realiza el clear correctamente
        conjunto.clear();
        assertEquals(0, conjunto.size());
        
        conjunto.clear();
        assertEquals(0, conjunto.size());
    }
}

