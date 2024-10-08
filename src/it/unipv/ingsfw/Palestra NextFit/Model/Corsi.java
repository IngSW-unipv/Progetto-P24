package Model;

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

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}

	public Corso getCorso(int i) {
		Corso corso;
		corso = corsi[i];
		return corso;
	}

	public Corso getCorsodalCorsista(Dipendente d) {
		Corso corso = null;
		for (int i = 0; i < c; i++) {
			if (d.equals(corsi[i].getCorsista())) {
				corso = corsi[i];
			}
		}
		return corso;
	}

	public boolean isclPresente(ClienteAbbonato c) {
		for (IscrittoalCorso iscritto : iscritti) {
			if (iscritto.getCliente().equals(c)) {
				return true;
			}
		}
		return false;
	}

	public void eliminaIscrizioniCliente(ClienteAbbonato cliente) {
		iscritti.removeIf(iscritto -> iscritto.getCliente().equals(cliente));
	}

	public boolean aggCorsi(Corso corso) {
		boolean corsoEsistente = false;
		boolean corsoinserito = false;
		
		for (int i = 0; i < c; i++) {
			if (corso.getNome().equals(corsi[i].getNome()) && corso.getCorsista().equals(corsi[i].getCorsista())) {
				corsoEsistente = true;
				corsoinserito = false;
				System.out.println("Il corso già esiste.");
				break;
			}
		}

		if (!corsoEsistente) {
			corsi[c] = corso;
			c++;

			CorsiDAO dao2 = new CorsiDAO();
			dao2.insertCorso(corso);
			
			corsoinserito=true;
		}
		return corsoinserito;
	}

	public void eliminaCorso(String nomeCorso, Dipendente dip) {
		// Cerca il corso da eliminare utilizzando il metodo ricercaCorso
		Corso corsoDaEliminare = ricercaCorso(nomeCorso, dip);
		if (corsoDaEliminare != null) {
			// Trovato il corso da eliminare
			for (int i = 0; i < c; i++) {
				if (corsoDaEliminare.equals(corsi[i])) {
					// Sposta l'ultimo elemento nella posizione del corso da eliminare
					corsi[i] = corsi[c - 1];
					// Rimuove l'ultimo elemento del vettore
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

	public IscrittoalCorso aggIsAlCorso(ClienteAbbonato c, Corso co) {
		IscrittoalCorso ic = new IscrittoalCorso(co, c);

		if (!iscrittoPresente(c, co)) {
			iscritti.add(ic);
			// System.out.println("Cliente iscritto al corso con successo.");
		} else {
			System.out.println("Il cliente è già iscritto a questo corso.");
		}

		return ic;
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

	public boolean iscorsoPresente(Corso corso) {
		for (IscrittoalCorso iscritto : iscritti) {
			if (iscritto.getCorso().equals(corso)) {
				return true;
			}
		}
		return false;
	}

	public List<IscrittoalCorso> getIscrittiCorso(Corso corso) {
		List<IscrittoalCorso> iscrittiCorso = new ArrayList<>();
		for (IscrittoalCorso iscritto : iscritti) {
			if (iscritto.getCorso().equals(corso)) {
				iscrittiCorso.add(iscritto);
			}
		}
		return iscrittiCorso;
	}
}
