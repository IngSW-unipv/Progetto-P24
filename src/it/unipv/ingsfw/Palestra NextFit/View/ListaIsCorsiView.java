package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Controller.NavigationController;
import Model.ClienteAbbonato;
import Model.Corsi;
import Model.Corso;
import Model.Dipendente;
import Model.IscrittoalCorso;

public class ListaIsCorsiView extends JFrame {

	private JPanel panel, backpanel;
	private JButton back;
	private JLabel titolo;
	private ArrayList<JLabel> iscrittiLabels;

	public ListaIsCorsiView(Dipendente dipendente, Corsi corsi, ListaCorsiPropView parent, Corso corso) {
		setTitle("Lista Iscritti al Corso");
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panel = new JPanel(new GridLayout(0, 1));
		Color CBACK = new Color(28, 28, 28);
		panel.setBackground(CBACK);

		iscrittiLabels = new ArrayList<>();
		panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		titolo = new JLabel("<html><font color='orange'>ISC</font>RITTI</html>");
		titolo.setFont(new Font("Rockwell", Font.BOLD, 40));
		panel.add(titolo);

		backpanel = new JPanel(new FlowLayout());
		backpanel.setBackground(CBACK);
		Color CBUT = new Color(40, 40, 40);
		Color or = new Color(250, 140, 0);

		back = new JButton("Indietro");
		back.setBackground(or);
		back.setFont(new Font("Arial", Font.BOLD, 13));
		back.setBorder(BorderFactory.createLineBorder(or, 6, false));
		back.setMaximumSize(new Dimension(100, 30));
		NavigationController nc = new NavigationController();

		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				nc.indietro(parent);
				dispose();

			}
		});
		panel.add(new JLabel());

		// Corso corso = corsi.getCorsodalCorsista(dipendente);
		JLabel label = new JLabel("Iscritti a: " + corso);

		// Aggiungi i nomi degli iscritti al corso come etichette al pannello
		for (IscrittoalCorso iscritto : corsi.getIscrittiCorso(corso)) {
			ClienteAbbonato cliente = iscritto.getCliente();
			label = new JLabel(cliente.getCliente().getNome() + " " + cliente.getCliente().getCognome());
			label.setForeground(Color.white);
			iscrittiLabels.add(label);
			panel.add(label);
		}

		JScrollPane scrollPane = new JScrollPane(panel);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		getContentPane().add(scrollPane, BorderLayout.CENTER);

		panel.add(new JLabel());
		backpanel.add(back);
		panel.add(backpanel);

		setSize(480, 640);
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
