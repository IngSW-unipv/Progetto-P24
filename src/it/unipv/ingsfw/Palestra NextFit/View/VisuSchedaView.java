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
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import Controller.NavigationController;
import Model.ClienteAbbonato;
import Model.Esercizio;
import Model.Palestra;
import Model.RichiestaAlPT;
import Model.Richieste;
import Model.Scheda;

public class VisuSchedaView extends JFrame {
	private JLabel SCHEDA, PT, ES1, ES2, ES3, ES4, ES5;
	private JButton back;

	public VisuSchedaView(Richieste ri, ClienteAbbonato ca, LatoClienteView parent) {
		setTitle("schede");

		int cod = ri.getCodiceSchedaCliente(ca);
		Scheda scheda = ri.getScheda(cod);
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		Color CBACK = new Color(28, 28, 28);

		panel.setBackground(CBACK);

		SCHEDA = new JLabel("<html><font color='orange'>SCH</font><font color='white'>EDA</font></html>");
		SCHEDA.setFont(new Font("Rockwell", Font.BOLD, 40));
		PT = new JLabel("<html><font color='orange'>SCH</font><font color='white'>EDA</font></html>");
		PT.setFont(new Font("Rockwell", Font.BOLD, 40));

		panel.add(SCHEDA);

		panel.add(Box.createRigidArea(new Dimension(0, 20)));
		Color CBUT = new Color(40, 40, 40);

		ES1 = new JLabel(scheda.getNomeEsercizio(0));
		ES1.setForeground(Color.white);
		ES2 = new JLabel(scheda.getNomeEsercizio(1));
		ES2.setForeground(Color.white);
		ES3 = new JLabel(scheda.getNomeEsercizio(2));
		ES3.setForeground(Color.white);
		ES4 = new JLabel(scheda.getNomeEsercizio(3));
		ES4.setForeground(Color.white);
		ES5 = new JLabel(scheda.getNomeEsercizio(4));
		ES5.setForeground(Color.white);

		panel.add(ES1);
		panel.add(Box.createRigidArea(new Dimension(0, 20)));
		panel.add(ES2);
		panel.add(Box.createRigidArea(new Dimension(0, 20)));
		panel.add(ES3);
		panel.add(Box.createRigidArea(new Dimension(0, 20)));
		panel.add(ES4);
		panel.add(Box.createRigidArea(new Dimension(0, 20)));
		panel.add(ES5);

		Color or = new Color(250, 140, 0);
		back = new JButton("indietro");
		back.setBackground(or);
		back.setFont(new Font("Arial", Font.BOLD, 13));
		back.setMaximumSize(new Dimension(100, 30));
		back.setBorder(BorderFactory.createLineBorder(or, 6, false));
		NavigationController nc = new NavigationController();

		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				nc.indietro(parent);
				dispose();

			}

		});
		panel.add(Box.createRigidArea(new Dimension(0, 20)));
		panel.add(back);
		getContentPane().add(panel, BorderLayout.CENTER);

		panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		setSize(480, 640); // Impostiamo le dimensioni della finestra
		setLocationRelativeTo(null); // Posizioniamo la finestra al centro dello schermo
		setVisible(true);

	}

}
