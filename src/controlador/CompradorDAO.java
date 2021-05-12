package controlador;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;

import conexionBD.ConexionBD;
import modelo.Comprador;

public class CompradorDAO {
	
	/*ConexionBD conexion;
	
	public CompradorDAO() {
		conexion = new ConexionBD();
	}*/
	
	public boolean insertarRegistro(Comprador c) {
		boolean resultado = false;
		
		/*String sql="INSERT INTO Comprador VALUES("
				+ c.getCompradorId()+","
				+ "'"+c.getNombre()+"',"
				+ "'"+c.getWallet()+"',"
				+ "'"+c.getDireccion()+"',"
				+ "'"+c.getCiudad()+"',"
				+ "'"+c.getEstado()+"',"
				+ "'"+c.getTelefono()+"',"
				+ "'"+c.getEmail()+"'"
				+ ")";*/
		
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
		boolean primero=true;
		
		/*String sql = "UPDATE Comprador SET ";
		
		if (flags[0]) {
			if (!primero) {sql+=", ";
			}else {primero = false;}
			sql+=("nombre='"+c.getNombre()+"'");
		}
		if (flags[1]) {
			if (!primero) {sql+=", ";
			}else {primero = false;}
			sql+=("wallet='"+c.getWallet()+"'");
		}
		if (flags[2]) {
			if (!primero) {sql+=", ";
			}else {primero = false;}
			sql+=("direccion='"+c.getDireccion()+"'");
		}
		if (flags[3]) {
			if (!primero) {sql+=", ";
			}else {primero = false;}
			sql+=("ciudad='"+c.getCiudad()+"'");
		}
		if (flags[4]) {
			if (!primero) {sql+=", ";
			}else {primero = false;}
			sql+=("estado='"+c.getEstado()+"'");
		}
		if (flags[5]) {
			if (!primero) {sql+=", ";
			}else {primero = false;}
			sql+=("telefono='"+c.getTelefono()+"'");
		}
		if (flags[6]) {
			if (!primero) {sql+=", ";
			}else {primero = false;}
			sql+=("email='"+c.getEmail()+"'");
		}
		
		sql+=(" WHERE compradorId = "+c.getCompradorId());*/
		resultado = ConexionBD.actualizarRegistro(c);
		
		return resultado;
	}

	public ArrayList<Comprador> buscarCompradores(String filtro){
		ArrayList<Comprador> listaCompradores = new ArrayList<Comprador>();
		
		ResultSet rs;
		
		rs = ConexionBD.ejecutarConsulta(filtro);
		//SELECT * FROM Alumnos WHERE prod_name = 'Microsoft 10-20 Keyboard' AND prod_price < 30;
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
