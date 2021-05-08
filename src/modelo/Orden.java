package modelo;

public class Orden {

	long ordenId;
	String fechaOrden;
	int compradorId;
	int horasDeOperacion;
	
	public Orden(long ordenId, String fechaOrden, int compradorId, int horasDeOperacion) {
		this.ordenId = ordenId;
		this.fechaOrden = fechaOrden;
		this.compradorId = compradorId;
		this.horasDeOperacion = horasDeOperacion;
	}

	public long getOrdenId() {
		return ordenId;
	}

	public void setOrdenId(long ordenId) {
		this.ordenId = ordenId;
	}

	public String getFechaOrden() {
		return fechaOrden;
	}

	public void setFechaOrden(String fechaOrden) {
		this.fechaOrden = fechaOrden;
	}

	public int getCompradorId() {
		return compradorId;
	}

	public void setCompradorId(int compradorId) {
		this.compradorId = compradorId;
	}

	public int getHorasDeOperacion() {
		return horasDeOperacion;
	}

	public void setHorasDeOperacion(int horasDeOperacion) {
		this.horasDeOperacion = horasDeOperacion;
	}

	@Override
	public String toString() {
		return "Orden [ordenId=" + ordenId + ", fechaOrden=" + fechaOrden + ", compradorId=" + compradorId
				+ ", horasDeOperacion=" + horasDeOperacion + "]";
	}
	
}
