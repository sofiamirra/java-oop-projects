package centrosportivo;

public class CentroSportivo {
    
    public CentroSportivo(String nome, String indirizzo, String cap, String citta) {
    }

    public String getNome() {
        return null;
    }

    public String getIndirizzo() {
        return null;
    }

    public String getCap() {
        return null;
    }

    public String getCitta() {
        return null;
    }

    public void definisciDirettore(String nome, String cognome) {
    }

    public String direttore() {
        return null;
    }

    public int aggiungiIscritto(String nome, String cognome, String dataIscrizione) {
        return -1;
    }

    public String iscritto(int codice) {
        return null;
    }

    public int aggiungiCorso(String titolo, String istruttore, String categoria, int durataMinuti) {
        return -1;
    }

    public String corso(int codiceCorso) {
        return null;
    }

    public void registrazione(int codiceIscritto, int codiceCorso) {
    }

    public String elencoIscrittiCorso(int codiceCorso) {
        return null;
    }

    public String elencoCorsiIscritto(int codiceIscritto) {
        return null;
    }

    public int numeroIscritti(int codiceCorso) {
        return -1;
    }

    public int numeroCorsi(int codiceIscritto) {
        return -1;
    }

    public String elencoCorsiCategoria(String categoria) {
        return null;
    }

    public int durataTotaleCorsiIscritto(int codiceIscritto) {
        return -1;
    }
}
