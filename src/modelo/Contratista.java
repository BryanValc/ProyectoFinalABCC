package modelo;

public class Contratista {

	int contratistaId;
	String nombreContratista;
	int mesesOperando;
	
	public Contratista(int contratistaId, String nombreContratista, int mesesOperando) {
		this.contratistaId = contratistaId;
		this.nombreContratista = nombreContratista;
		this.mesesOperando = mesesOperando;
	}

	public int getContratistaId() {
		return contratistaId;
	}

	public void setContratistaId(int contratistaId) {
		this.contratistaId = contratistaId;
	}

	public String getNombreContratista() {
		return nombreContratista;
	}

	public void setNombreContratista(String nombreContratista) {
		this.nombreContratista = nombreContratista;
	}

	public int getMesesOperando() {
		return mesesOperando;
	}

	public void setMesesOperando(int mesesOperando) {
		this.mesesOperando = mesesOperando;
	}

	@Override
	public String toString() {
		return "Contratista [contratistaId=" + contratistaId + ", nombreContratista=" + nombreContratista
				+ ", mesesOperando=" + mesesOperando + "]";
	}
	
	
	
}
