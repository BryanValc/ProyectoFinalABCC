package controlador;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;

import conexionBD.ConexionBD;
import modelo.Orden;

public class OrdenDAO {
	
	private static OrdenDAO ordenDAO=null;
	
	private OrdenDAO() {
		
	}
	
	public static synchronized OrdenDAO getInstance(){
		if (ordenDAO==null) {
			ordenDAO = new OrdenDAO();
		}
		return ordenDAO;
	}
	
	public boolean insertarRegistro(Orden o) {
		boolean resultado = false;
		resultado = ConexionBD.agregarRegistro(o);
		return resultado;
	}
	
	public boolean eliminarRegistro(long ordenId) {
		boolean resultado = false;
		
		String sql="DELETE FROM Orden WHERE ordenId = "+ordenId;
		resultado = ConexionBD.eliminarRegistro(sql);
		
		return resultado;
	}
	
	public boolean modificarRegistro(Orden o, boolean flags[]) {
		boolean resultado = false;
		boolean primero=true;
		resultado = ConexionBD.actualizarRegistro(o);
		return resultado;
	}
	
	public ArrayList<Orden> buscarOrdenes(String filtro){
		ArrayList<Orden> listaOrdenes = new ArrayList<Orden>();
		
		ResultSet rs;
		
		rs = ConexionBD.ejecutarConsulta(filtro);
		try {
			if (rs.next()) {
				do {
					listaOrdenes.add(new Orden(
							rs.getLong(1),
							rs.getString(2),
							rs.getInt(3),
							rs.getInt(4)));
				} while (rs.next());			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listaOrdenes;
	}
	
}
