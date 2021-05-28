package controlador;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conexionBD.ConexionBD;
import modelo.Criptomoneda;

class ConsultaCriptomonedas implements Runnable{
	ArrayList<Criptomoneda> listaCriptomonedas = new ArrayList<Criptomoneda>();
	String filtro;
	
	public ConsultaCriptomonedas(String filtro) {
		this.filtro = filtro;
	}

	@Override
	public void run() {
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
		
	}

	public ArrayList<Criptomoneda> getListaCriptomonedas() {
		return listaCriptomonedas;
	}

	public void setListaCriptomonedas(ArrayList<Criptomoneda> listaCriptomonedas) {
		this.listaCriptomonedas = listaCriptomonedas;
	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}
	
}

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
	
	public synchronized ArrayList<Criptomoneda> buscarCriptomonedas(String filtro){
		ConsultaCriptomonedas cc = new ConsultaCriptomonedas(filtro);
		Thread h1 = new Thread(cc);
		h1.start();
		try {
			h1.join();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cc.getListaCriptomonedas();
	}
	
}
