package controlador;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;

import conexionBD.ConexionBD;
import modelo.Contratista;

class ConsultaContratistas implements Runnable{
	ArrayList<Contratista> listaContratistas = new ArrayList<Contratista>();
	String filtro;
	
	public ConsultaContratistas(String filtro) {
		this.filtro = filtro;
	}

	@Override
	public void run() {
		ResultSet rs;
		
		rs = ConexionBD.ejecutarConsulta(filtro);
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
	}

	public ArrayList<Contratista> getListaContratistas() {
		return listaContratistas;
	}

	public void setListaContratistas(ArrayList<Contratista> listaContratistas) {
		this.listaContratistas = listaContratistas;
	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}
	
	
	
}


public class ContratistaDAO{
	
	private static ContratistaDAO contratistaDAO=null;
	
	private ContratistaDAO() {
		
	}
	
	public static synchronized ContratistaDAO getInstance() {
		if (contratistaDAO==null) {
			contratistaDAO=new ContratistaDAO();
		}
		return contratistaDAO;
	}
	
	public boolean insertarRegistro(Contratista c) {
		boolean resultado = false;
		resultado = ConexionBD.agregarRegistro(c);
		return resultado;
	}
	
	public boolean eliminarRegistro(int contratistaId) {
		boolean resultado = false;
		String sql="DELETE FROM Contratista WHERE contratistaId = "+contratistaId;
		resultado = ConexionBD.eliminarRegistro(sql);
		return resultado;
	}
	
	public boolean modificarRegistro(Contratista c, boolean flags[]) {
		boolean resultado = false;
		resultado = ConexionBD.actualizarRegistro(c);
		return resultado;
	}
	
	public synchronized ArrayList<Contratista> buscarContratistas(String filtro){
		ConsultaContratistas cc = new ConsultaContratistas(filtro);
		Thread h1 = new Thread(cc);
		h1.start();
		try {
			h1.join();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cc.getListaContratistas();
	}
	
}
