package controlador;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;

import conexionBD.ConexionBD;
import modelo.Orden;

public class OrdenDAO {

	/*ConexionBD conexion;
	
	public OrdenDAO() {
		conexion = new ConexionBD();
	}*/
	
	public boolean insertarRegistro(Orden o) {
		boolean resultado = false;
		
		/*String sql="INSERT INTO Orden VALUES("
				+ o.getOrdenId()+","
				+ "'"+o.getFechaOrden()+"',"
				+o.getCompradorId()+","
				+o.getHorasDeOperacion()
				+ ")";*/
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
		
		/*String sql = "UPDATE Orden SET ";
		
		if (flags[0]) {
			if (!primero) {sql+=", ";
			}else {primero = false;}
			sql+=("fechaOrden='"+o.getFechaOrden()+"'");
		}
		if (flags[1]) {
			if (!primero) {sql+=", ";
			}else {primero = false;}
			sql+=("compradorId="+o.getCompradorId());
		}
		if (flags[2]) {
			if (!primero) {sql+=", ";
			}else {primero = false;}
			sql+=("horasDeOperacion="+o.getHorasDeOperacion());
		}
		sql+=(" WHERE ordenId = "+o.getOrdenId());*/
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
