package Controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import GUI.AbbonamentoGui;
import NextFit.Cliente;
import NextFit.Corsi;
import NextFit.Palestra;
import NextFit.Proprietario;
import NextFit.Richieste;
import View.Login;
import View.RegView;

public class RegController {
	private Palestra palestra;
    private Proprietario proprietario;
    private RegView view;
	private Corsi co;
	private Richieste r;
	public RegController(Palestra palestra, Proprietario proprietario,RegView view, Corsi co, Richieste r)
	{
		this.palestra = palestra;
        this.proprietario = proprietario;
        this.view = view;
        this.co=co;
        this.r=r;
	}
	public void Registra()
	{
		String nome = view.getNomeField().getText();
		String cognome = view.getCognomeField().getText();
		String mail = view.getMailField().getText();
		String password = new String(view.getPasswordField().getPassword());
		int eta = Integer.parseInt(view.getEtaField().getText());
		Cliente cliente = new Cliente(nome, cognome, mail, password, eta);

		

		if (palestra.registraCliente(cliente) == true && isValidEmail(mail) && isValidnome(nome)
				&& isValidcognome(cognome)) {

			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					// new LatoClienteGui(co, cliente, palestra);
					new AbbonamentoGui(cliente, proprietario, palestra, co, r);
				}
			});
		} else {
			if (palestra.registraCliente(cliente) == false) {

				
			} else if (isValidEmail(mail) == false || isValidnome(nome) == false
					|| isValidcognome(cognome) == false) {
				
	}
}
		}
	public boolean isValidEmail(String email) {
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
		Pattern pattern = Pattern.compile(emailRegex);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();

	}

	public boolean isValidnome(String nome) {
		String Regex = "^[a-zA-ZàèìòùÀÈÌÒÙ'\\-\\s]{2,}$";
		Pattern pattern = Pattern.compile(Regex);
		Matcher matcher = pattern.matcher(nome);
		return matcher.matches();
	}

	public boolean isValidcognome(String cognome) {
		String Regex = "^[a-zA-ZàèìòùÀÈÌÒÙ'\\-\\s]{2,}$";
		Pattern pattern = Pattern.compile(Regex);
		Matcher matcher = pattern.matcher(cognome);
		return matcher.matches();
	}
	}
