package View;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import Controller.RegController;
import Exception.AccountAlreadyExists;
import Exception.InvalidCredentialsException;
import Model.Cliente;
import Model.Corsi;
import Model.Palestra;
import Model.Proprietario;
import Model.Richieste;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegView extends JFrame {
	private JTextField nomeField, cognomeField, mailField, etaField;
	private JButton registraButton;
	private JPasswordField passwordField;
	private JLabel nome, cognome, pw, eta, mail, NEXTFIT, errorLabel, errorlabel2;
	private RegController reg;

	public RegView(Palestra palestra, Proprietario proprietario, Corsi co, Richieste r) {

		setTitle("Registrazione Cliente Palestra");
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		Color CBACK = new Color(28, 28, 28);

		panel.setBackground(CBACK);

		NEXTFIT = new JLabel("<html><font color='orange'>REGI</font><font color='white'>STRAZIONE</font></html>");
		NEXTFIT.setFont(new Font("Rockwell", Font.BOLD, 40));

		panel.add(NEXTFIT);

		panel.add(Box.createRigidArea(new Dimension(0, 20)));

		nomeField = new JTextField();
		nomeField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
		nomeField.setForeground(Color.white);
		cognomeField = new JTextField();
		cognomeField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
		cognomeField.setForeground(Color.white);
		mailField = new JTextField();
		mailField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
		mailField.setForeground(Color.white);
		etaField = new JTextField();
		etaField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
		etaField.setForeground(Color.white);
		passwordField = new JPasswordField();
		passwordField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
		passwordField.setForeground(Color.white);

		Color CBUT = new Color(40, 40, 40);

		Border roundedBorder = BorderFactory.createCompoundBorder(new LineBorder(Color.black, 2, true),
				new EmptyBorder(0, 10, 0, 10) // Margine interno
		);

		nomeField.setBorder(roundedBorder);
		nomeField.setBackground(CBUT);
		cognomeField.setBorder(roundedBorder);
		cognomeField.setBackground(CBUT);
		mailField.setBorder(roundedBorder);
		mailField.setBackground(CBUT);
		etaField.setBorder(roundedBorder);
		etaField.setBackground(CBUT);
		passwordField.setBorder(roundedBorder);
		passwordField.setBackground(CBUT);

		nome = new JLabel("Nome:");
		nome.setForeground(Color.white);
		cognome = new JLabel("Cognome:");
		cognome.setForeground(Color.white);
		eta = new JLabel("Età:");
		eta.setForeground(Color.white);
		mail = new JLabel("Email:");
		mail.setForeground(Color.white);
		pw = new JLabel("Password:");
		pw.setForeground(Color.white);

		panel.add(nome);
		panel.add(Box.createRigidArea(new Dimension(0, 20)));
		panel.add(nomeField);
		panel.add(Box.createRigidArea(new Dimension(0, 20)));
		panel.add(cognome);
		panel.add(Box.createRigidArea(new Dimension(0, 20)));
		panel.add(cognomeField);
		panel.add(Box.createRigidArea(new Dimension(0, 20)));
		panel.add(mail);
		panel.add(Box.createRigidArea(new Dimension(0, 20)));
		panel.add(mailField);
		panel.add(Box.createRigidArea(new Dimension(0, 20)));
		panel.add(eta);
		panel.add(Box.createRigidArea(new Dimension(0, 20)));
		panel.add(etaField);
		panel.add(Box.createRigidArea(new Dimension(0, 20)));
		panel.add(pw);
		panel.add(Box.createRigidArea(new Dimension(0, 20)));
		panel.add(passwordField);
		panel.add(Box.createRigidArea(new Dimension(0, 20)));

		panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		Color or = new Color(250, 140, 0);

		JLabel errorLabel = new JLabel("*Errore: minorenne o Cliente già presente");
		errorLabel.setForeground(Color.RED);

		JLabel errorLabel2 = new JLabel("*Errore: I campi devono essere scritti nel modo corretto");
		errorLabel2.setForeground(Color.RED);

		registraButton = new JButton("Registrati");

		registraButton.setBackground(or);
		registraButton.setFont(new Font("Arial", Font.BOLD, 13));
		registraButton.setMaximumSize(new Dimension(100, 30));
		registraButton.setBorder(BorderFactory.createLineBorder(or, 6, false));

		reg = new RegController(palestra, proprietario, this, co, r);
		registraButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					try {
						reg.Registra();
					} catch (InvalidCredentialsException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} catch (AccountAlreadyExists e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		panel.add(registraButton);

		getContentPane().add(panel, BorderLayout.CENTER);

		setSize(480, 640); // Impostiamo le dimensioni della finestra
		setLocationRelativeTo(null); // Posizioniamo la finestra al centro dello schermo
		setVisible(true);
	}

	public JTextField getNomeField() {
		return nomeField;
	}

	public void setNomeField(JTextField nomeField) {
		this.nomeField = nomeField;
	}

	public JTextField getCognomeField() {
		return cognomeField;
	}

	public void setCognomeField(JTextField cognomeField) {
		this.cognomeField = cognomeField;
	}

	public JTextField getMailField() {
		return mailField;
	}

	public void setMailField(JTextField mailField) {
		this.mailField = mailField;
	}

	public JTextField getEtaField() {
		return etaField;
	}

	public void setEtaField(JTextField etaField) {
		this.etaField = etaField;
	}

	public JPasswordField getPasswordField() {
		return passwordField;
	}

	public void setPasswordField(JPasswordField passwordField) {
		this.passwordField = passwordField;
	}

}