package modelo;

public class Criptomoneda {

	String criptomonedaId;
	double precioUnidad;
	String descripcionUnidad;
	
	public Criptomoneda(String criptomonedaId, double precioUnidad, String descripcionUnidad) {
		this.criptomonedaId = criptomonedaId;
		this.precioUnidad = precioUnidad;
		this.descripcionUnidad = descripcionUnidad;
	}

	public String getCriptomonedaId() {
		return criptomonedaId;
	}

	public void setCriptomonedaId(String criptomonedaId) {
		this.criptomonedaId = criptomonedaId;
	}

	public double getPrecioUnidad() {
		return precioUnidad;
	}

	public void setPrecioUnidad(double precioUnidad) {
		this.precioUnidad = precioUnidad;
	}

	public String getDescripcionUnidad() {
		return descripcionUnidad;
	}

	public void setDescripcionUnidad(String descripcionUnidad) {
		this.descripcionUnidad = descripcionUnidad;
	}

	@Override
	public String toString() {
		return "Criptomoneda [criptomonedaId=" + criptomonedaId + ", precioUnidad=" + precioUnidad
				+ ", descripcionUnidad=" + descripcionUnidad + "]";
	}
	
	
	
	
}
