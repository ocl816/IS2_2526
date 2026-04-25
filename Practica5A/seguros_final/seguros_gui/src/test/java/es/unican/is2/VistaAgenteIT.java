package es.unican.is2;

import org.assertj.swing.fixture.FrameFixture;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class VistaAgenteIT {
    private FrameFixture window;

    @BeforeEach
    public void preparacionTests() {
        // Inicializa lo necesario para crear la ventana de la GUI
        IClientesDAO clientesDAO = new ClientesDAO(); 
        ISegurosDAO segurosDAO = new SegurosDAO();
        GestionSeguros negocio = new GestionSeguros(clientesDAO, segurosDAO);

        // Crea la vista del agente
        VistaAgente vista = new VistaAgente(negocio, negocio, negocio);

        // Crea la clase para realizar las pruebas
        window = new FrameFixture(vista);
        window.show();
    }

    @AfterEach
    public void finTests() {
        //Limpia la ventana para proceder con el siguiente test
        window.cleanUp();
    }

    @Test
    public void testClienteConVariosSeguros() {
        // Entrada
        window.textBox("txtDNICliente").enterText("11111111A");
        window.button("btnBuscar").click();

        // SALIDA ESPERADA (Aserciones)
        window.textBox("txtNombreCliente").requireText("Juan");
        window.textBox("txtTotalCliente").requireText("1820.0"); 
        window.list().requireItemCount(3);
    }

    @Test
    public void testClienteSinSeguros() {
        // Entrada
        window.textBox("txtDNICliente").enterText("33333333A");
        window.button("btnBuscar").click();

        // Salida esperada
        window.textBox("txtNombreCliente").requireText("Luis");
        window.textBox("txtTotalCliente").requireText("0.0");
        window.list().requireItemCount(0);
    }

    @Test
    public void testCP3_ClienteConUnSeguro() {
        // Entrada
        window.textBox("txtDNICliente").enterText("22222222A");
        window.button("btnBuscar").click();

        // Salida esperada
        window.textBox("txtNombreCliente").requireText("Ana");
        window.textBox("txtTotalCliente").requireText("600.0");
    }

    @Test
    public void testCP4_ClienteNoExiste() {
        // Entrada
        window.textBox("txtDNICliente").enterText("99999999Z");
        window.button("btnBuscar").click();

        // Salida esperada
        window.textBox("txtNombreCliente").requireText("Cliente no encontrado");
        window.textBox("txtTotalCliente").requireText("");
        window.list().requireItemCount(0);
    }

    @Test
    public void testCP5_SimulacionCaidaBBDD() {
        // Limpia la ventana 
        window.cleanUp();

        // Cream un nuevo objeto IInfoSeguros que simulará la caida de la base de datos
        IInfoSeguros infoFallo = new IInfoSeguros() {
            @Override
            public Cliente cliente(String dni) throws DataAccessException {
                throw new DataAccessException(); // ¡Bum! Simulamos la caída
            }

            @Override
            public Seguro seguro(String matricula) throws DataAccessException {
                throw new UnsupportedOperationException("Unimplemented method 'seguro'");
            }
        };

        // Crea una nueva vista pero con la clase nueva rota
        // (Podemos pasar null a los otros DAOs porque el botón buscar solo usa 'info.cliente()')
        VistaAgente vistaRota = new VistaAgente(null, null, infoFallo);
        
        // Inicializa la ventana 
        FrameFixture windowFallo = new FrameFixture(vistaRota);
        windowFallo.show();

        // Entrada (Da igual el DNI que pongamos, la BBDD "está caída")
        windowFallo.textBox("txtDNICliente").enterText("11111111A");
        windowFallo.button("btnBuscar").click();

        // Salida esperada
        windowFallo.textBox("txtNombreCliente").requireText("Error en BBDD");
        windowFallo.textBox("txtTotalCliente").requireText("");
        windowFallo.list().requireItemCount(0);

        // Limpia la ventana utilizada
        windowFallo.cleanUp();
    }
}
