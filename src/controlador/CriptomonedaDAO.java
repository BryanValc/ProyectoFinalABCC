package controlador;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;

import conexionBD.ConexionBD;
import modelo.Comprador;
import modelo.Criptomoneda;

public class CriptomonedaDAO {

	/*ConexionBD conexion;
	
	public CriptomonedaDAO() {
		conexion = new ConexionBD();
	}*/
	
	public boolean insertarRegistro(Criptomoneda c) {
		boolean resultado = false;
		
		/*String sql="INSERT INTO Criptomoneda VALUES("
				+ "'"+c.getCriptomonedaId()+"',"
				+c.getPrecioUnidad()+","
				+ "'"+c.getDescripcionUnidad()+"'"
				+ ")";*/
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
		/*boolean primero = true;
		
		String sql = "UPDATE Criptomoneda SET ";
		
		if (flags[0]) {
			if (!primero) {sql+=", ";
			}else {primero = false;}
			sql+=("precioUnidad= "+c.getPrecioUnidad());
		}
		if (flags[1]) {
			if (!primero) {sql+=", ";
			}else {primero = false;}
			sql+=("descripcionUnidad='"+c.getDescripcionUnidad()+"'");
		}
		
		sql+=(" WHERE criptomonedaId = '"+c.getCriptomonedaId()+"'");*/
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
