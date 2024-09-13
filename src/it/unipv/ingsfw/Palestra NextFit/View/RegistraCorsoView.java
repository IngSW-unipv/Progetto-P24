package View;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import NextFit.Corsi;
import NextFit.Dipendente;
import NextFit.Palestra;
import Controller.NavigationController;
import Controller.RegistraCorsoController;

public class RegistraCorsoView extends JFrame {
	private JTextField nomeCField, maxField;
	private JButton registraButton;
	private JLabel nomeC, nome, maxis, NEXTFIT, errorLabel;
	private JComboBox<String> tipoField;
	private JPanel regPanel;
	private RegistraCorsoController controller;
	private JButton backButton;
	private JFrame previousFrame;

	public RegistraCorsoView(Corsi corsi, Palestra palestra, JFrame previousFrame) {
		this.previousFrame = previousFrame;
		
		setTitle("Registrazione Corso");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		GridLayout gridLayout = new GridLayout(0, 1);
		gridLayout.setHgap(10);
		JPanel panel = new JPanel(gridLayout);
		Color CBACK = new Color(28, 28, 28);

		panel.setBackground(CBACK);

		NEXTFIT = new JLabel("<html><font color='orange'>REGI</font><font color='white'>STRAZIONE</font></html>");
		NEXTFIT.setFont(new Font("Rockwell", Font.BOLD, 40));
		panel.add(NEXTFIT);

		nomeCField = new JTextField();
		nomeCField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
		nomeCField.setForeground(Color.white);

		Dipendente[] d = new Dipendente[palestra.contaDip("corsista")];
		String[] options = new String[palestra.contaDip("corsista")];

		for (int i = 0; i < palestra.contaDip("corsista"); i++) {
			d[i] = palestra.getDIP("corsista", i);
			options[i] = d[i].getNome() + " " + d[i].getCognome() + " " + d[i].getMail();
		}

		tipoField = new JComboBox<>(options);
		tipoField.setForeground(Color.white);

		maxField = new JTextField();
		maxField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
		maxField.setForeground(Color.white);

		Color CBUT = new Color(40, 40, 40);

		Border roundedBorder = BorderFactory.createCompoundBorder(new LineBorder(Color.black, 2, true),
				new EmptyBorder(0, 10, 0, 10));

		nomeCField.setBorder(roundedBorder);
		nomeCField.setBackground(CBUT);

		maxField.setBorder(roundedBorder);
		maxField.setBackground(CBUT);

		nomeC = new JLabel("Nome Corso:");
		nomeC.setForeground(Color.white);
		nome = new JLabel("Corsista:");
		nome.setForeground(Color.white);
		maxis = new JLabel("Numero max iscritti:");
		maxis.setForeground(Color.white);

		panel.add(nomeC);
		panel.add(nomeCField);
		panel.add(nome);
		panel.add(tipoField);
		tipoField.setBorder(roundedBorder);
		tipoField.setBackground(CBUT);

		panel.add(maxis);
		panel.add(maxField);
		panel.add(Box.createRigidArea(new Dimension(0, 10)));
		panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		Color or = new Color(250, 140, 0);

		errorLabel = new JLabel("*Errore corso già esistente o Corsista non trovato. ");
		errorLabel.setForeground(Color.RED);
		errorLabel.setVisible(false);

		regPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		regPanel.setBackground(CBACK);

		registraButton = new JButton("Registra Corso");
		registraButton.setBackground(or);
		registraButton.setFont(new Font("Arial", Font.BOLD, 13));
		registraButton.setBorder(BorderFactory.createLineBorder(or, 6, false));

		registraButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String nomeCorso = nomeCField.getText();
				String nomeCognome = (String) tipoField.getSelectedItem();
				String[] parts = nomeCognome.split(" ");
				String nome = parts[0];
				String cognome = parts[1];
				String mail = parts[2];
				int maxIscritti = Integer.parseInt(maxField.getText());

				controller.registraCorso(nomeCorso, nome, cognome, mail, maxIscritti);
			}
		});
		
		backButton = new JButton("Indietro");
		backButton.setBackground(or);
		backButton.setFont(new Font("Arial", Font.BOLD, 13));
		backButton.setBorder(BorderFactory.createLineBorder(or, 6, false));
		NavigationController nc = new NavigationController();

		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				nc.indietro(previousFrame);
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

	public void setController(RegistraCorsoController controller) {
		this.controller = controller;
	}

	public void mostraErrore() {
		errorLabel.setVisible(true);
	}

	public void mostraSuccesso() {
		errorLabel.setVisible(false);
	}
}
