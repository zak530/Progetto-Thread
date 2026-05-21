// Importa ArrayBlockingQueue, cioè una coda bloccante con capacità limitata
import java.util.concurrent.ArrayBlockingQueue;

// Importa BlockingQueue, cioè il tipo generale della coda thread-safe
import java.util.concurrent.BlockingQueue;

// Classe principale del programma
public class ProntoSoccorso {

    // Metodo main: punto di partenza del programma
    public static void main(String[] args) {

        // Crea una sala d'attesa con massimo 5 pazienti
        BlockingQueue<String> salaAttesa = new ArrayBlockingQueue<>(5);

        // Crea il primo thread produttore, cioè un reparto che genera pazienti
        Thread reparto1 = new Thread(new Paziente(salaAttesa, "Reparto Cardiologia"));

        // Crea il secondo thread produttore
        Thread reparto2 = new Thread(new Paziente(salaAttesa, "Reparto Ortopedia"));

        // Crea il primo thread consumatore, cioè un dottore
        Thread dottore1 = new Thread(new Dottore(salaAttesa, 1));

        // Crea il secondo thread consumatore
        Thread dottore2 = new Thread(new Dottore(salaAttesa, 2));

        // Avvia il thread del primo reparto
        reparto1.start();

        // Avvia il thread del secondo reparto
        reparto2.start();

        // Avvia il thread del primo dottore
        dottore1.start();

        // Avvia il thread del secondo dottore
        dottore2.start();

        try {
            // Il main aspetta che il primo reparto finisca di generare pazienti
            reparto1.join();

            // Il main aspetta che il secondo reparto finisca di generare pazienti
            reparto2.join();

        } catch (InterruptedException e) {

            // Se il main viene interrotto, stampa l'errore
            e.printStackTrace();
        }

        // Interrompe il primo dottore, perché non arriveranno più pazienti
        dottore1.interrupt();

        // Interrompe il secondo dottore
        dottore2.interrupt();

        // Stampa messaggio finale
        System.out.println("\n=== PRONTO SOCCORSO CHIUSO ===");
    }
}