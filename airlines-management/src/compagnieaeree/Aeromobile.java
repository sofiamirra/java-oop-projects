package compagnieaeree;

public class Aeromobile implements Comparable <Aeromobile>{
	String siglaModello;
	int quantita;
	int autonomia;
	int numPostiBusiness;
	int numPostiEconomy; // posti disponibili (inizialmente tutti)
	int postiComplessivi;
	
	public Aeromobile(String siglaModello, int quantita, int autonomia, int numPostiBusiness, int numPostiEconomy) {
		this.siglaModello = siglaModello;
		this.quantita = quantita;
		this.autonomia = autonomia;
		this.numPostiBusiness = numPostiBusiness;
		this.numPostiEconomy = numPostiEconomy;
	}

	public String getSiglaModello() {
		return siglaModello;
	}

	public int getQuantita() {
		return quantita;
	}

	public int getAutonomia() {
		return autonomia;
	}

	// Restituice il numero dei posti disponibili in base alla stringa passata
	public int numeroPostiPerClasse(String classe) {
		if (classe.equals("Business")) {
			return numPostiBusiness;
		} else if (classe.equals("Economy")) {
		return numPostiEconomy;
		}
		return 0;
	}

	public int getPostiComplessivi() {
		return postiComplessivi;
	}

	public void setPostiComplessivi(int postiComplessivi) {
		this.postiComplessivi = postiComplessivi;
	}
	
	public void setNumPostiBusiness(int numPostiBusiness) {
		this.numPostiBusiness = numPostiBusiness;
	}

	public void setNumPostiEconomy(int numPostiEconomy) {
		this.numPostiEconomy = numPostiEconomy;
	}

	@Override // in ordine decrescente
	public int compareTo(Aeromobile o) {
		return - (this.getPostiComplessivi() - o.getPostiComplessivi());
	}
	
	

}
