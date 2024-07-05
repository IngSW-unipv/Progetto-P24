package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
	private JTextField nomeCField, maxField;
	private JButton registraButton;
	private JLabel nomeC, nome, cognome, mail, maxis, NEXTFIT, errorLabel;
	private String[] options;
	private JPanel regPanel;

	public RegistraCorso(Corsi corsi, Palestra palestra) {

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
		
		Dipendente[] d=new Dipendente[palestra.contaDip("corsista")];
		String[] options=new String[palestra.contaDip("corsista")];
		
		for(int i=0;i<palestra.contaDip("corsista");i++)
		{
			d[i]=palestra.getDIP("corsista", i);
			options[i] = d[i].getNome() + " " + d[i].getCognome() + " " +d[i].getMail() ;
		}
		

		JComboBox<String> tipoField = new JComboBox<>(options);
		tipoField.setForeground(Color.white);
		
		
		maxField = new JTextField();
		maxField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
		maxField.setForeground(Color.white);

		Color CBUT = new Color(40, 40, 40);

		Border roundedBorder = BorderFactory.createCompoundBorder(new LineBorder(Color.black, 2, true),
				new EmptyBorder(0, 10, 0, 10) // Margine interno
		);

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

		JLabel errorLabel = new JLabel("*Errore corso già esistente o Corsista non trovato. ");
		errorLabel.setForeground(Color.RED);

		regPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		regPanel.setBackground(CBACK);
		
		registraButton = new JButton("Registra Corso");

		registraButton.setBackground(or);
		registraButton.setFont(new Font("Arial", Font.BOLD, 13));

		registraButton.setBorder(BorderFactory.createLineBorder(or, 6, false));

		registraButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String ncorso = nomeCField.getText();
				
				String nomecognome = (String) tipoField.getSelectedItem();
				String[] parts = nomecognome.split(" ");
				
				String nome = parts[0];
                String cognome = parts[1];
				String mail = parts[2];
				int max = Integer.parseInt(maxField.getText());

				Dipendente d = palestra.ricercaCorsista(nome, cognome, mail);

				if (d.getTipo().equals("corsista")) {
					Corso corso = new Corso(ncorso, d, max, 0);
					corsi.aggCorsi(corso);
				}

				corsi.visuCorsi();

				regPanel.add(registraButton);
				regPanel.add(errorLabel);

				if (corsi.ricercaCorso(ncorso, d) != null) {
					regPanel.remove(errorLabel);
					panel.add(regPanel);
					panel.revalidate();
					panel.repaint();
				} else {

					panel.add(regPanel);
					// Aggiorna il pannello
					panel.revalidate();
					panel.repaint();

				}

			}
		});
		regPanel.add(registraButton);
		panel.add(regPanel);

		getContentPane().add(panel, BorderLayout.CENTER);

		setSize(480, 640); // Impostiamo le dimensioni della finestra
		setLocationRelativeTo(null); // Posizioniamo la finestra al centro dello schermo
		setVisible(true);
	}
}
