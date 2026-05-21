# Simulatore di Pronto Soccorso – Problema Produttori-Consumatori

## Descrizione del progetto

Questo progetto consiste in una simulazione concorrente di un pronto soccorso sviluppata in Java, basata sul classico problema dei produttori-consumatori.

L’obiettivo è mostrare come più thread possano collaborare in modo sicuro utilizzando una risorsa condivisa limitata, evitando problemi di sincronizzazione e accessi concorrenti non controllati.

Nel progetto:
- i reparti ospedalieri rappresentano i produttori;
- i medici rappresentano i consumatori;
- la sala d’attesa rappresenta il buffer condiviso.

---

# Struttura del progetto

Il progetto è composto da 3 classi principali:

| Classe | Ruolo |
|---|---|
| `ProntoSoccorso` | Classe principale che avvia il sistema |
| `Paziente` | Thread produttore che genera pazienti |
| `Dottore` | Thread consumatore che visita i pazienti |

---

# Funzionamento

## Produttori

I thread `Paziente` simulano i reparti ospedalieri che generano continuamente nuovi pazienti.

Ogni paziente possiede:
- numero identificativo;
- reparto di provenienza;
- codice colore simulato (`ROSSO`, `GIALLO`, `VERDE`).

I pazienti vengono inseriti nella sala d’attesa condivisa.

---

## Consumatori

I thread `Dottore` rappresentano i medici del pronto soccorso.

Ogni medico:
1. preleva un paziente dalla sala d’attesa;
2. simula la visita;
3. completa il trattamento;
4. passa al paziente successivo.

---

# Sala d’attesa condivisa

La sala d’attesa utilizza:

```java
BlockingQueue<String>
```

implementata tramite:

```java
ArrayBlockingQueue<String>
```

Questa struttura:
- è thread-safe;
- sincronizza automaticamente i thread;
- evita race condition;
- blocca automaticamente produttori e consumatori quando necessario.

---

# Tecnologie utilizzate

- Java
- Thread
- Runnable
- BlockingQueue
- ArrayBlockingQueue
- AtomicInteger

---

# Problema produttori-consumatori

Il progetto implementa il classico problema produttori-consumatori.

Nel sistema:
- i produttori inseriscono pazienti nella coda;
- i consumatori prelevano pazienti dalla coda.

I problemi principali della concorrenza sono:
- buffer pieno;
- buffer vuoto;
- accessi simultanei alla stessa risorsa.

La `BlockingQueue` risolve automaticamente questi problemi.

---

# Prevenzione dei problemi di sincronizzazione

Per evitare errori concorrenti il progetto utilizza:
- `put()` → blocca i produttori se la sala è piena;
- `take()` → blocca i consumatori se la sala è vuota;
- `interrupt()` → termina correttamente i thread;
- `Thread.sleep()` → simula i tempi reali del sistema.

---

# Compilazione

Nel terminale eseguire:

```bash
javac *.java
```

---

# Esecuzione

Avviare il programma con:

```bash
java ProntoSoccorso
```

---

# Output atteso

Esempio di esecuzione:

```text
Reparto Cardiologia segnala: Paziente 1 - Codice GIALLO

Paziente 1 - Codice GIALLO entra in sala d'attesa

Dottore 1 visita Paziente 1 - Codice GIALLO

Dottore 1 ha terminato la visita di Paziente 1 - Codice GIALLO in 1450 ms

PRONTO SOCCORSO CHIUSO
```

---

# Concetti teorici applicati

Il progetto utilizza:
- thread concorrenti;
- problema produttori-consumatori;
- sincronizzazione;
- risorse condivise;
- thread-safe collections;
- gestione della concorrenza in Java.

---

# Obiettivo didattico

Il progetto dimostra come utilizzare i thread in Java per coordinare produttori e consumatori in un sistema concorrente reale, garantendo sicurezza nell’accesso alle risorse condivise e corretta gestione dei thread.