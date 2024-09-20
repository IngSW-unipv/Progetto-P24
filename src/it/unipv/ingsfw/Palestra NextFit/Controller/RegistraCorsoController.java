package Controller;

import javax.swing.JOptionPane;

import NextFit.Corsi;
import NextFit.Corso;
import NextFit.Dipendente;
import NextFit.Palestra;
import View.RegistraCorsoView;

public class RegistraCorsoController {
	private Corsi corsi;
	private Palestra palestra;
	private RegistraCorsoView view;

	public RegistraCorsoController(Corsi corsi, Palestra palestra, RegistraCorsoView view) {
		this.corsi = corsi;
		this.palestra = palestra;
		this.view = view;
		this.view.setController(this);
	}

	public void registraCorso(String nomeCorso, String nome, String cognome, String mail, Integer maxIscritti) {
		Dipendente d = palestra.ricercaCorsista(nome, cognome, mail);

		if (nomeCorso.length() <= 0) {
			JOptionPane.showMessageDialog(null, "Nome corso non inserito", "Errore", JOptionPane.ERROR_MESSAGE);
			throw new IllegalAccessError("Campo Nome vuoto");
		}
		
		if (maxIscritti <= 0 || maxIscritti == null) {
			JOptionPane.showMessageDialog(null, "Numero iscritti non inserito", "Errore", JOptionPane.ERROR_MESSAGE);
			throw new IllegalAccessError("Campo Nome vuoto");
		}

		if (d != null && d.getTipo().equals("corsista")) {
			Corso corso = new Corso(nomeCorso, d, maxIscritti, 0);
			if (corsi.aggCorsi(corso) == false)
				view.mostraSuccesso();
			else
				view.mostraErrore();
		} else {
			view.mostraErrore();
		}
	}
}
