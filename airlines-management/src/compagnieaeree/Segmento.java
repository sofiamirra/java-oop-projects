package compagnieaeree;

public class Segmento {
	int numeroBiglietto;
	String siglaVolo;
	String data;
	String classe;
	double tariffa;
	double tasse;
	double durata;
	
	public Segmento(int numeroBiglietto, String siglaVolo, String data, String classe, double tariffa, double tasse, double durata) {
		this.numeroBiglietto = numeroBiglietto;
		this.siglaVolo = siglaVolo;
		this.data = data;
		this.classe = classe;
		this.tariffa = tariffa;
		this.tasse = tasse;
		this.durata = durata;
	}

	public int getNumeroBiglietto() {
		return numeroBiglietto;
	}

	public String getSiglaVolo() {
		return siglaVolo;
	}

	public String getData() {
		return data;
	}

	public String getClasse() {
		return classe;
	}

	public double getTariffa() {
		return tariffa;
	}

	public double getTasse() {
		return tasse;
	}

	public double getDurata() {
		return durata;
	}
	

}
