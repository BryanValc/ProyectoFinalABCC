package modelo;

public class Comprador {
	int compradorId;
	String nombre;
	String wallet;
	String direccion;
	String ciudad;
	String estado;
	String telefono;
	String email;
	
	public Comprador(int compradorId, String nombre, String wallet, String direccion, String ciudad, String estado,
			String telefono, String email) {
		this.compradorId = compradorId;
		this.nombre = nombre;
		this.wallet = wallet;
		this.direccion = direccion;
		this.ciudad = ciudad;
		this.estado = estado;
		this.telefono = telefono;
		this.email = email;
	}
	
	public int getCompradorId() {
		return compradorId;
	}
	public void setCompradorId(int compradorId) {
		this.compradorId = compradorId;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getWallet() {
		return wallet;
	}
	public void setWallet(String wallet) {
		this.wallet = wallet;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Comprador [compradorId=" + compradorId + ", nombre=" + nombre + ", wallet=" + wallet + ", direccion="
				+ direccion + ", ciudad=" + ciudad + ", estado=" + estado + ", telefono=" + telefono + ", email="
				+ email + "]";
	}
	
}
