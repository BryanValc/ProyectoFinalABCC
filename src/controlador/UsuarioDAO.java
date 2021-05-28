package controlador;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conexionBD.ConexionBD;
import modelo.Usuario;

class ConsultaUsuarios implements Runnable{
	ArrayList<Usuario> listaUsuarios = new ArrayList<Usuario>();
	String filtro;
	
	public ConsultaUsuarios(String filtro) {
		this.filtro = filtro;
	}
	
	@Override
	public void run() {
		ResultSet rs;
		
		rs = ConexionBD.ejecutarConsulta(filtro);
		try {
			if (rs.next()) {
				do {
					listaUsuarios.add(new Usuario(
							rs.getString(1),
							rs.getString(2)
							));
				} while (rs.next());}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Usuario> getListaUsuarios() {
		return listaUsuarios;
	}

	public void setListaUsuarios(ArrayList<Usuario> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}
	
}

public class UsuarioDAO {
	
	private static UsuarioDAO usuarioDAO=null;
	
	private UsuarioDAO() {
		
	}
	
	public static synchronized UsuarioDAO getInstance() {
		if (usuarioDAO == null) {
			usuarioDAO = new UsuarioDAO();
		}
		return usuarioDAO;
	}
	
	public synchronized ArrayList<Usuario> buscarUsuarios(String filtro){
		ConsultaUsuarios cu = new ConsultaUsuarios(filtro);
		Thread h1 = new Thread(cu);
		h1.start();
		try {
			h1.join();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cu.getListaUsuarios();
	}
	
	
	
}
