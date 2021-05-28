package controlador;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conexionBD.ConexionBD;
import modelo.Orden;

class ConsultaOrdenes implements Runnable{
	ArrayList<Orden> listaOrdenes = new ArrayList<Orden>();
	String filtro;
	
	public ConsultaOrdenes(String filtro) {
		this.filtro = filtro;
	}

	@Override
	public void run() {
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
	}

	public ArrayList<Orden> getListaOrdenes() {
		return listaOrdenes;
	}

	public void setListaOrdenes(ArrayList<Orden> listaOrdenes) {
		this.listaOrdenes = listaOrdenes;
	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}
	
}

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
		resultado = ConexionBD.actualizarRegistro(o);
		return resultado;
	}
	
	public synchronized ArrayList<Orden> buscarOrdenes(String filtro){
		ConsultaOrdenes co = new ConsultaOrdenes(filtro);
		Thread h1 = new Thread(co);
		h1.start();
		try {
			h1.join();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return co.getListaOrdenes();
	}
	
}
