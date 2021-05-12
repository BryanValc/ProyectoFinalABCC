package controlador;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;

import conexionBD.ConexionBD;
import modelo.Comprador;

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

	public ArrayList<Comprador> buscarCompradores(String filtro){
		ArrayList<Comprador> listaCompradores = new ArrayList<Comprador>();
		
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
		
		return listaCompradores;
	}
	
	
}
