package Controller;
import Exception.NoAccountException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import NextFit.Corsi;
import NextFit.Palestra;
import NextFit.Proprietario;
import NextFit.Richieste;
import View.LatoCorsistaGui;
import View.LatoPTGui;
import View.Login;
import View.ProprietarioView;
import GUI.LatoClienteGui;
public class LogController {
	private Palestra palestra;
    private Proprietario proprietario;
    private Login view;
	private Corsi co;
	private Richieste r;
	public LogController(Palestra palestra, Proprietario proprietario, Login view,Corsi co, Richieste r)
	{
		this.palestra = palestra;
        this.proprietario = proprietario;
        this.view = view;
        this.co=co;
        this.r=r;
	}
	public void Login() throws NoAccountException
	{
		
				String mail = view.getMailField().getText();
		        String password = new String(view.getPasswordField().getPassword());
		       

		        if (mail.toLowerCase().equals(proprietario.getMail()) && password.equals(proprietario.getPassword())) {
		            new ProprietarioView(proprietario, palestra, co, r);
		         
		        } else if (palestra.esisteCli(mail.toLowerCase(), password)) {
		            new LatoClienteGui(co, palestra.accessoCli(mail.toLowerCase(), password), palestra, r);
		            
		        } else if (palestra.esisteDip(mail.toLowerCase(), password)) {
		            String tipo = palestra.accessoDip(mail.toLowerCase(), password).getTipo();
		            if (tipo.equals("personaltrainer")) {
		                new LatoPTGui(palestra.accessoDip(mail.toLowerCase(), password), palestra, r);
		                
		            } else if (tipo.equals("corsista")) {
		                new LatoCorsistaGui(palestra.accessoDip(mail.toLowerCase(), password), palestra, co);
		               
		            }
		        }else
		        {
		        	JOptionPane.showMessageDialog(null,
		                    "Account non esistente",
		                    "Errore", JOptionPane.ERROR_MESSAGE);
		        	throw new NoAccountException("Account non trovato. Verifica le credenziali.");
		        }
		        }
			
	
}
