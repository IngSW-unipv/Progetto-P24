package View;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import NextFit.Palestra;
import Controller.RegistraDipController;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistraDipView extends JFrame {
	private JTextField nomeField, cognomeField, mailField, etaField, stipField;
	private JButton registraButton, backButton;
	private JPasswordField passwordField;
	private JLabel nome, cognome, pw, eta, mail, tipo, stipendio, NEXTFIT, errorLabel;
	private JPanel regPanel;
	private JComboBox<String> tipoField;
	private RegistraDipController controller;
	private JFrame previousFrame;

	public RegistraDipView(Palestra palestra, JFrame previousFrame) {
		this.previousFrame = previousFrame;

		setTitle("Registrazione Dipendente Palestra");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		GridLayout gridLayout = new GridLayout(0, 1);
		gridLayout.setHgap(10);
		JPanel panel = new JPanel(gridLayout);

		Color CBACK = new Color(28, 28, 28);
		panel.setBackground(CBACK);

		NEXTFIT = new JLabel("<html><font color='orange'>REGI</font><font color='white'>STRAZIONE</font></html>");
		NEXTFIT.setFont(new Font("Rockwell", Font.BOLD, 40));
		panel.add(NEXTFIT);

		nomeField = new JTextField();
		nomeField.setForeground(Color.white);
		cognomeField = new JTextField();
		cognomeField.setForeground(Color.white);
		mailField = new JTextField();
		mailField.setForeground(Color.white);
		etaField = new JTextField();
		etaField.setForeground(Color.white);

		String[] options = { "PersonalTrainer", "Corsista", "Dietista" };
		tipoField = new JComboBox<>(options);
		tipoField.setForeground(Color.white);

		passwordField = new JPasswordField();
		passwordField.setForeground(Color.white);
		stipField = new JTextField();
		stipField.setForeground(Color.white);

		Color CBUT = new Color(40, 40, 40);

		Border roundedBorder = BorderFactory.createCompoundBorder(new LineBorder(Color.black, 2, true),
				new EmptyBorder(0, 10, 0, 10));

		nomeField.setBorder(roundedBorder);
		nomeField.setBackground(CBUT);
		nomeField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 20));
		cognomeField.setBorder(roundedBorder);
		cognomeField.setBackground(CBUT);
		cognomeField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 20));
		mailField.setBorder(roundedBorder);
		mailField.setBackground(CBUT);
		mailField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 20));
		etaField.setBorder(roundedBorder);
		etaField.setBackground(CBUT);
		etaField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 20));
		tipoField.setBorder(roundedBorder);
		tipoField.setBackground(CBUT);
		tipoField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 20));
		passwordField.setBorder(roundedBorder);
		passwordField.setBackground(CBUT);
		passwordField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 20));
		stipField.setBorder(roundedBorder);
		stipField.setBackground(CBUT);
		stipField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 20));

		nome = new JLabel("Nome:");
		nome.setForeground(Color.white);
		nome.setMaximumSize(new Dimension(Integer.MAX_VALUE, 20));
		cognome = new JLabel("Cognome:");
		cognome.setForeground(Color.white);
		cognome.setMaximumSize(new Dimension(Integer.MAX_VALUE, 20));
		eta = new JLabel("Età:");
		eta.setForeground(Color.white);
		eta.setMaximumSize(new Dimension(Integer.MAX_VALUE, 20));
		mail = new JLabel("Email:");
		mail.setForeground(Color.white);
		mail.setMaximumSize(new Dimension(Integer.MAX_VALUE, 20));
		tipo = new JLabel("Tipologia:");
		tipo.setForeground(Color.white);
		tipo.setMaximumSize(new Dimension(Integer.MAX_VALUE, 20));
		pw = new JLabel("Password:");
		pw.setForeground(Color.white);
		pw.setMaximumSize(new Dimension(Integer.MAX_VALUE, 20));
		stipendio = new JLabel("Stipendio:");
		stipendio.setForeground(Color.white);
		stipendio.setMaximumSize(new Dimension(Integer.MAX_VALUE, 20));

		panel.add(nome);
		panel.add(nomeField);
		panel.add(cognome);
		panel.add(cognomeField);
		panel.add(mail);
		panel.add(mailField);
		panel.add(eta);
		panel.add(etaField);
		panel.add(tipo);
		panel.add(tipoField);
		panel.add(pw);
		panel.add(passwordField);
		panel.add(stipendio);
		panel.add(stipField);

		panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		Color or = new Color(250, 140, 0);

		errorLabel = new JLabel("*Errore: Dipendente già presente");
		errorLabel.setForeground(Color.RED);
		errorLabel.setVisible(false);

		regPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		regPanel.setBackground(CBACK);

		registraButton = new JButton("Registra Dipendente");
		registraButton.setBackground(or);
		registraButton.setFont(new Font("Arial", Font.BOLD, 13));
		registraButton.setBorder(BorderFactory.createLineBorder(or, 6, false));

		registraButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String nome = nomeField.getText();
				String cognome = cognomeField.getText();
				String mail = mailField.getText();
				String tipo = (String) tipoField.getSelectedItem();
				String password = new String(passwordField.getPassword());
				int eta = Integer.parseInt(etaField.getText());
				double stipendio = Double.parseDouble(stipField.getText());

				controller.registraDipendente(nome, cognome, mail, password, eta, stipendio, tipo);
			}
		});

		backButton = new JButton("Indietro");
		backButton.setBackground(or);
		backButton.setFont(new Font("Arial", Font.BOLD, 13));
		backButton.setBorder(BorderFactory.createLineBorder(or, 6, false));

		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				previousFrame.setVisible(true);
				dispose();
			}
		});

		regPanel.add(registraButton);
		regPanel.add(errorLabel);
		regPanel.add(backButton);
		panel.add(regPanel);

		getContentPane().add(panel, BorderLayout.CENTER);

		setSize(480, 640);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public void setController(RegistraDipController controller) {
		this.controller = controller;
	}

	public void mostraErrore() {
		errorLabel.setVisible(true);
	}

	public void mostraSuccesso() {
		errorLabel.setVisible(false);
	}
}
