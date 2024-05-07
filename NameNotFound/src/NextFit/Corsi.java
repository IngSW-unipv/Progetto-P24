package NextFit;

import java.util.ArrayList;
import java.util.List;

import DB.CorsiDAO;

public class Corsi {
	private Corso[] corsi;
	private List<IscrittoalCorso> iscritti;
	private int max, c;

	public Corsi(int max) {
		this.max = max;
		c = 0;
		corsi = new Corso[max];
		this.iscritti = new ArrayList<>();
	}

	public Corso getCorso(int i) {
		Corso corso;
		corso = corsi[i];
		return corso;
	}

	public void aggCorsi(Corso corso) {
		boolean corsoEsistente = false;
		for (int i = 0; i < c; i++) {
			if (corso.getNome().equals(corsi[i].getNome()) && corso.getCorsista().equals(corsi[i].getCorsista())) {
				corsoEsistente = true;
				System.out.println("Il corso già esiste.");
				break;
			}
		}

		if (!corsoEsistente) {
			corsi[c] = corso;
			c++;

			CorsiDAO dao2 = new CorsiDAO();
			dao2.insertCorso(corso);
		}
	}
	
	public void eliminaCorso(String nomeCorso, Dipendente dip) {
	    // Cerca il corso da eliminare utilizzando il metodo ricercaCorso
	    Corso corsoDaEliminare = ricercaCorso(nomeCorso, dip);
	    if (corsoDaEliminare != null) {
	        // Trovato il corso da eliminare
	        for (int i = 0; i < c; i++) {
	            if (corsoDaEliminare.equals(corsi[i])) {
	                // Sposta gli elementi successivi nel vettore per riempire il vuoto
	                for (int j = i; j < c - 1; j++) {
	                    corsi[j] = corsi[j + 1];
	                }
	                // Rimuove l'ultimo elemento del vettore (che ora è duplicato)
	                corsi[c - 1] = null;
	                c--; // Decrementa il contatore
	                System.out.println("Corso eliminato con successo.");
	                return; // Esci dal metodo dopo aver eliminato il corso
	            }
	        }
	    } else {
	        // Se il corso non è stato trovato nel vettore
	        System.out.println("Il corso da eliminare non è presente.");
	    }
	}


	public int getC() {
		return c;
	}

	public void visuCorsi() {
		System.out.println("Lista corsi: ");
		for (int i = 0; i < c; i++) {
			System.out.println(corsi[i].getNome() + " " + corsi[i].getCorsista().getNome() + " "
					+ corsi[i].getCorsista().getCognome());
		}
	}

	public Corso ricercaCorso(String nomeCorso, Dipendente cr) {
		for (int i = 0; i < c; i++) {
			Corso corso = corsi[i];
			if (corso.getNome().equalsIgnoreCase(nomeCorso) && corso.getCorsista().equals(cr)) {
				return corso;
			}
		}
		return null; // Se il corso non è trovato
	}

	public void iscalCorso(ClienteAbbonato c, Corso co) {
		IscrittoalCorso ic = new IscrittoalCorso(co, c);

		if (!iscrittoPresente(c, co)) {
			iscritti.add(ic);
			// System.out.println("Cliente iscritto al corso con successo.");
		} else {
			System.out.println("Il cliente è già iscritto a questo corso.");
		}
	}

	public void elidalCorso(IscrittoalCorso ic) {

		iscritti.remove(ic);

	}

	public List<IscrittoalCorso> getIscritti() {
		return iscritti;
	}

	public void visuClisCorsi() {
		for (IscrittoalCorso i : iscritti) {
			System.out.println(i.getCliente().getCliente().getNome() + " " + i.getCorso().getNome());
		}
	}

	public boolean iscrittoPresente(ClienteAbbonato c, Corso co) {
		IscrittoalCorso iscritto = new IscrittoalCorso(co, c);

		for (IscrittoalCorso i : iscritti) {
			if (i.getCliente().equals(iscritto.getCliente()) && i.getCorso().equals(iscritto.getCorso())) {
				return true;
			}

		}
		return false;
	}

	public IscrittoalCorso trovaIscritto(ClienteAbbonato c, Corso co) {
		for (IscrittoalCorso iscritto : iscritti) {
			if (iscritto.getCliente().equals(c) && iscritto.getCorso().equals(co)) {
				return iscritto;
			}
		}
		return null; // Se l'iscritto non è trovato
	}
}
