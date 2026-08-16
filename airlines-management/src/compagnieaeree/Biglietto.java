package compagnieaeree;

import java.util.LinkedList;

public class Biglietto {
	int numeroBiglietto;
	String nome;
	String cognome;
	char sesso;
	String numeroFrequentFlyer;
	int durataVolo;
	LinkedList <Segmento> segmentiVolo = new LinkedList<> ();

	public Biglietto(int numeroBiglietto, String nome, String cognome, char sesso, String numeroFrequentFlyer) {
		this.numeroBiglietto = numeroBiglietto;
		this.nome = nome;
		this.cognome = cognome;
		this.sesso = sesso;
		this.numeroFrequentFlyer = numeroFrequentFlyer;
	}

	public int getNumeroBiglietto() {
		return numeroBiglietto;
	}

	public String getNome(){
		return nome;
	}
	
	public String getCognome(){
		return cognome;
	}
	
	public char getSesso(){
		return sesso;
	}
	
	public String getNumeroFrequentFlyer() {
		return numeroFrequentFlyer;
	}

}



