package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import NextFit.Corsi;
import NextFit.Corso;
import NextFit.Dipendente;
import NextFit.Palestra;

public class RegistraCorso extends JFrame {
	private JTextField nomeCField, nomeField, cognomeField, maxField;
	private JButton registraButton;
	private JLabel nomeC, nome, cognome, maxis, NEXTFIT, errorLabel;

	public RegistraCorso(Corsi corsi, Palestra palestra) {

		setTitle("Registrazione Corso");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		Color CBACK = new Color(28, 28, 28);

		panel.setBackground(CBACK);

		NEXTFIT = new JLabel("<html><font color='orange'>REGI</font><font color='white'>STRAZIONE</font></html>");
		NEXTFIT.setFont(new Font("Rockwell", Font.BOLD, 40));

		panel.add(NEXTFIT);

		panel.add(Box.createRigidArea(new Dimension(0, 20)));

		nomeCField = new JTextField();
		nomeCField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
		nomeCField.setForeground(Color.white);
		nomeField = new JTextField();
		nomeField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
		nomeField.setForeground(Color.white);
		cognomeField = new JTextField();
		cognomeField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
		cognomeField.setForeground(Color.white);
		maxField = new JTextField();
		maxField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
		maxField.setForeground(Color.white);

		Color CBUT = new Color(40, 40, 40);

		Border roundedBorder = BorderFactory.createCompoundBorder(new LineBorder(Color.black, 2, true),
				new EmptyBorder(0, 10, 0, 10) // Margine interno
		);

		nomeCField.setBorder(roundedBorder);
		nomeCField.setBackground(CBUT);
		nomeField.setBorder(roundedBorder);
		nomeField.setBackground(CBUT);
		cognomeField.setBorder(roundedBorder);
		cognomeField.setBackground(CBUT);
		maxField.setBorder(roundedBorder);
		maxField.setBackground(CBUT);

		nomeC = new JLabel("Nome Corso:");
		nomeC.setForeground(Color.white);
		nome = new JLabel("Nome Corsista:");
		nome.setForeground(Color.white);
		cognome = new JLabel("Cognome Corsista:");
		cognome.setForeground(Color.white);
		maxis = new JLabel("Numero max iscritti:");
		maxis.setForeground(Color.white);

		panel.add(nomeC);
		panel.add(Box.createRigidArea(new Dimension(0, 20)));
		panel.add(nomeCField);
		panel.add(Box.createRigidArea(new Dimension(0, 20)));
		panel.add(nome);
		panel.add(Box.createRigidArea(new Dimension(0, 20)));
		panel.add(nomeField);
		panel.add(Box.createRigidArea(new Dimension(0, 20)));
		panel.add(cognome);
		panel.add(Box.createRigidArea(new Dimension(0, 20)));
		panel.add(cognomeField);
		panel.add(Box.createRigidArea(new Dimension(0, 20)));
		panel.add(maxis);
		panel.add(Box.createRigidArea(new Dimension(0, 20)));
		panel.add(maxField);
		panel.add(Box.createRigidArea(new Dimension(0, 20)));

		panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		Color or = new Color(250, 140, 0);

		JLabel errorLabel = new JLabel("*Errore corso già esistente o Corsista non trovato. ");
		errorLabel.setForeground(Color.RED);

		registraButton = new JButton("Registra Corso");

		registraButton.setBackground(or);
		registraButton.setFont(new Font("Arial", Font.BOLD, 13));
		registraButton.setMaximumSize(new Dimension(120, 30));
		registraButton.setBorder(BorderFactory.createLineBorder(or, 6, false));

		registraButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String ncorso = nomeCField.getText();
				String nome = nomeField.getText();
				String cognome = cognomeField.getText();
				int max = Integer.parseInt(maxField.getText());

				Dipendente d = palestra.ricercaCorsista(nome, cognome);

				if (d.getTipo().equals("corsista")) {
					Corso corso = new Corso(ncorso, d, max, 0);
					corsi.aggCorsi(corso);
				}
				
				corsi.visuCorsi();

				JPanel buttonPanel = new JPanel();
				buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
				buttonPanel.setBackground(CBACK);
				buttonPanel.add(registraButton);
				buttonPanel.add(errorLabel);

				if (corsi.ricercaCorso(ncorso, d) != null) {
					buttonPanel.add(registraButton);
					buttonPanel.remove(errorLabel);
					panel.add(buttonPanel);
					panel.revalidate();
					panel.repaint();
				} else {

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
