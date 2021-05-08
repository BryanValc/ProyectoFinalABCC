package modelo;

public class Pool {

	String poolId;
	long potenciaDeMinadoMHs;
	int cantidadDeTrabajadores;
	int cantidadDeMineros;
	
	public Pool(String poolId, long potenciaDeMinadoMHs, int cantidadDeTrabajadores, int cantidadDeMineros) {
		this.poolId = poolId;
		this.potenciaDeMinadoMHs = potenciaDeMinadoMHs;
		this.cantidadDeTrabajadores = cantidadDeTrabajadores;
		this.cantidadDeMineros = cantidadDeMineros;
	}

	public String getPoolId() {
		return poolId;
	}

	public void setPoolId(String poolId) {
		this.poolId = poolId;
	}

	public long getPotenciaDeMinadoMHs() {
		return potenciaDeMinadoMHs;
	}

	public void setPotenciaDeMinadoMHs(long potenciaDeMinadoMHs) {
		this.potenciaDeMinadoMHs = potenciaDeMinadoMHs;
	}

	public int getCantidadDeTrabajadores() {
		return cantidadDeTrabajadores;
	}

	public void setCantidadDeTrabajadores(int cantidadDeTrabajadores) {
		this.cantidadDeTrabajadores = cantidadDeTrabajadores;
	}

	public int getCantidadDeMineros() {
		return cantidadDeMineros;
	}

	public void setCantidadDeMineros(int cantidadDeMineros) {
		this.cantidadDeMineros = cantidadDeMineros;
	}

	@Override
	public String toString() {
		return "Pool [poolId=" + poolId + ", potenciaDeMinadoMHs=" + potenciaDeMinadoMHs + ", cantidadDeTrabajadores="
				+ cantidadDeTrabajadores + ", cantidadDeMineros=" + cantidadDeMineros + "]";
	}
	
}
