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

	public Corso ricercaCorso(String nomeCorso, Corsista cr) {
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
		if (c == null || co == null) {
			return false;
		}

		IscrittoalCorso iscritto = new IscrittoalCorso(co, c);

		for (IscrittoalCorso i : iscritti) {
			if (i != null && i.getCliente() != null && i.getCorso() != null) {
				if (i.getCliente().getCliente() != null && i.getCorso().equals(iscritto.getCorso())) {
					return true;
				}
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
