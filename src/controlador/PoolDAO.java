package controlador;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;

import conexionBD.ConexionBD;
import modelo.Pool;

public class PoolDAO {
	
ConexionBD conexion;
	
	public PoolDAO() {
		conexion = new ConexionBD();
	}
	
	public boolean insertarRegistro(Pool p) {
		boolean resultado = false;
		
		String sql="INSERT INTO Pool VALUES("
				+ "'"+p.getPoolId()+"',"
				+ p.getPotenciaDeMinadoMHs()+","
				+ p.getCantidadDeTrabajadores()+","
				+ p.getCantidadDeMineros()+""
				+ ")";
		resultado = conexion.ejecutarInstruccion(sql);
		
		return resultado;
	}
	
	public boolean eliminarRegistro(String poolId) {
		boolean resultado = false;
		
		String sql="DELETE FROM Pool WHERE poolId = '"+poolId+"'";
		resultado = conexion.ejecutarInstruccion(sql);
		
		return resultado;
	}
	
	public boolean modificarRegistro(Pool p, boolean flags[]) {
		boolean resultado = false;
		boolean primero=true;
		
		String sql = "UPDATE Pool SET ";
		
		if (flags[0]) {
			if (!primero) {sql+=", ";
			}else {primero = false;}
			sql+=("potenciaDeMinadoMHs="+p.getPotenciaDeMinadoMHs());
		}
		if (flags[1]) {
			if (!primero) {sql+=", ";
			}else {primero = false;}
			sql+=("cantidadDeTrabajadores="+p.getCantidadDeTrabajadores());
		}
		if (flags[2]) {
			if (!primero) {sql+=", ";
			}else {primero = false;}
			sql+=("cantidadDeMineros="+p.getCantidadDeMineros());
		}
		
		sql+=(" WHERE poolId = '"+p.getPoolId()+"'");
		resultado = conexion.ejecutarInstruccion(sql);
		
		return resultado;
	}

	public ArrayList<Pool> buscarPools(String filtro){
		ArrayList<Pool> listaPools = new ArrayList<Pool>();
		
		ResultSet rs;
		
		rs = conexion.ejecutarConsulta(filtro);
		try {
			if (rs.next()) {
				do {
					listaPools.add(new Pool(
							rs.getString(1),
							rs.getLong(2),
							rs.getInt(3),
							rs.getInt(4)));
				} while (rs.next());			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listaPools;
	}

}
