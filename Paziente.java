// Importa BlockingQueue, usata come sala d'attesa condivisa
import java.util.concurrent.BlockingQueue;

// Importa AtomicInteger, utile per creare numeri progressivi sicuri tra più thread
import java.util.concurrent.atomic.AtomicInteger;

// Classe Paziente: rappresenta il produttore
public class Paziente implements Runnable {

    // Sala d'attesa condivisa tra reparti e dottori
    private BlockingQueue<String> salaAttesa;

    // Nome del reparto che genera pazienti
    private String reparto;

    // Contatore condiviso tra tutti i reparti
    private static AtomicInteger contatore = new AtomicInteger(0);

    // Costruttore della classe Paziente
    public Paziente(BlockingQueue<String> salaAttesa, String reparto) {

        // Salva la sala d'attesa ricevuta dal main
        this.salaAttesa = salaAttesa;

        // Salva il nome del reparto ricevuto dal main
        this.reparto = reparto;
    }

    // Metodo eseguito quando parte il thread
    @Override
    public void run() {

        try {
            // Array con i possibili codici colore
            String[] codici = {"ROSSO", "GIALLO", "VERDE"};

            // Ogni reparto genera 5 pazienti
            for (int i = 1; i <= 5; i++) {

                // Aumenta il contatore globale e ottiene il numero del paziente
                int numero = contatore.incrementAndGet();

                // Sceglie un codice colore alternando tra ROSSO, GIALLO e VERDE
                String codice = codici[numero % codici.length];

                // Crea la descrizione completa del paziente
                String paziente = "Paziente " + numero + " - Codice " + codice;

                // Stampa da quale reparto arriva il paziente
                System.out.println(reparto + " segnala: " + paziente);

                // Inserisce il paziente nella sala d'attesa
                // Se la sala è piena, il thread aspetta
                salaAttesa.put(paziente);

                // Conferma che il paziente è entrato in sala
                System.out.println(paziente + " entra in sala d'attesa");

                // Simula il tempo tra un paziente e il successivo
                Thread.sleep(600);
            }

            // Messaggio quando il reparto ha finito
            System.out.println(reparto + " non invia più pazienti.");

        } catch (InterruptedException e) {

            // Se il thread viene interrotto, mantiene lo stato di interruzione
            Thread.currentThread().interrupt();
        }
    }
}