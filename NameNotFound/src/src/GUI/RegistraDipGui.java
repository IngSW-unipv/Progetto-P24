package GUI;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import NextFit.Cliente;
import NextFit.Corsi;
import NextFit.Dipendente;
import NextFit.Palestra;
import NextFit.Proprietario;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistraDipGui extends JFrame {
	private JTextField nomeField, cognomeField, mailField, etaField, tipoField, stipField;
	private JButton registraButton;
	private JPasswordField passwordField;
	private JLabel nome, cognome, pw, eta, mail, tipo, stipendio, NEXTFIT, errorLabel;

	public RegistraDipGui(Palestra palestra) {

		setTitle("Registrazione Dipendente Palestra");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
		tipoField = new JTextField();
		tipoField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
		tipoField.setForeground(Color.white);
		passwordField = new JPasswordField();
		passwordField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
		passwordField.setForeground(Color.white);
		stipField = new JTextField();
		stipField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
		stipField.setForeground(Color.white);

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
		tipoField.setBorder(roundedBorder);
		tipoField.setBackground(CBUT);
		passwordField.setBorder(roundedBorder);
		passwordField.setBackground(CBUT);
		stipField.setBorder(roundedBorder);
		stipField.setBackground(CBUT);

		nome = new JLabel("Nome:");
		nome.setForeground(Color.white);
		cognome = new JLabel("Cognome:");
		cognome.setForeground(Color.white);
		eta = new JLabel("Età:");
		eta.setForeground(Color.white);
		mail = new JLabel("Email:");
		mail.setForeground(Color.white);
		tipo = new JLabel("Tipologia:");
		tipo.setForeground(Color.white);
		pw = new JLabel("Password:");
		pw.setForeground(Color.white);
		stipendio = new JLabel("Stipendio:");
		stipendio.setForeground(Color.white);

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
		panel.add(tipo);
		panel.add(Box.createRigidArea(new Dimension(0, 20)));
		panel.add(tipoField);
		panel.add(Box.createRigidArea(new Dimension(0, 20)));
		panel.add(pw);
		panel.add(Box.createRigidArea(new Dimension(0, 20)));
		panel.add(passwordField);
		panel.add(Box.createRigidArea(new Dimension(0, 20)));
		panel.add(stipendio);
		panel.add(Box.createRigidArea(new Dimension(0, 20)));
		panel.add(stipField);
		panel.add(Box.createRigidArea(new Dimension(0, 20)));

		panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		Color or = new Color(250, 140, 0);

		JLabel errorLabel = new JLabel("*Errore: minorenne o Dipendente già presente");
		errorLabel.setForeground(Color.RED);

		registraButton = new JButton("Registra Dipendente");

		registraButton.setBackground(or);
		registraButton.setFont(new Font("Arial", Font.BOLD, 13));
		registraButton.setMaximumSize(new Dimension(100, 30));
		registraButton.setBorder(BorderFactory.createLineBorder(or, 6, false));

		registraButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String nome = nomeField.getText();
				String cognome = cognomeField.getText();
				String mail = mailField.getText();
				String tipo = tipoField.getText();
				String password = new String(passwordField.getPassword());
				int eta = Integer.parseInt(etaField.getText());
				double stipendio = Double.parseDouble(stipField.getText());

				Dipendente d = palestra.creaDipendente(nome, cognome, mail, password, eta, stipendio, tipo);
				// palestra.registraDipendente(d);

				JPanel buttonPanel = new JPanel();
				buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
				buttonPanel.setBackground(CBACK);
				buttonPanel.add(registraButton);
				buttonPanel.add(errorLabel);

				if (palestra.registraDipendente(d) == true) {

					buttonPanel.add(registraButton);
					buttonPanel.remove(errorLabel);
					panel.add(buttonPanel);
					panel.revalidate();
					panel.repaint();
				}

				/*
				 * SwingUtilities.invokeLater(new Runnable() { public void run() { // new
				 * LatoClienteGui(co, cliente, palestra); //new AbbonamentoGui(cliente,
				 * proprietario, palestra); } }); }
				 */ else {

					panel.add(buttonPanel);

					// Aggiorna il pannello
					panel.revalidate();
					panel.repaint();

				}

			}
		});
		panel.add(registraButton);

		getContentPane().add(panel, BorderLayout.CENTER);

		setSize(480, 640); // Impostiamo le dimensioni della finestra
		setLocationRelativeTo(null); // Posizioniamo la finestra al centro dello schermo
		setVisible(true);
	}

}
