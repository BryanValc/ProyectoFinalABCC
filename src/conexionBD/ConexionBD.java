package conexionBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import modelo.*;

public class ConexionBD {
	
	private static PreparedStatement pstm;
	private static ResultSet rs;
	
	private static ConexionBD conexionBD;
	private static Connection conexion= null;
	
	private ConexionBD() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			String URL = "jdbc:mysql://localhost:3306/NiceHash";
			
			conexion = DriverManager.getConnection(URL,"root","c1s1g7o");
			
			System.out.println("Conexion establecida");
			
		} catch (ClassNotFoundException e) {
			System.out.printf("Error de Driver");
		} catch (SQLException e) {
			System.out.printf("Error de conexion a MySQL o de la BD");
		}
	}
	
	public static synchronized ConexionBD getInstance() {
		if (conexionBD == null) {
			new ConexionBD();
		}
		return conexionBD;
	}
	
	public static Connection getConexion() {
		if (conexion == null) {
			new ConexionBD();
		}
		return conexion;
	}
	
	static void cerrarConnexion() {
		try {
			pstm.close();
			conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static boolean eliminarRegistro(String sql) {
		try {
			String consulta = sql;
		    pstm = conexion.prepareStatement(consulta);
	        pstm.executeUpdate();
	        return true;
	 } catch (Exception ex) {
		 
	 }
	 return false;
	}
	
	public static ResultSet ejecutarConsulta(String sql) {
		try {
			String consulta = sql;
			pstm = conexion.prepareStatement(consulta);
			return pstm.executeQuery();
		} catch (Exception e) {
			
		}
		return null;
	}
	
	public static boolean actualizarRegistro(Comprador comprador) {
		try {
			pstm = conexion.prepareStatement("UPDATE Comprador SET nombre=?,wallet=?,direccion=?,ciudad=?,estado=?,telefono=?,email=? WHERE compradorId="+comprador.getCompradorId()+"");
			pstm.setString(1, comprador.getNombre());
			pstm.setString(2, comprador.getWallet());
			pstm.setString(3, comprador.getDireccion());
			pstm.setString(4, comprador.getCiudad());
			pstm.setString(5, comprador.getEstado());
			pstm.setString(6, comprador.getTelefono());
			pstm.setString(7, comprador.getEmail());
			
			pstm.executeUpdate();
			return true;
		} catch (Exception e) {
		}
		return false;
	}
	public static boolean actualizarRegistro(Contratista contratista) {
		try {
			pstm = conexion.prepareStatement("UPDATE Contratista SET nombreContratista=?,mesesOperando=? WHERE contratistaId="+contratista.getContratistaId()+"");
			pstm.setString(1, contratista.getNombreContratista());
			pstm.setInt(2, contratista.getMesesOperando());
			
			pstm.executeUpdate();
			return true;
		} catch (Exception e) {
		}
		return false;
	}
	public static boolean actualizarRegistro(Criptomoneda criptomoneda) {
		try {
			pstm = conexion.prepareStatement("UPDATE Criptomoneda SET precioUnidad=?,descripcionUnidad=? WHERE criptomonedaId='"+criptomoneda.getCriptomonedaId()+"'");
			pstm.setDouble(1, criptomoneda.getPrecioUnidad());
			pstm.setString(2, criptomoneda.getDescripcionUnidad());
			
			pstm.executeUpdate();
			return true;
		} catch (Exception e) {
		}
		return false;
	}
	public static boolean actualizarRegistro(Pool pool) {
		try {
			pstm = conexion.prepareStatement("UPDATE Pool SET potenciaDeMinadoMHs=?,cantidadDeTrabajadores=?,cantidadDeMineros=? WHERE poolId='"+pool.getPoolId()+"'");
			pstm.setLong(1, pool.getPotenciaDeMinadoMHs());
			pstm.setInt(2, pool.getCantidadDeTrabajadores());
			pstm.setInt(3, pool.getCantidadDeMineros());
			
			pstm.executeUpdate();
			return true;
		} catch (Exception e) {
		}
		return false;
	}
	public static boolean actualizarRegistro(Orden orden) {
		try {
			pstm = conexion.prepareStatement("UPDATE Orden SET fechaOrden=?,compradorId=?,horasDeOperacion=? WHERE ordenId="+orden.getOrdenId()+"");
			pstm.setString(1, orden.getFechaOrden());
			pstm.setInt(2, orden.getCompradorId());
			pstm.setInt(3, orden.getHorasDeOperacion());
			
			pstm.executeUpdate();
			return true;
		} catch (Exception e) {
		}
		return false;
	}
	public static boolean actualizarRegistro(OrdenDePotencia ordenDePotencia) {
		try {
			pstm = conexion.prepareStatement("UPDATE OrdenDePotencia SET ordenId=?,criptomonedaId=?,contratistaId=?,poolId=?,cantidadDeCriptomonedas=?,precioFiat=? WHERE compraId="+ordenDePotencia.getCompraId()+"");
			pstm.setLong(1, ordenDePotencia.getOrdenId());
			pstm.setString(2, ordenDePotencia.getCriptomonedaId());
			pstm.setInt(3, ordenDePotencia.getContratistaId());
			pstm.setString(4, ordenDePotencia.getPoolId());
			pstm.setDouble(5, ordenDePotencia.getCantidadDeCriptomonedas());
			pstm.setDouble(6, ordenDePotencia.getPrecioFiat());
			
			pstm.executeUpdate();
			return true;
		} catch (Exception e) {
		}
		return false;
	}
	
	public static boolean agregarRegistro(Comprador comprador) {
		try {
			pstm = conexion.prepareStatement("INSERT INTO Comprador VALUES("+comprador.getCompradorId()+",?,?,?,?,?,?,?)");
			pstm.setString(1, comprador.getNombre());
			pstm.setString(2, comprador.getWallet());
			pstm.setString(3, comprador.getDireccion());
			pstm.setString(4, comprador.getCiudad());
			pstm.setString(5, comprador.getEstado());
			pstm.setString(6, comprador.getTelefono());
			pstm.setString(7, comprador.getEmail());
			
			pstm.executeUpdate();
			return true;
		} catch (Exception e) {
		}
		return false;
	}
	public static boolean agregarRegistro(Contratista contratista) {
		try {
			pstm = conexion.prepareStatement("INSERT INTO Contratista VALUES("+contratista.getContratistaId()+",?,?)");
			pstm.setString(1, contratista.getNombreContratista());
			pstm.setInt(2, contratista.getMesesOperando());
			
			pstm.executeUpdate();
			return true;
		} catch (Exception e) {
		}
		return false;
	}
	public static boolean agregarRegistro(Criptomoneda criptomoneda) {
		try {
			pstm = conexion.prepareStatement("INSERT INTO Criptomoneda VALUES('"+criptomoneda.getCriptomonedaId()+"',?,?)");
			pstm.setDouble(1, criptomoneda.getPrecioUnidad());
			pstm.setString(2, criptomoneda.getDescripcionUnidad());
			
			pstm.executeUpdate();
			return true;
		} catch (Exception e) {
		}
		return false;
	}
	public static boolean agregarRegistro(Pool pool) {
		try {
			pstm = conexion.prepareStatement("INSERT INTO Pool VALUES('"+pool.getPoolId()+"',?,?,?)");
			pstm.setLong(1, pool.getPotenciaDeMinadoMHs());
			pstm.setInt(2, pool.getCantidadDeTrabajadores());
			pstm.setInt(3, pool.getCantidadDeMineros());
			
			pstm.executeUpdate();
			return true;
		} catch (Exception e) {
		}
		return false;
	}
	public static boolean agregarRegistro(Orden orden) {
		try {
			pstm = conexion.prepareStatement("INSERT INTO Orden VALUES("+orden.getOrdenId()+",?,?,?)");
			pstm.setString(1, orden.getFechaOrden());
			pstm.setInt(2, orden.getCompradorId());
			pstm.setInt(3, orden.getHorasDeOperacion());
			
			pstm.executeUpdate();
			return true;
		} catch (Exception e) {
		}
		return false;
	}
	public static boolean agregarRegistro(OrdenDePotencia ordenDePotencia) {
		try {
			pstm = conexion.prepareStatement("INSERT INTO OrdenDePotencia VALUES("+ordenDePotencia.getCompraId()+",?,?,?,?,?,?)");
			pstm.setLong(1, ordenDePotencia.getOrdenId());
			pstm.setString(2, ordenDePotencia.getCriptomonedaId());
			pstm.setInt(3, ordenDePotencia.getContratistaId());
			pstm.setString(4, ordenDePotencia.getPoolId());
			pstm.setDouble(5, ordenDePotencia.getCantidadDeCriptomonedas());
			pstm.setDouble(6, ordenDePotencia.getPrecioFiat());
			
			pstm.executeUpdate();
			return true;
		} catch (Exception e) {
		}
		return false;
	}
	
	
}//class
