package controlador;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conexionBD.ConexionBD;
import modelo.OrdenDePotencia;

class ConsultaOrdenesDePotencia implements Runnable{
	ArrayList<OrdenDePotencia> listaOrdenesDePotencia = new ArrayList<OrdenDePotencia>();
	String filtro;
	
	public ConsultaOrdenesDePotencia(String filtro) {
		this.filtro = filtro;
	}

	@Override
	public void run() {
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
	}

	public ArrayList<OrdenDePotencia> getListaOrdenesDePotencia() {
		return listaOrdenesDePotencia;
	}

	public void setListaOrdenesDePotencia(ArrayList<OrdenDePotencia> listaOrdenesDePotencia) {
		this.listaOrdenesDePotencia = listaOrdenesDePotencia;
	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}
	
}

public class OrdenDePotenciaDAO {
	
	private static OrdenDePotenciaDAO ordenDePotenciaDAO=null;
	
	private OrdenDePotenciaDAO() {
		
	}
	
	public static synchronized OrdenDePotenciaDAO getInstance(){
		if (ordenDePotenciaDAO==null) {
			ordenDePotenciaDAO = new OrdenDePotenciaDAO();
		}
		return ordenDePotenciaDAO;
	}
	
	public boolean insertarRegistro(OrdenDePotencia o) {
		boolean resultado = false;
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
		resultado = ConexionBD.actualizarRegistro(o);
		return resultado;
	}

	public synchronized ArrayList<OrdenDePotencia> buscarOrdenesDePotencia(String filtro){
		ConsultaOrdenesDePotencia codp = new ConsultaOrdenesDePotencia(filtro);
		Thread h1 = new Thread(codp);
		h1.start();
		try {
			h1.join();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return codp.getListaOrdenesDePotencia();
	}

}
