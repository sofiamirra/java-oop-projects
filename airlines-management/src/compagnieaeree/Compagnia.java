package compagnieaeree;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class Compagnia {
	String nome;
	String sede;
	String web;
	String codiceIata;
	
	int numeroBiglietto = 100000;
	
	TreeMap <String, Aeroporto> mappaAeroporti = new TreeMap <> ();
	LinkedList <Aeroporto> listaAeroporti = new LinkedList<> ();
	
	TreeMap <String, Aeromobile> mappaAeromobili = new TreeMap <> ();

	TreeMap <String, Volo> mappaDataSigla = new TreeMap <> ();
	TreeMap <String, Volo> mappaDataOraSigla = new TreeMap <> (); // di supporto per l'ordinamento
	
	TreeMap <Integer, Biglietto> mappaBiglietti = new TreeMap <> ();

	public Compagnia(String nome, String sede, String web, String codiceIata) {
		this.nome = nome;
		this.sede = sede;
		this.web = web;
		this.codiceIata = codiceIata;
	}

	public String getNome() {
		return nome;
	}

	public String getSede() {
		return sede;
	}

	public String getWeb() {
		return web;
	}

	public String getCodiceIata() {
		return codiceIata;
	}

	public Aeroporto nuovoAeroporto(String sigla, String nome, String citta, String nazione){
		Aeroporto aeroporto = mappaAeroporti.get(sigla);
		if (aeroporto != null) return aeroporto;
		
		aeroporto = new Aeroporto(sigla, nome, citta, nazione);
		mappaAeroporti.put(sigla, aeroporto);
		listaAeroporti.add(aeroporto);
		return aeroporto;
	}

	public Collection<Aeroporto> elencoAeroporti(){
		LinkedList <Aeroporto> copia = new LinkedList<> (listaAeroporti);
		Collections.sort(copia);
		return copia;
	}
	
	public Collection<Aeroporto> elencoAeroporti(String nazione){
		LinkedList <Aeroporto> risultato = new LinkedList<> ();
		for (Aeroporto a : listaAeroporti) {
			if (a.getNazione().equals(nazione)) {
				risultato.add(a);
			}
		}
		Collections.sort(risultato);
		return risultato;
	}
	
	public void nuovoAeromobile(String siglaModello, int quantita, int autonomia, int numPostiBusiness, int numPostiEconomy){
		if (mappaAeromobili.containsKey(siglaModello)) { // se l'oggetto è già definito
			Aeromobile a = mappaAeromobili.get(siglaModello);
	        a.quantita += quantita;  // aggiorno la quantità (attributo) incrementandola
		} else { // altrimenti creo l'oggetto
			Aeromobile aeromobile = new Aeromobile(siglaModello, quantita, autonomia, numPostiBusiness, numPostiEconomy);
			mappaAeromobili.put(siglaModello, aeromobile); 
			}
	}
	
	public Aeromobile cercaAeromobile(String siglaModello){
		return mappaAeromobili.get(siglaModello);
	}
	
	public Collection<Aeromobile> elencoAeromobili(){
		LinkedList <Aeromobile> risultato = new LinkedList<> ();
		for (Aeromobile a : mappaAeromobili.values()) {
			int postiComplessivi = a.numeroPostiPerClasse("Business") + a.numeroPostiPerClasse("Economy");
			a.setPostiComplessivi(postiComplessivi); // imposto il totale
			risultato.add(a);
		}
		Collections.sort(risultato); // per numero di posti complessivi decrescente
		return risultato;
	}
	
	// R2
	public Volo nuovoVolo(String sigla, String siglaAeroportoPartenza, String siglaAeroportoArrivo, String dataPartenza, String oraPartenza, String dataArrivo, String oraArrivo, String siglaModelloAeromobile) throws EccezioneOrarioInconsistente{
		Aeroporto aeroportoPartenza = mappaAeroporti.get(siglaAeroportoPartenza);
		Aeroporto aeroportoArrivo = mappaAeroporti.get(siglaAeroportoArrivo);
		Aeromobile aeromobile = mappaAeromobili.get(siglaModelloAeromobile);
		
		if (aeroportoPartenza == null || aeroportoArrivo == null) return null;
		String chiave = dataPartenza + " " + sigla; // così mantengo l'ordine anche per ora
		String chiave2 = dataPartenza + " " + oraPartenza + " " + sigla; // così mantengo l'ordine anche per ora
		
		Volo volo = mappaDataSigla.get(chiave);
		
		// Gestione Eccezioni
		// Controllo se data/ora arrivo precede data/ora partenza
		if (Integer.parseInt(dataArrivo) < Integer.parseInt(dataPartenza))
		    throw new EccezioneOrarioInconsistente();

		if (dataArrivo.equals(dataPartenza) && // se le date coincidono
		    Integer.parseInt(oraArrivo.replace(":", "")) <= Integer.parseInt(oraPartenza.replace(":", "")))
		    throw new EccezioneOrarioInconsistente();

		if (volo != null) { // se c'è già un volo definito, aggiorno le informazioni
			// oraPartenza può cambiare: inc aso di aggiornamento va rimosso dalla mappa
			String vecchiaChiaveOrdinamento = volo.getDataPartenza() + " " + volo.getOraPartenza() + " " + volo.getSigla();
	        mappaDataOraSigla.remove(vecchiaChiaveOrdinamento); // elimino la vecchia chiave nella mappa
	        
	        // Solo gli attributi di versi da sigla e dataPartenza possono essere aggiornati 
			volo.oraPartenza = oraPartenza;
			volo.dataArrivo = dataArrivo;
			volo.oraArrivo = oraArrivo;
			volo.aeroportoPartenza = aeroportoPartenza;
			volo.aeroportoArrivo = aeroportoArrivo;
			volo.aeromobile = aeromobile;
		} else {
			// Altrimenti, creo il nuovo oggetto
			volo = new Volo(sigla, aeroportoPartenza, aeroportoArrivo, dataPartenza, oraPartenza, dataArrivo, oraArrivo, aeromobile);
			mappaDataSigla.put(chiave, volo);
		}
		mappaDataOraSigla.put(chiave2, volo);
		return volo;
	}
	
	public String orario(){
		StringBuilder sb = new StringBuilder();
		for (Volo v : mappaDataOraSigla.values( )) { // in ordine di data, ora e sigla
			sb.append(v.getSigla()).append(" ").append(v.getAeroportoPartenza().getSigla()).append(" ").append(v.getDataPartenza()).append(" ").append(v.getOraPartenza())
			.append(" ").append(v.getAeroportoArrivo().getSigla()).append(" ").append(v.getDataArrivo()).append(" ").append(v.getOraArrivo()).append("\n");
		}
		return sb.toString().trim();
	}
	
	// R3
	public void registraDecollo(String siglaVolo, String dataPartenza, String dataPartenzaEffettiva, String oraPartenzaEffettiva){
		String chiave = dataPartenza + " " + siglaVolo; 
		Volo volo = mappaDataSigla.get(chiave);
		if (volo == null) return;
		
		volo.setDataPartenzaEffettiva(dataPartenzaEffettiva);
		volo.setOraPartenzaEffettiva(oraPartenzaEffettiva);
		volo.setDecollato(true); // il volo è decollato
	}

	public void registraAtterraggio(String siglaVolo, String dataPartenza, String dataArrivoEffettiva, String oraArrivoEffettiva){
		String chiave = dataPartenza + " " + siglaVolo; 
		Volo volo = mappaDataSigla.get(chiave);
		if (volo == null) return;
		volo.setAtterrato(true); // il volo è atterrato
		volo.setDataArrivoEffettiva(dataArrivoEffettiva);
		volo.setOraArrivoEffettiva(oraArrivoEffettiva);
	}
	
	public double calcolaRitardo(String siglaVolo, String data){
		String chiave = data + " " + siglaVolo; 
		Volo volo = mappaDataSigla.get(chiave);
		if (volo == null) return 0.0;
		
		if (!volo.isAtterrato()) return 0.0; // se il volo non è atterrato
		
		// Conversione delle date di arrivo da stringa a intero
		int dataArrivoPrevista = Integer.parseInt(volo.dataArrivo);
		int dataArrivoEffettiva = Integer.parseInt(volo.dataArrivoEffettiva);
		
		// Orari di arrivo previsti ed effettivi (formato HH:MM)
		String orarioArrivoPrevisto = volo.getOraArrivo();
		String orarioArrivoEffettivo = volo.getOraArrivoEffettiva();
		
		// Estrazione dei minuti (MM) dall'orario
		int minutiArrivoPrevisti = Integer.parseInt(volo.getOraArrivo().substring(3)); // MM
		int minutiArrivoEffettivi = Integer.parseInt(volo.getOraArrivoEffettiva().substring(3)); // MM
		
		// Estrazione delle ore (HH) dall'orario
		int oraArrivoPrevisti = Integer.parseInt(volo.getOraArrivo().substring(0, 2)); // HH
		int oraArrivoEffettivi = Integer.parseInt(volo.getOraArrivoEffettiva().substring(0, 2)); // HH
	
		double minutiRitardo = 0.0;
		
		// Se la data di arrivo è diversa, calcolo il ritardo dovuto ai giorni di differenza
		if (dataArrivoPrevista != dataArrivoEffettiva) {
			minutiRitardo = (dataArrivoEffettiva - dataArrivoPrevista)*24*60;
		}
		
		// Se l'orario di arrivo è diverso, aggiungo il ritardo dovuto a ore e minuti
		if (!orarioArrivoPrevisto.equals(orarioArrivoEffettivo)) {
			minutiRitardo += (minutiArrivoEffettivi - minutiArrivoPrevisti) + ((oraArrivoEffettivi - oraArrivoPrevisti)*60);
		}
		
		volo.setMinutiRitardo(minutiRitardo);  // salvo il ritardo calcolato all'interno dell'oggetto Volo
		return minutiRitardo;
	}

	public double calcolaRitardoMedio(String siglaVolo){
		double totaleRitardo = 0.0;
		double ritardoMedio = 0.0;
		int conteggioVoliAtterrati = 0;
		for (Volo v : mappaDataSigla.values()) {
			if (v.getSigla().equals(siglaVolo) && v.isAtterrato()) {
				calcolaRitardo(v.getSigla(), v.getDataPartenza());
				totaleRitardo += v.getMinutiRitardo();
				conteggioVoliAtterrati++;
			}
		}
		
		if (conteggioVoliAtterrati == 0) return 0.0;
		ritardoMedio = totaleRitardo / conteggioVoliAtterrati;
		return ritardoMedio;
	}	
	
	public String partenze(String siglaAeroportoPartenza) {	
		StringBuilder sb = new StringBuilder();
		for (Volo v : mappaDataOraSigla.values( )) { // in ordine di data, ora e sigla
			if (v.getAeroportoPartenza().getSigla().equals(siglaAeroportoPartenza)) {
			sb.append(v.getSigla()).append(" ").append(v.getAeroportoPartenza().getSigla()).append(" ").append(v.getDataPartenza()).append(" ").append(v.getOraPartenza())
			.append(" ").append(v.getAeroportoArrivo().getSigla()).append(" ").append(v.getDataArrivo()).append(" ").append(v.getOraArrivo()).append(" ");
			
			if (v.isAtterrato()) sb.append("atterrato").append(" ").append(v.getDataArrivoEffettiva()).append(" ").append(v.getOraArrivoEffettiva()).append("\n");
			else if (v.isDecollato()) sb.append("partito").append("\n");
			else sb.append("non decollato").append("\n");
			}
		}
		return sb.toString().trim();	
	}
	
	// R4
	public int nuovoBiglietto(String nome, String cognome, char sesso, String numeroFrequentFlyer){
		int idCorrente = numeroBiglietto;
		Biglietto biglietto = new Biglietto (idCorrente, nome, cognome, sesso, numeroFrequentFlyer);
		mappaBiglietti.put(idCorrente, biglietto);
		numeroBiglietto++;
		return idCorrente;
	}

	public void aggiungiSegmento(int numeroBiglietto, String siglaVolo, String data, String classe, double tariffa, double tasse) throws EccezionePostiNonDisponibili{
		Biglietto biglietto = mappaBiglietti.get(numeroBiglietto);
		String chiave = data + " " + siglaVolo; 
		Volo volo = mappaDataSigla.get(chiave);
		
		// Se non ci sono posti disponibili in quella classe, scateno l'eccezione
		if (volo.getAeromobile().numeroPostiPerClasse(classe) == 0) { 
			throw new EccezionePostiNonDisponibili();
		}
	
		// Se posso prenotare
		// Decremento i posti disponibili 
		if (classe.equals("Business")) {
		    volo.getAeromobile().numPostiBusiness--;
		} else if (classe.equals("Economy")) {
		    volo.getAeromobile().numPostiEconomy--;
		}

		int minutiPartenza = Integer.parseInt(volo.getOraPartenza().substring(3)); // MM
		int minutiArrivo = Integer.parseInt(volo.getOraArrivo().substring(3)); // MM
		int oraPartenza = Integer.parseInt(volo.getOraPartenza().substring(0, 2)); // HH
		int oraArrivo = Integer.parseInt(volo.getOraArrivo().substring(0, 2)); // HH
		
		double durata = (minutiArrivo - minutiPartenza) + ((oraArrivo - oraPartenza)*60);	
		Segmento segmento = new Segmento(numeroBiglietto, siglaVolo, data, classe, tariffa, tasse, durata);
		biglietto.segmentiVolo.add(segmento);
	}
	
	public Biglietto cercaBiglietto(int numeroBiglietto){
		return mappaBiglietti.get(numeroBiglietto);
	}

	public String itinerarioBiglietto(int numeroBiglietto) {
		Biglietto biglietto = mappaBiglietti.get(numeroBiglietto);
		StringBuilder sb = new StringBuilder();
		for (Segmento s : biglietto.segmentiVolo) {
			sb.append(s.getSiglaVolo()).append(" ").append(s.getData()).append(" ").append(s.getClasse()).append(" ").append(s.getDurata()).append("\n");
		}
		return sb.toString().trim();
	}

    public void leggiFile(String file){
    	try {
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			
			String riga;
			while ((riga = br.readLine()) != null) {
				String array[] = riga.split(",");
				
				String sigla = array[0];
				String siglaAeroportoPartenza = array[1];
				String siglaAeroportoArrivo = array[2];
				String dataPartenza = array[3];
				String oraPartenza = array[4];
				String dataArrivo = array[5];
				String oraArrivo = array[6];
				String siglaModelloAeromobile = array[7];
				this.nuovoVolo(sigla, siglaAeroportoPartenza, siglaAeroportoArrivo, dataPartenza, oraPartenza, dataArrivo, oraArrivo, siglaModelloAeromobile);
			}
			
			br.close();
			fr.close();
		} 
		catch(Exception e) { // Macro-eccezione su tutto
			e.printStackTrace();
		}
	}
}
