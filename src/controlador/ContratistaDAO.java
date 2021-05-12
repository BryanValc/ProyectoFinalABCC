package controlador;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;

import conexionBD.ConexionBD;
import modelo.Comprador;
import modelo.Contratista;

public class ContratistaDAO{
	
	private static ContratistaDAO contratistaDAO=null;
	
	private ContratistaDAO() {
		
	}
	
	public static synchronized ContratistaDAO getInstance() {
		if (contratistaDAO==null) {
			contratistaDAO=new ContratistaDAO();
		}
		return contratistaDAO;
	}
	
	public boolean insertarRegistro(Contratista c) {
		boolean resultado = false;
		resultado = ConexionBD.agregarRegistro(c);
		return resultado;
	}
	
	public boolean eliminarRegistro(int contratistaId) {
		boolean resultado = false;
		String sql="DELETE FROM Contratista WHERE contratistaId = "+contratistaId;
		resultado = ConexionBD.eliminarRegistro(sql);
		return resultado;
	}
	
	public boolean modificarRegistro(Contratista c, boolean flags[]) {
		boolean resultado = false;
		resultado = ConexionBD.actualizarRegistro(c);
		return resultado;
	}
	
	public ArrayList<Contratista> buscarContratistas(String filtro){
		ArrayList<Contratista> listaContratistas = new ArrayList<Contratista>();
		
		ResultSet rs;
		
		rs = ConexionBD.ejecutarConsulta(filtro);
		try {
			if (rs.next()) {
				do {
					listaContratistas.add(new Contratista(
							rs.getInt(1),
							rs.getString(2),
							rs.getInt(3)));
				} while (rs.next());			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listaContratistas;
	}
	
}
