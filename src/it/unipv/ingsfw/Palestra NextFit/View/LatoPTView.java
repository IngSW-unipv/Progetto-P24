package View;

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

import Model.ClienteAbbonato;
import Model.Corsi;
import Model.Dipendente;
import Model.Palestra;
import Model.PersonalTrainer;
import Model.Richieste;

public class LatoPTView extends JFrame {
	private JButton LC;
	private JLabel NEXTFIT;
	private PersonalTrainer PT;
	private LatoPTView LatoPTGui;
	private ListaCLView ListaCL;

	public LatoPTView(Dipendente dipendente, Palestra palestra, Richieste r) {
		setTitle("Pagina principale");
		PT = (PersonalTrainer) dipendente;

		LatoPTGui = this;
		ListaCL = new ListaCLView(palestra, r, PT, LatoPTGui);
		ListaCL.setVisible(false);

		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		Color CBACK = new Color(28, 28, 28);

		panel.setBackground(CBACK);

		NEXTFIT = new JLabel("<html><font color='orange'>NEXT</font><font color='white'>FIT</font></html>");
		NEXTFIT.setFont(new Font("Rockwell", Font.BOLD, 40));

		panel.add(NEXTFIT);

		panel.add(Box.createRigidArea(new Dimension(0, 20)));

		LC = new JButton("Lista clienti");

		Color CBUT = new Color(40, 40, 40);

		LC.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
		LC.setBackground(CBUT);
		LC.setFont(new Font("Rockwell", Font.BOLD, 20));
		LC.setForeground(Color.white);

		LC.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ListaCL.setVisible(true);
				dispose();
			}
		});
		panel.add(LC);
		panel.add(Box.createRigidArea(new Dimension(0, 20)));

		getContentPane().add(panel, BorderLayout.CENTER);

		panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		getContentPane().add(panel, BorderLayout.CENTER);

		setSize(480, 640);
		setLocationRelativeTo(null); // Posizioniamo la finestra al centro dello schermo
		setVisible(true);
	}

}
