package controlador;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;

import conexionBD.ConexionBD;
import modelo.Usuario;

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
	
	public ArrayList<Usuario> buscarUsuarios(String filtro){
		ArrayList<Usuario> listaUsuarios = new ArrayList<Usuario>();
		
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
		
		return listaUsuarios;
	}
	
	
	
}
