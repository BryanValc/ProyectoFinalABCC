package controlador;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conexionBD.ConexionBD;
import modelo.Comprador;

class ConsultaCompradores implements Runnable{
	ArrayList<Comprador> listaCompradores = new ArrayList<Comprador>();
	String filtro;
	
	public ConsultaCompradores(String filtro) {
		this.filtro = filtro;
	}

	public void run(){
		
		ResultSet rs;
		
		rs = ConexionBD.ejecutarConsulta(filtro);
		try {
			if (rs.next()) {
				do {
					listaCompradores.add(new Comprador(
							rs.getInt(1),
							rs.getString(2),
							rs.getString(3),
							rs.getString(4),
							rs.getString(5),
							rs.getString(6),
							rs.getString(7),
							rs.getString(8)));
				} while (rs.next());			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public ArrayList<Comprador> getListaCompradores() {
		return listaCompradores;
	}

	public void setListaCompradores(ArrayList<Comprador> listaCompradores) {
		this.listaCompradores = listaCompradores;
	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}
	
	
	
}

public class CompradorDAO {
	
	private static CompradorDAO compradorDAO=null;
	
	private CompradorDAO() {
		
	}
	public static synchronized CompradorDAO getInstance() {
		if (compradorDAO == null) {
			compradorDAO = new CompradorDAO();
		}
		return compradorDAO;
	}
	
	public boolean insertarRegistro(Comprador c) {
		boolean resultado = false;
		resultado = ConexionBD.agregarRegistro(c);
		return resultado;
	}
	
	public boolean eliminarRegistro(int compradorId) {
		boolean resultado = false;
		String sql="DELETE FROM Comprador WHERE compradorId = "+compradorId;
		resultado = ConexionBD.eliminarRegistro(sql);
		return resultado;
	}
	
	public boolean modificarRegistro(Comprador c, boolean flags[]) {
		boolean resultado = false;
		resultado = ConexionBD.actualizarRegistro(c);
		
		return resultado;
	}

	public synchronized ArrayList<Comprador> buscarCompradores(String filtro){
		ConsultaCompradores cc = new ConsultaCompradores(filtro);
		Thread h1 = new Thread(cc);
		h1.start();
		try {
			h1.join();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cc.getListaCompradores();
	}
	
	
}
