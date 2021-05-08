package controlador;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;

import conexionBD.ConexionBD;
import modelo.Comprador;
import modelo.Contratista;

public class ContratistaDAO {

	ConexionBD conexion;
	
	public ContratistaDAO() {
		conexion = new ConexionBD();
	}
	
	public boolean insertarRegistro(Contratista c) {
		boolean resultado = false;
		
		String sql="INSERT INTO Contratista VALUES("
				+ c.getContratistaId()+","
				+ "'"+c.getNombreContratista()+"',"
				+ +c.getMesesOperando()
				+ ")";
		resultado = conexion.ejecutarInstruccion(sql);
		
		return resultado;
	}
	
	public boolean eliminarRegistro(int contratistaId) {
		boolean resultado = false;
		
		String sql="DELETE FROM Contratista WHERE contratistaId = "+contratistaId;
		resultado = conexion.ejecutarInstruccion(sql);
		
		return resultado;
	}
	
	public boolean modificarRegistro(Contratista c, boolean flags[]) {
		boolean resultado = false;
		boolean primero=true;
		
		String sql = "UPDATE Contratista SET ";
		
		if (flags[0]) {
			if (!primero) {sql+=", ";
			}else {primero = false;}
			sql+=("nombreContratista='"+c.getNombreContratista()+"'");
		}
		if (flags[1]) {
			if (!primero) {sql+=", ";
			}else {primero = false;}
			sql+=("mesesOperando= "+c.getMesesOperando());
		}
		
		sql+=(" WHERE contratistaId = "+c.getContratistaId());
		resultado = conexion.ejecutarInstruccion(sql);
		
		return resultado;
	}
	
	public ArrayList<Contratista> buscarContratistas(String filtro){
		ArrayList<Contratista> listaContratistas = new ArrayList<Contratista>();
		
		ResultSet rs;
		
		rs = conexion.ejecutarConsulta(filtro);
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
