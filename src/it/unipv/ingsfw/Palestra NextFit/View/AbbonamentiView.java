package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
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

import Controller.AbbonamentiController;
import DB.ClienteAbboDAO;
import NextFit.Cliente;
import NextFit.ClienteAbbonato;
import NextFit.Corsi;
import NextFit.Palestra;
import NextFit.Proprietario;
import NextFit.Richieste;

public class AbbonamentiView extends JFrame {
	private JButton mensile, semestrale, annuale;
	private JLabel AB, attenzione;
	protected ClienteAbbonato ca;

	public AbbonamentiView(Cliente c, Proprietario p, Palestra pa, Corsi co, Richieste r) {
		setTitle("scelta abbonamenti");

		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		Color CBACK = new Color(28, 28, 28);

		panel.setBackground(CBACK);

		AB = new JLabel("<html><font color='orange'>ABBO</font><font color='white'>NAMENTI</font></html>");
		AB.setFont(new Font("Rockwell", Font.BOLD, 40));

		panel.add(AB);

		panel.add(Box.createRigidArea(new Dimension(0, 20)));

		Color CBUT = new Color(40, 40, 40);

		mensile = new JButton("mensile");
		mensile.setFont(new Font("Rockwell", Font.BOLD, 20));
		mensile.setForeground(Color.white);
		mensile.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
		mensile.setBackground(CBUT);

		semestrale = new JButton("semestrale");
		semestrale.setFont(new Font("Rockwell", Font.BOLD, 20));
		semestrale.setForeground(Color.white);
		semestrale.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
		semestrale.setBackground(CBUT);

		annuale = new JButton("annuale");
		annuale.setFont(new Font("Rockwell", Font.BOLD, 20));
		annuale.setForeground(Color.white);
		annuale.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
		annuale.setBackground(CBUT);
		AbbonamentiController av = new AbbonamentiController(c, p, pa);

		mensile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				ca = av.mensile();

				new LatoClienteView(co, ca, pa, r);
				dispose();
			}
		});

		semestrale.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				ca = av.semestrale();

				new LatoClienteView(co, ca, pa, r);
				dispose();
			}
		});

		annuale.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				ca = av.annuale();

				new LatoClienteView(co, ca, pa, r);
				dispose();
			}
		});

		panel.add(mensile);
		panel.add(Box.createRigidArea(new Dimension(0, 20)));
		panel.add(semestrale);
		panel.add(Box.createRigidArea(new Dimension(0, 20)));
		panel.add(annuale);
		panel.add(Box.createRigidArea(new Dimension(0, 20)));
		String testoat = "*Attenzione: qualora non si scegliesse un abbonamento e non si procedesse al pagamento non sarà possibile registrarsi e quindi accedere ai contenuti della palestra*";
		attenzione = new JLabel("<html><div style='width: 200px; text-align: center;'>" + testoat + "</div></html>");
		attenzione.setForeground(Color.RED);
		attenzione.setBorder(BorderFactory.createEmptyBorder(0, 85, 0, 0));

		panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		panel.add(attenzione);

		getContentPane().add(panel, BorderLayout.CENTER);

		setSize(480, 640);
		setLocationRelativeTo(null); // Posizioniamo la finestra al centro dello schermo
		setVisible(true);
	}
}
