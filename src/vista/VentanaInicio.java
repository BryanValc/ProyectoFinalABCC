package vista;

import java.util.ArrayList;

import controlador.CompradorDAO;
import modelo.Comprador;

public class VentanaInicio {

	public static void main(String[] args) {
		
		CompradorDAO compradorDAO = new CompradorDAO();
		Comprador comprador = new Comprador(1212, "Bryan", "1x1E1SA123123", "Arq Damaso", "Jerez", "Zacatecas", "494-118-9287", "bryan.valdez117@outlook.es");
		//compradorDAO.insertarRegistro(comprador);
		//compradorDAO.eliminarRegistro(1212);
		Comprador comprador2 = new Comprador(1212, "Juan", "1x1E1SA123123", "Arq Damaso", "Zacatecas", "Zacatecas", "494-118-9287", "bryan.valdez117@outlook.es");
		boolean flags[]=new boolean[7];
		flags[0]=true;
		compradorDAO.modificarRegistro(comprador2, flags);
		ArrayList<Comprador> compradores = compradorDAO.buscarCompradores("SELECT * FROM Comprador WHERE nombre = 'Bryan'");
		System.out.println(compradores);
	}

}
