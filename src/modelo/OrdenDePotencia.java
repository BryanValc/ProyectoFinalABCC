package modelo;

public class OrdenDePotencia {

	long compraId;
	long ordenId;
	String criptomonedaId;
	int contratistaId;
	String poolId;
	double cantidadDeCriptomonedas;
	double precioFiat;
	
	public OrdenDePotencia(long compraId, long ordenId, String criptomonedaId, int contratistaId, String poolId,
			double cantidadDeCriptomonedas, double precioFiat) {
		this.compraId = compraId;
		this.ordenId = ordenId;
		this.criptomonedaId = criptomonedaId;
		this.contratistaId = contratistaId;
		this.poolId = poolId;
		this.cantidadDeCriptomonedas = cantidadDeCriptomonedas;
		this.precioFiat = precioFiat;
	}

	public long getCompraId() {
		return compraId;
	}

	public void setCompraId(long compraId) {
		this.compraId = compraId;
	}

	public long getOrdenId() {
		return ordenId;
	}

	public void setOrdenId(long ordenId) {
		this.ordenId = ordenId;
	}

	public String getCriptomonedaId() {
		return criptomonedaId;
	}

	public void setCriptomonedaId(String criptomonedaId) {
		this.criptomonedaId = criptomonedaId;
	}

	public int getContratistaId() {
		return contratistaId;
	}

	public void setContratistaId(int contratistaId) {
		this.contratistaId = contratistaId;
	}

	public String getPoolId() {
		return poolId;
	}

	public void setPoolId(String poolId) {
		this.poolId = poolId;
	}

	public double getCantidadDeCriptomonedas() {
		return cantidadDeCriptomonedas;
	}

	public void setCantidadDeCriptomonedas(double cantidadDeCriptomonedas) {
		this.cantidadDeCriptomonedas = cantidadDeCriptomonedas;
	}

	public double getPrecioFiat() {
		return precioFiat;
	}

	public void setPrecioFiat(double precioFiat) {
		this.precioFiat = precioFiat;
	}

	@Override
	public String toString() {
		return "OrdenDePotencia [compraId=" + compraId + ", ordenId=" + ordenId + ", criptomonedaId=" + criptomonedaId
				+ ", contratistaId=" + contratistaId + ", poolId=" + poolId + ", cantidadDeCriptomonedas="
				+ cantidadDeCriptomonedas + ", precioFiat=" + precioFiat + "]";
	}
	
}
