import centrosportivo.CentroSportivo;

public class Principale {

    public static void main(String[] args) {

        String nomeCentro = "FitLife Club";
        String indirizzoCentro = "Via Roma 12";
        String capCentro = "10121";
        String cittaCentro = "Torino";

        CentroSportivo centro = new CentroSportivo(nomeCentro, indirizzoCentro, capCentro, cittaCentro);

        centro.definisciDirettore("Giulia", "Bianchi");

        System.out.println("Centro sportivo " + centro.getNome() + " con sede in " + centro.getIndirizzo() + ", cap " + centro.getCap() + ", città " + centro.getCitta());
        System.out.println("Direttore: " + centro.direttore());

        // --- Aggiunta iscritti ---
        System.out.println("\nAggiunti iscritti");

        int iA = centro.aggiungiIscritto("Mario", "Rossi", "20241018");
        int iB = centro.aggiungiIscritto("Carla", "Neri", "20241020");

        System.out.println("\nIscritti aggiunti: ");
        System.out.println(iA);
        System.out.println(iB);

        System.out.println();
        System.out.println("Primo iscritto aggiunto:\n" + centro.iscritto(iA));

        // --- Aggiunta corsi ---
        System.out.println("\nAggiunti corsi");

        int cX = centro.aggiungiCorso("Pilates Base", "Anna Verdi", "Fitness", 60);
        int cY = centro.aggiungiCorso("Acquagym", "Luca Fontana", "Nuoto", 45);
        int cZ = centro.aggiungiCorso("Functional Training", "Marco Riva", "Fitness", 90);

        System.out.println("\nPrimo corso aggiunto:");
        System.out.println(centro.corso(cX));

        // --- Registrazione iscritti ai corsi ---
        centro.registrazione(iA, cX);
        centro.registrazione(iB, cY);
        centro.registrazione(iB, cZ);

        // --- Elenco iscritti per corso ---
        System.out.println();
        System.out.println("Elenco iscritti per il corso " + cX + ":");
        System.out.println(centro.elencoIscrittiCorso(cX));

        // --- Elenco corsi per un iscritto ---
        System.out.println();
        System.out.println("Elenco corsi assegnati a Carla Neri:");
        System.out.println(centro.elencoCorsiIscritto(iB));

        // --- Filtraggio per categoria sportiva ---
        System.out.println();
        System.out.println("Elenco corsi della categoria Fitness:");
        System.out.println(centro.elencoCorsiCategoria("Fitness"));

        // --- Calcolo durata totale ---
        System.out.println();
        System.out.println("Durata complessiva dei corsi cui Carla Neri è iscritta:");
        System.out.println(centro.durataTotaleCorsiIscritto(iB));
    }
}
