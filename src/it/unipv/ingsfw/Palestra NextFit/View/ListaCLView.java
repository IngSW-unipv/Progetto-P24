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
import NextFit.Corsi;
import NextFit.Palestra;
import NextFit.PersonalTrainer;
import NextFit.RichiestaAlPT;
import NextFit.Richieste;

public class ListaCLView extends JFrame {
	private ArrayList<JButton> dipButtons;
	private JPanel panel, backpanel;
	private JButton back;
	private JLabel CLIENTI;
	private Palestra p;
	private Corsi co;

	public ListaCLView(Palestra p, Richieste r, PersonalTrainer pt, LatoPTView parent) {

		setTitle("Interfaccia dipendenti");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // comando per chiudere tutte le altre schede

		GridLayout gridLayout = new GridLayout(0, 2);
		gridLayout.setHgap(10);
		gridLayout.setVgap(10);
		panel = new JPanel(gridLayout);
		panel.setBackground(new Color(28, 28, 28));
		panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		dipButtons = new ArrayList<>();

		CLIENTI = new JLabel("<html><font color='orange'>CLI</font><font color='white'>ENTI</font></html>");
		CLIENTI.setFont(new Font("Rockwell", Font.BOLD, 30));
		panel.add(CLIENTI);

		backpanel = new JPanel(new FlowLayout());
		backpanel.setBackground(new Color(28, 28, 28));

		back = new JButton("indietro");
		back.setBackground(new Color(250, 140, 0));
		back.setFont(new Font("Arial", Font.BOLD, 13));
		back.setBorder(BorderFactory.createLineBorder(new Color(250, 140, 0), 6, false));
		back.setMaximumSize(new Dimension(100, 30));
		NavigationController nc = new NavigationController();

		addBackButton();

		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				nc.indietro(parent);
				dispose();
			}
		});

		for (int i = 0; i < r.getR(pt); i++) {
			JButton button = new JButton(r.getNomeCL(i, pt) + " " + r.getCognomeCL(i, pt));
			final int n = i;
			button.setBackground(new Color(40, 40, 40));
			button.setPreferredSize(new Dimension(200, 100));
			button.setFont(new Font("Rockwell", Font.BOLD, 20));
			button.setForeground(Color.white);
			dipButtons.add(button);
			panel.add(button);
			button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					new CreaSchedaView(p, r.getrichiestapt(pt, n), r);
				}
			});
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

	private void addBackButton() {
		panel.add(new JLabel());
		backpanel.add(back);
		panel.add(backpanel);
	}

}
