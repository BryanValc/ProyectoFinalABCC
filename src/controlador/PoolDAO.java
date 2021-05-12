package controlador;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;

import conexionBD.ConexionBD;
import modelo.Pool;

public class PoolDAO {
	
	private static PoolDAO poolDAO=null;
	
	private PoolDAO() {
		
	}
	public static synchronized PoolDAO getInstance() {
		if (poolDAO == null) {
			poolDAO = new PoolDAO();
		}
		return poolDAO;
	}
	
	public boolean insertarRegistro(Pool p) {
		boolean resultado = false;
		resultado = ConexionBD.agregarRegistro(p);
		return resultado;
	}
	
	public boolean eliminarRegistro(String poolId) {
		boolean resultado = false;
		
		String sql="DELETE FROM Pool WHERE poolId = '"+poolId+"'";
		resultado = ConexionBD.eliminarRegistro(sql);
		
		return resultado;
	}
	
	public boolean modificarRegistro(Pool p, boolean flags[]) {
		boolean resultado = false;
		resultado = ConexionBD.actualizarRegistro(p);
		return resultado;
	}

	public ArrayList<Pool> buscarPools(String filtro){
		ArrayList<Pool> listaPools = new ArrayList<Pool>();
		
		ResultSet rs;
		
		rs = ConexionBD.ejecutarConsulta(filtro);
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
