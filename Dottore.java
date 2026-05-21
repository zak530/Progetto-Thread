// Importa BlockingQueue, usata per leggere i pazienti dalla sala d'attesa
import java.util.concurrent.BlockingQueue;

// Classe Dottore: rappresenta il consumatore
public class Dottore implements Runnable {

    // Sala d'attesa condivisa da cui il dottore prende i pazienti
    private BlockingQueue<String> salaAttesa;

    // Numero identificativo del dottore
    private int numeroDottore;

    // Costruttore della classe Dottore
    public Dottore(BlockingQueue<String> salaAttesa, int numeroDottore) {

        // Salva la sala d'attesa ricevuta dal main
        this.salaAttesa = salaAttesa;

        // Salva il numero del dottore
        this.numeroDottore = numeroDottore;
    }

    // Metodo eseguito quando parte il thread
    @Override
    public void run() {

        try {
            // Il dottore continua a lavorare finché non viene interrotto
            while (true) {

                // Prende un paziente dalla sala d'attesa
                // Se la sala è vuota, il dottore aspetta
                String paziente = salaAttesa.take();

                // Stampa che il dottore inizia la visita
                System.out.println("Dottore " + numeroDottore + " visita " + paziente);

                // Genera una durata casuale della visita tra 800 e 2000 ms circa
                int durataVisita = 800 + (int) (Math.random() * 1200);

                // Simula il tempo della visita
                Thread.sleep(durataVisita);

                // Stampa che la visita è finita
                System.out.println(
                        "Dottore " + numeroDottore +
                        " ha terminato la visita di " + paziente +
                        " in " + durataVisita + " ms"
                );
            }

        } catch (InterruptedException e) {

            // Quando il dottore viene interrotto, termina il turno
            System.out.println("Dottore " + numeroDottore + " termina il turno.");

            // Ripristina lo stato di interruzione del thread
            Thread.currentThread().interrupt();
        }
    }
}