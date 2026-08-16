package compagnieaeree;

public class Volo {
	String sigla;
	Aeroporto aeroportoPartenza;
	Aeroporto aeroportoArrivo;
	String dataPartenza;
	String oraPartenza;
	String dataArrivo;
	String oraArrivo;
	Aeromobile aeromobile; 
	
	String dataPartenzaEffettiva;
	String oraPartenzaEffettiva;
	String dataArrivoEffettiva;
	String oraArrivoEffettiva;
	boolean decollato;
	boolean atterrato;
	double minutiRitardo;
	
	public Volo(String sigla, Aeroporto aeroportoPartenza, Aeroporto aeroportoArrivo, String dataPartenza,
			String oraPartenza, String dataArrivo, String oraArrivo, Aeromobile aeromobile) {
		this.sigla = sigla;
		this.aeroportoPartenza = aeroportoPartenza;
		this.aeroportoArrivo = aeroportoArrivo;
		this.dataPartenza = dataPartenza;
		this.oraPartenza = oraPartenza;
		this.dataArrivo = dataArrivo;
		this.oraArrivo = oraArrivo;
		this.aeromobile = aeromobile;
	}

	public String getSigla() {
		return sigla;
	}

	public Aeroporto getAeroportoPartenza() {
		return aeroportoPartenza;
	}

	public Aeroporto getAeroportoArrivo() {
		return aeroportoArrivo;
	}

	public String getDataPartenza() {
		return dataPartenza;
	}

	public String getOraPartenza() {
		return oraPartenza;
	}

	public String getDataArrivo() {
		return dataArrivo;
	}

	public String getOraArrivo() {
		return oraArrivo;
	}

	public Aeromobile getAeromobile() {
		return aeromobile;
	}


	public void setDataPartenzaEffettiva(String dataPartenzaEffettiva) {
		this.dataPartenzaEffettiva = dataPartenzaEffettiva;
	}

	public void setOraPartenzaEffettiva(String oraPartenzaEffettiva) {
		this.oraPartenzaEffettiva = oraPartenzaEffettiva;
	}

	public void setDataArrivoEffettiva(String dataArrivoEffettiva) {
		this.dataArrivoEffettiva = dataArrivoEffettiva;
	}

	public void setOraArrivoEffettiva(String oraArrivoEffettiva) {
		this.oraArrivoEffettiva = oraArrivoEffettiva;
	}

	public String getDataPartenzaEffettiva() {
		return dataPartenzaEffettiva;
	}

	public String getOraPartenzaEffettiva() {
		return oraPartenzaEffettiva;
	}

	public String getDataArrivoEffettiva() {
		return dataArrivoEffettiva;
	}

	public String getOraArrivoEffettiva() {
		return oraArrivoEffettiva;
	}

	public boolean isAtterrato() {
		return atterrato;
	}

	public void setAtterrato(boolean atterrato) {
		this.atterrato = atterrato;
	}

	public double getMinutiRitardo() {
		return minutiRitardo;
	}

	public void setMinutiRitardo(double minutiRitardo) {
		this.minutiRitardo = minutiRitardo;
	}

	public boolean isDecollato() {
		return decollato;
	}

	public void setDecollato(boolean decollato) {
		this.decollato = decollato;
	}
	
	
	

}
