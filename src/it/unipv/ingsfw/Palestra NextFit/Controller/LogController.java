package Controller;

import Exception.NoAccountException;
import Model.Corsi;
import Model.Palestra;
import Model.Proprietario;
import Model.Richieste;

import javax.swing.JOptionPane;

import View.LatoClienteView;
import View.LatoCorsistaView;
import View.LatoPTView;
import View.LoginView;
import View.ProprietarioView;

public class LogController {
	private Palestra palestra;
	private Proprietario proprietario;
	private LoginView view;
	private Corsi co;
	private Richieste r;

	public LogController(Palestra palestra, Proprietario proprietario, LoginView view, Corsi co, Richieste r) {
		this.palestra = palestra;
		this.proprietario = proprietario;
		this.view = view;
		this.co = co;
		this.r = r;
	}

	public void Login() throws NoAccountException {

		String mail = view.getMailField().getText();
		String password = new String(view.getPasswordField().getPassword());

		if (mail.toLowerCase().equals(proprietario.getMail()) && password.equals(proprietario.getPassword())) {
			new ProprietarioView(proprietario, palestra, co, r);

		} else if (palestra.esisteCli(mail.toLowerCase(), password)) {
			new LatoClienteView(co, palestra.accessoCli(mail.toLowerCase(), password), palestra, r, proprietario);

		} else if (palestra.esisteDip(mail.toLowerCase(), password)) {
			String tipo = palestra.accessoDip(mail.toLowerCase(), password).getTipo();
			if (tipo.equals("personaltrainer")) {
				new LatoPTView(palestra.accessoDip(mail.toLowerCase(), password), palestra, r);

			} else if (tipo.equals("corsista")) {
				new LatoCorsistaView(palestra.accessoDip(mail.toLowerCase(), password), palestra, co);

			}
		} else {
			JOptionPane.showMessageDialog(null, "Account non esistente", "Errore", JOptionPane.ERROR_MESSAGE);
			throw new NoAccountException("Account non trovato. Verifica le credenziali.");
		}
	}

}
