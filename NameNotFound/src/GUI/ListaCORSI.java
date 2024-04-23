package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import DB.CorsiDAO;
import NextFit.ClienteAbbonato;
import NextFit.Corsi;

public class ListaCORSI extends JFrame {

	private ArrayList<JButton> coursesButtons;
	private JPanel panel;
	private JButton back;
	private JLabel CORSI;

	public ListaCORSI(Corsi co, ClienteAbbonato clienteAbbonato, LatoClienteGui parent) {
		setTitle("Interfaccia Corsi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		GridBagLayout gridBagLayout = new GridBagLayout();

		panel = new JPanel(gridBagLayout);

		Color CBACK = new Color(28, 28, 28);

		panel.setBackground(CBACK);
		GridBagConstraints gbc = new GridBagConstraints();

		coursesButtons = new ArrayList<>();
		panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.gridy = 0;
		gbc.gridx = 0;
		gbc.weightx = 1; // Peso orizzontale
		gbc.weighty = 1; // Peso verticale
		gbc.fill = GridBagConstraints.BOTH;

		JLabel CORSI = new JLabel("<html><font color='orange'>COR</font><font color='white'>SI</font></html>");
		CORSI.setFont(new Font("Rockwell", Font.BOLD, 40));
		gbc.gridx = 0;
		gbc.gridy = 0;
		panel.add(CORSI, gbc);

		// Resetta le coordinate per la griglia dei bottoni
		gbc.gridx = 0;
		gbc.gridy = 1;

		Color CBUT = new Color(40, 40, 40);
		Color or = new Color(250, 140, 0);

		back = new JButton("indietro");
		back.setBackground(or);
		back.setFont(new Font("Arial", Font.BOLD, 13));
		back.setMaximumSize(new Dimension(100, 30));
		back.setBorder(BorderFactory.createLineBorder(or, 6, false));

		// mettere nel for num max PT
		for (int i = 0; i <= co.getC() - 1; i++) {
			JButton button = new JButton(co.getCorso(i).getNome());
			int n = i;
			button.setBackground(CBUT);
			button.setFont(new Font("Rockwell", Font.BOLD, 20));
			button.setForeground(Color.white);
			gbc.gridx = n % 2; // Alternare tra 0 e 1 per le colonne
			gbc.gridy = 1 + n / 2; // Incrementare per ogni coppia di componenti
			coursesButtons.add(button);
			panel.add(button, gbc);
			button.addActionListener(new ActionListener() {
				boolean isIscritto = false;

				@Override
				public void actionPerformed(ActionEvent e) {
					CorsiDAO dao = new CorsiDAO();

					if (!isIscritto) {
						co.getCorso(n).aggPalCorso();
						System.out.println(co.getCorso(n).getNp());
						JButton button = (JButton) e.getSource();
						button.setText(co.getCorso(n).getNome() + " - iscritto");

						dao.upIscritti(co.getCorso(n).getNome());

						isIscritto = true;
					} else {
						co.getCorso(n).eliPdalCorso();
						System.out.println(co.getCorso(n).getNp());
						JButton button = (JButton) e.getSource();
						button.setText(co.getCorso(n).getNome());

						dao.dwIscritti(co.getCorso(n).getNome());

						isIscritto = false;
					}

				}
			});

		}

		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				parent.setVisible(true);
				dispose(); // Chiude la finestra corrente

			}

		});

		JScrollPane scrollPane = new JScrollPane(panel);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		getContentPane().add(scrollPane, BorderLayout.CENTER);

		gbc.gridx = 1;
		gbc.gridy = gbc.gridy + 1;
		gbc.weightx = 0; // Peso orizzontale
		gbc.weighty = 0; // Peso verticale
		gbc.fill = GridBagConstraints.NONE;
		panel.add(back, gbc);

		setSize(480, 640);
		setLocationRelativeTo(null);
		setVisible(true);
	}

}