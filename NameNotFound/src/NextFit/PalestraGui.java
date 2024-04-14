package NextFit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PalestraGui extends JFrame {
	private JTextField nomeField, cognomeField, mailField, etaField;
	private JButton registraButton;
	private JPasswordField passwordField;

	public PalestraGui(Palestra palestra, Proprietario proprietario, CreaClAbbo creabbo,Corsi co) {

		setTitle("Registrazione Cliente Palestra");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel(new GridLayout(6, 2));

		nomeField = new JTextField();
		cognomeField = new JTextField();
		mailField = new JTextField();
		etaField = new JTextField();
		passwordField = new JPasswordField();

		panel.add(new JLabel("Nome:"));
		panel.add(nomeField);
		panel.add(new JLabel("Cognome:"));
		panel.add(cognomeField);
		panel.add(new JLabel("Email:"));
		panel.add(mailField);
		panel.add(new JLabel("Età:"));
		panel.add(etaField);
		panel.add(new JLabel("Password:"));
		panel.add(passwordField);

		registraButton = new JButton("Registra");
		registraButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String nome = nomeField.getText();
				String cognome = cognomeField.getText();
				String mail = mailField.getText();
				String password = new String(passwordField.getPassword());
				int eta = Integer.parseInt(etaField.getText());

				Cliente cliente = new Cliente(nome, cognome, mail, password, eta);
				palestra.registraCliente(cliente);
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						new LatoClienteGui(co,cliente,palestra);
						new AbbonamentoGui(cliente, proprietario, creabbo);
					}
				});
			}
		});

		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		buttonPanel.add(registraButton);

		getContentPane().add(panel, BorderLayout.CENTER);
		getContentPane().add(buttonPanel, BorderLayout.SOUTH);

		setSize(480, 640); // Impostiamo le dimensioni della finestra
		setLocationRelativeTo(null); // Posizioniamo la finestra al centro dello schermo
		setVisible(true);
	}

}