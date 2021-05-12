package controlador;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;

import conexionBD.ConexionBD;
import modelo.Criptomoneda;

public class CriptomonedaDAO {

	private static CriptomonedaDAO criptomonedaDAO=null;
	
	private CriptomonedaDAO() {
		
	}
	
	public static synchronized CriptomonedaDAO getInstance() {
		if (criptomonedaDAO==null) {
			criptomonedaDAO = new CriptomonedaDAO();
		}
		return criptomonedaDAO;
	}
	
	public boolean insertarRegistro(Criptomoneda c) {
		boolean resultado = false;
		resultado = ConexionBD.agregarRegistro(c);
		return resultado;
	}
	
	public boolean eliminarRegistro(String criptomonedaId) {
		boolean resultado = false;
		String sql="DELETE FROM Criptomoneda WHERE criptomonedaId = '"+criptomonedaId+"'";
		resultado = ConexionBD.eliminarRegistro(sql);
		return resultado;
	}
	
	public boolean modificarRegistro(Criptomoneda c, boolean flags[]) {
		boolean resultado = false;
		resultado = ConexionBD.actualizarRegistro(c);
		return resultado;
	}
	
	public ArrayList<Criptomoneda> buscarCriptomonedas(String filtro){
		ArrayList<Criptomoneda> listaCriptomonedas = new ArrayList<Criptomoneda>();
		
		ResultSet rs;
		
		rs = ConexionBD.ejecutarConsulta(filtro);
		try {
			if (rs.next()) {
				do {
					listaCriptomonedas.add(new Criptomoneda(
							rs.getString(1),
							rs.getDouble(2),
							rs.getString(3)));
				} while (rs.next());			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listaCriptomonedas;
	}
	
}
