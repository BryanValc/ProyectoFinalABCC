package controlador;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;

import conexionBD.ConexionBD;
import modelo.OrdenDePotencia;

public class OrdenDePotenciaDAO {
	
	/*ConexionBD conexion;
	
	public OrdenDePotenciaDAO() {
		conexion = new ConexionBD();
	}*/
	
	public boolean insertarRegistro(OrdenDePotencia o) {
		boolean resultado = false;
		
		/*String sql="INSERT INTO OrdenDePotencia VALUES("
				+ o.getCompraId()+","
				+ o.getOrdenId()+","
				+ "'"+o.getCriptomonedaId()+"',"
				+ o.getContratistaId()+","
				+ "'"+o.getPoolId()+"',"
				+ o.getCantidadDeCriptomonedas()+","
				+ o.getPrecioFiat()
				+ ")";*/
		resultado = ConexionBD.agregarRegistro(o);
		
		return resultado;
	}
	
	public boolean eliminarRegistro(long compraId) {
		boolean resultado = false;
		
		String sql="DELETE FROM OrdenDePotencia WHERE compraId = "+compraId;
		resultado = ConexionBD.eliminarRegistro(sql);
		
		return resultado;
	}
	
	public boolean modificarRegistro(OrdenDePotencia o, boolean flags[]) {
		boolean resultado = false;
		/*boolean primero=true;
		
		String sql = "UPDATE OrdenDePotencia SET ";
		
		if (flags[0]) {
			if (!primero) {sql+=", ";
			}else {primero = false;}
			sql+=("ordenId="+o.getOrdenId());
		}
		if (flags[1]) {
			if (!primero) {sql+=", ";
			}else {primero = false;}
			sql+=("criptomonedaId='"+o.getCriptomonedaId()+"'");
		}
		if (flags[2]) {
			if (!primero) {sql+=", ";
			}else {primero = false;}
			sql+=("contratistaId="+o.getContratistaId());
		}
		if (flags[3]) {
			if (!primero) {sql+=", ";
			}else {primero = false;}
			sql+=("poolId='"+o.getPoolId()+"'");
		}
		if (flags[4]) {
			if (!primero) {sql+=", ";
			}else {primero = false;}
			sql+=("cantidadDeCriptomonedas="+o.getCantidadDeCriptomonedas());
		}
		if (flags[5]) {
			if (!primero) {sql+=", ";
			}else {primero = false;}
			sql+=("precioFiat="+o.getPrecioFiat());
		}
		
		sql+=(" WHERE compraId = "+o.getCompraId());*/
		resultado = ConexionBD.actualizarRegistro(o);
		
		return resultado;
	}

	public ArrayList<OrdenDePotencia> buscarOrdenesDePotencia(String filtro){
		ArrayList<OrdenDePotencia> listaOrdenesDePotencia = new ArrayList<OrdenDePotencia>();
		
		ResultSet rs;
		
		rs = ConexionBD.ejecutarConsulta(filtro);
		try {
			if (rs.next()) {
				do {
					listaOrdenesDePotencia.add(new OrdenDePotencia(
							rs.getLong(1),
							rs.getLong(2),
							rs.getString(3),
							rs.getInt(4),
							rs.getString(5),
							rs.getDouble(6),
							rs.getDouble(7)));
				} while (rs.next());			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listaOrdenesDePotencia;
	}

}
