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
import javax.swing.SwingUtilities;

import NextFit.ClienteAbbonato;
import NextFit.Corsi;
import NextFit.Dipendente;
import NextFit.Palestra;
import NextFit.PersonalTrainer;
import NextFit.Proprietario;

public class ProprietarioGui extends JFrame {

	private JButton RD, RC;
	private JLabel NEXTFIT;

	public ProprietarioGui(Proprietario proprietario, Palestra palestra, Corsi corsi) {
		setTitle("Pagina principale");

		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		Color CBACK = new Color(28, 28, 28);

		panel.setBackground(CBACK);

		NEXTFIT = new JLabel("<html><font color='orange'>NEXT</font><font color='white'>FIT</font></html>");
		NEXTFIT.setFont(new Font("Rockwell", Font.BOLD, 40));

		panel.add(NEXTFIT);

		panel.add(Box.createRigidArea(new Dimension(0, 20)));

		RD = new JButton("Registra Dipendente");

		Color CBUT = new Color(40, 40, 40);

		RD.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
		RD.setBackground(CBUT);
		RD.setFont(new Font("Rockwell", Font.BOLD, 20));
		RD.setForeground(Color.white);

		RD.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new RegistraDipGui(palestra);

			}
		});
		panel.add(RD);

		panel.add(Box.createRigidArea(new Dimension(0, 20)));

		RC = new JButton("Registra Corso");

		RC.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
		RC.setBackground(CBUT);
		RC.setFont(new Font("Rockwell", Font.BOLD, 20));
		RC.setForeground(Color.white);

		RC.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new RegistraCorso(corsi, palestra);

			}
		});
		panel.add(RC);

		panel.add(Box.createRigidArea(new Dimension(0, 20)));

		getContentPane().add(panel, BorderLayout.CENTER);

		panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		getContentPane().add(panel, BorderLayout.CENTER);

		setSize(480, 640);
		setLocationRelativeTo(null); // Posizioniamo la finestra al centro dello schermo
		setVisible(true);

	}

}
