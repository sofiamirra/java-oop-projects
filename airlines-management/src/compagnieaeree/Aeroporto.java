package compagnieaeree;

public class Aeroporto implements Comparable <Aeroporto>{
	String sigla;
	String nome;
	String citta; 
	String nazione;
	
	public Aeroporto(String sigla, String nome, String citta, String nazione) {
		this.sigla = sigla;
		this.nome = nome;
		this.citta = citta;
		this.nazione = nazione;
	}

	public String getSigla() {
		return sigla;
	}

	public String getNome() {
		return nome;
	}

	public String getCitta() {
		return citta;
	}

	public String getNazione() {
		return nazione;
	}

	@Override
	public int compareTo(Aeroporto o) {
		return this.getNome().compareTo(o.getNome());
	}
	
	
	
}

