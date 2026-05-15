package es.unican.is2;

import java.util.LinkedList;
import java.util.List;
import fundamentos.*;

/*
CC = 18
WMC = 18
WMCn = 18/2 = 9
CCog = 34
CCogn = 34/2 = 17
*/

/**
 * Gestion de una empresa de transportes
 */
public class GestionTransportesGUI {

	/**
	 * Programa principal basado en menu
	 */
	public static void main(String[] args) { // CC +1 || CCOg +0
		// opciones del menu
		final int ANHADE_CONDUCTOR = 0, ANHADE_TRANSPORTE = 1,
				SUELDO_CONDUCTOR = 2, MEJOR_CONDUCTOR = 3;

		// crea la empresa de transportes
		GestionTransportes gt = new GestionTransportes();
		// crea la ventana de menu
		Menu menu = new Menu("Transportes");
		menu.insertaOpcion("Anhade conductor", ANHADE_CONDUCTOR);
		menu.insertaOpcion("Anhade transporte", ANHADE_TRANSPORTE);
		menu.insertaOpcion("Sueldo conductor", SUELDO_CONDUCTOR);
		menu.insertaOpcion("Mejor conductor", MEJOR_CONDUCTOR);

		int opcion;

		// lazo de espera de comandos del usuario
		while (true) { // CC +1 || CCOg +1
			opcion = menu.leeOpcion();

			// realiza las acciones dependiendo de la opcion elegida
			switch (opcion) { // CC +1 +1 +1 +1 || CCOg +1 +1
				case ANHADE_CONDUCTOR:
					procesaAnhadeConductor(gt);
					break;

				case ANHADE_TRANSPORTE:
					procesaAnhadeTransporte(gt);
					break;

				case SUELDO_CONDUCTOR:
					procesaSueldoConductor(gt);
					break;

				case MEJOR_CONDUCTOR:
					procesaMejorConductor(gt);
					break;
			}
		}
	}

	/**
	 * Procesa la peticion de anhadir un nuevo conductor
	 * @param gt gestion de los transportes
	 */
	public static void procesaAnhadeConductor(GestionTransportes gt) {
		Lectura lect = new Lectura("Datos Conductor");
		lect.creaEntrada("DNI", "");
		lect.creaEntrada("Nombre", "");
		lect.creaEntrada("Apellido1", "");
		lect.creaEntrada("Apellido2", "");
		lect.creaEntrada("Direccion", "");
		lect.esperaYCierra();
		String dni = lect.leeString("DNI");
		String nombre = lect.leeString("Nombre");
		String apellido1 = lect.leeString("Apellido1");
		String apellido2 = lect.leeString("Apellido2");
		String direccion = lect.leeString("Direccion");
		// Anhade el conductor
		if (!gt.anhadeConductor(dni, nombre, apellido1, apellido2, direccion)) // CC +1 || CCOg +1 +1 +1
			mensaje("ERROR", "Ya existe un conductor con DNI " + dni);
	}

	/**
	 * Procesa la peticion de anhadir un nuevo transporte
	 * @param gt gestion de los transportes
	 */
	public static void procesaAnhadeTransporte(GestionTransportes gt) {
		Lectura lect = new Lectura("Nuevo transporte");
		lect.creaEntrada("DNI", "");
		lect.creaEntrada("Tipo Transporte: P | M | MP", "");
		lect.creaEntrada("Horas", 0);
		lect.creaEntrada("Personas", 0);
		lect.creaEntrada("Toneladas", 0);
		lect.esperaYCierra();
		String dni = lect.leeString("DNI");
		String tipo = lect.leeString("Tipo Transporte: P | M | MP");
		int horas = lect.leeInt("Horas");
		int personas = lect.leeInt("Personas");
		int toneladas = lect.leeInt("Toneladas");

		Transporte t = null;
		Conductor c = gt.buscaConductor(dni);
		if (c != null) { // CC +1 || CCOg +1 +1 +1
			switch (tipo) { // CC +1 +1 +1 || CCOg +1 +1 +1 +1
				case "P":
					t = new Transporte(horas, CategoriaTransporte.Personas, personas);
					c.anhadeTransporte(t);
					break;
				case "M":
					t = new Transporte(horas, CategoriaTransporte.Mercancias, toneladas);
					c.anhadeTransporte(t);
					break;
				case "MP":
					t = new Transporte(horas, CategoriaTransporte.MercanciasPeligrosas, toneladas);
					c.anhadeTransporte(t);
					break;
			}
		} else { // CCog +1
			mensaje("ERROR", "No existe un conductor con DNI " + dni);
		}
	}

	/**
	 * Procesa la peticion de mostrar el sueldo de un conductor
	 * @param gt gestion de los transportes
	 */
	public static void procesaSueldoConductor(GestionTransportes gt) {
		Lectura lect = new Lectura("Transportes Peligrosos");
		lect.creaEntrada("DNI", "");
		lect.esperaYCierra();
		String dni = lect.leeString("DNI");
		Conductor c = gt.buscaConductor(dni);
		if (c != null) { // CC +1 || CCOg +1 +1 +1
			mensaje("Sueldo", "El sueldo del conductor es: " + c.sueldo());
		} else { // CCog +1
			mensaje("ERROR", "No existe un conductor con DNI " + dni);
		}
	}

	/**
	 * Procesa la peticion de mostrar el mejor conductor
	 * @param gt gestion de los transportes
	 */
	public static void procesaMejorConductor(GestionTransportes gt) {
		List<Conductor> resultado = new LinkedList<Conductor>();
		double maxSueldo = 0.0;
		for (Conductor conductor : gt.conductores()) { // CC +1 || CCOg +1 +1 +1
			if (conductor.sueldo() > maxSueldo) { // CC +1 || CCOg +1 +1 +1 +1
				maxSueldo = conductor.sueldo();
				resultado.clear();
				resultado.add(conductor);
			} else if (conductor.sueldo() == maxSueldo) { // CC +1 || CCOg +1
				resultado.add(conductor);
			}
		}
		String msj = "";
		if (resultado.size() == 0) { // CC +1 || CCOg +1 +1 +1
			msj = "No hay conductores";
		} else { // CCog +1
			for (Conductor conductor : resultado) { // CC +1 || CCOg +1 +1 +1 +1
				msj += conductor.getNombre() + " " + conductor.getNombre() + "\n";
			}
		}
		mensaje("MEJOR CONDUCTOR", msj);
	}

	/**
	 * Metodo auxiliar que muestra un ventana de mensaje
	 * 
	 * @param titulo titulo de la ventana
	 * @param txt    texto contenido en la ventana
	 */
	private static void mensaje(String titulo, String txt) { // CC +1 || CCOg +0
		Mensaje msj = new Mensaje(titulo);
		msj.escribe(txt);

	}

}
