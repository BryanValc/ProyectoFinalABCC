package vista;

import java.util.ArrayList;

import controlador.*;
import modelo.*;

public class VentanaInicio {

	public static void main(String[] args) {
		/*
		CompradorDAO compradorDAO = new CompradorDAO();
		Comprador comprador = new Comprador(1212, "Bryan", "1x1E1SA123123", "Arq Damaso", "Jerez", "Zacatecas", "494-118-9287", "bryan.valdez117@outlook.es");
		compradorDAO.insertarRegistro(comprador);
		compradorDAO.eliminarRegistro(1212);
		Comprador comprador2 = new Comprador(1212, "Juan", "1x1E1SA123123", "Arq Damaso", "Zacatecas", "Zacatecas", "494-118-9287", "bryan.valdez117@outlook.es");
		boolean flags[]=new boolean[7];
		flags[0]=true;
		compradorDAO.modificarRegistro(comprador2, flags);
		ArrayList<Comprador> compradores = compradorDAO.buscarCompradores("SELECT * FROM Comprador WHERE nombre = 'Bryan'");
		System.out.println(compradores);
		*/
		
		/*
		ContratistaDAO contratistaDAO = new ContratistaDAO();
		Contratista contratista = new Contratista(2121, "Bryan",1177);
		contratistaDAO.insertarRegistro(contratista);
		contratistaDAO.eliminarRegistro(2121);
		Contratista contratista2 = new Contratista(2121, "Leo",11);
		boolean flags[]=new boolean[2];
		flags[0]=true;
		contratistaDAO.modificarRegistro(contratista2, flags);
		ArrayList<Contratista> contratistas = contratistaDAO.buscarContratistas("SELECT * FROM Contratista WHERE nombreContratista = 'Leo'");
		System.out.println(contratistas);
		*/
		
		/*
		CriptomonedaDAO criptomonedaDAO = new CriptomonedaDAO();
		Criptomoneda criptomoneda = new Criptomoneda("SUSHI",16.36,"defi Token");
		criptomonedaDAO.insertarRegistro(criptomoneda);
		criptomonedaDAO.eliminarRegistro("SUSHI");
		boolean flags[]=new boolean[2];
		flags[0]=true;
		criptomoneda.setPrecioUnidad(17);
		criptomonedaDAO.modificarRegistro(criptomoneda, flags);
		ArrayList<Criptomoneda> criptomonedas = criptomonedaDAO.buscarCriptomonedas("SELECT * FROM Criptomoneda WHERE precioUnidad = 17");
		System.out.println(criptomonedas);
		*/
		
		/*
		OrdenDAO ordenDAO = new OrdenDAO();
		Orden orden = new Orden(9000000000000L,"08-May-2021",1212,5);
		ordenDAO.insertarRegistro(orden);
		ordenDAO.eliminarRegistro(9000000000000L);
		orden.setFechaOrden("08-Jun-2021");
		boolean flags[]=new boolean[3];
		flags[0]=true;
		ordenDAO.modificarRegistro(orden, flags);
		ArrayList<Orden> ordenes = ordenDAO.buscarOrdenes("SELECT * FROM Orden WHERE fechaOrden = '08-Jun-2021'");
		System.out.println(ordenes);
		*/
		
		/*
		PoolDAO poolDAO = new PoolDAO();
		Pool pool = new Pool("F2P", 70000000L, 32000, 16000);
		poolDAO.insertarRegistro(pool);
		poolDAO.eliminarRegistro("F2P");
		pool.setPotenciaDeMinadoMHs(160000000L);
		pool.setCantidadDeMineros(32000);
		boolean flags[]=new boolean[3];
		flags[0]=true;
		poolDAO.modificarRegistro(pool, flags);
		ArrayList<Pool> pools = poolDAO.buscarPools("SELECT * FROM Pool WHERE poolId = 'F2P'");
		System.out.println(pools);
		*/
		
	}

}
