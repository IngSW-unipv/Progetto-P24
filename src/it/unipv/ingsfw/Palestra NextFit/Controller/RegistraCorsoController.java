package Controller;

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

	public void registraCorso(String nomeCorso, String nome, String cognome, String mail, int maxIscritti) {
		Dipendente d = palestra.ricercaCorsista(nome, cognome, mail);

		if (d != null && d.getTipo().equals("corsista")) {
			Corso corso = new Corso(nomeCorso, d, maxIscritti, 0);
			corsi.aggCorsi(corso);
			view.mostraSuccesso();
		} else {
			view.mostraErrore();
		}
	}
}
