package Controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import Model.Dipendente;
import Model.Palestra;
import View.RegistraDipView;

public class RegistraDipController {
	private Palestra palestra;
	private RegistraDipView view;

	public RegistraDipController(Palestra palestra, RegistraDipView view) {
		this.palestra = palestra;
		this.view = view;
		this.view.setController(this);
	}

	public void registraDipendente(String nome, String cognome, String mail, String password, int eta, double stipendio,
			String tipo) {

		if (nome.length() <= 0) {
			JOptionPane.showMessageDialog(null, "Nome non inserito", "Errore", JOptionPane.ERROR_MESSAGE);
			throw new IllegalAccessError("Campo Nome vuoto");
		}

		if (cognome.length() <= 0) {
			JOptionPane.showMessageDialog(null, "Cognome non inserito", "Errore", JOptionPane.ERROR_MESSAGE);
			throw new IllegalAccessError("Campo Nome vuoto");
		}

		if (password.length() <= 0) {
			JOptionPane.showMessageDialog(null, "Password non inserita", "Errore", JOptionPane.ERROR_MESSAGE);
			throw new IllegalAccessError("Campo Nome vuoto");
		}

		Dipendente d = palestra.creaDipendente(nome, cognome, mail, password, eta, stipendio, tipo);

		if (isValidEmail(mail)) {
			if (palestra.registraDipendente(d)) {
				view.mostraSuccesso();
			} else {
				view.mostraErrore();
			}
		} else {
			JOptionPane.showMessageDialog(null, "Mail non valida", "Errore", JOptionPane.ERROR_MESSAGE);
		}
	}

	public boolean isValidEmail(String email) {
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
		Pattern pattern = Pattern.compile(emailRegex);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();

	}
}
