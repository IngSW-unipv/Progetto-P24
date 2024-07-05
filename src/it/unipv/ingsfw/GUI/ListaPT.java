package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

import DB.RichiesteDAO;
import NextFit.ClienteAbbonato;
import NextFit.Palestra;
import NextFit.PersonalTrainer;
import NextFit.Richieste;

public class ListaPT extends JFrame {

	private ArrayList<JButton> trainerButtons;
	private JPanel panel, backpanel;
	private JButton back;
	private JLabel PT;
	private boolean[] isIscritto;
	private boolean isRichiestaAttiva;

	public ListaPT(Palestra p, ClienteAbbonato clienteAbbonato, LatoClienteGui parent, Richieste r) {
		setTitle("Interfaccia Personal Trainer");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		isIscritto = new boolean[p.contaDip("personaltrainer")];
		isRichiestaAttiva = false;

		GridLayout gridLayout = new GridLayout(0, 2); // Imposta un layout a due colonne
		gridLayout.setHgap(10); // Imposta lo spazio orizzontale tra i bottoni a 10 pixel
		gridLayout.setVgap(10);
		panel = new JPanel(gridLayout);
		Color CBACK = new Color(28, 28, 28);
		panel.setBackground(CBACK);

		trainerButtons = new ArrayList<>();
		panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		PT = new JLabel("<html><font color='orange'>TRAI</font><font color='white'>NER</font></html>");
		PT.setFont(new Font("Rockwell", Font.BOLD, 40));
		panel.add(PT);

		backpanel = new JPanel(new FlowLayout());
		backpanel.setBackground(CBACK);
		Color CBUT = new Color(40, 40, 40);
		Color or = new Color(250, 140, 0);

		back = new JButton("indietro");
		back.setBackground(or);
		back.setFont(new Font("Arial", Font.BOLD, 13));

		back.setBorder(BorderFactory.createLineBorder(or, 6, false));
		back.setMaximumSize(new Dimension(100, 30));

		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				parent.setVisible(true);
				dispose(); // Chiude la finestra corrente
			}
		});

		panel.add(new JLabel());

		// Inizializza i pulsanti dei personal trainer
		for (int i = 0; i <= p.contaDip("personaltrainer") - 1; i++) {
			PersonalTrainer pt = (PersonalTrainer) p.getDIP("Personaltrainer", i);
			JButton button = new JButton(
					p.getDIP("Personaltrainer", i).getNome() + " " + p.getDIP("Personaltrainer", i).getCognome());
			int n = i;
			button.setBackground(CBUT);
			button.setPreferredSize(new Dimension(200, 100));
			button.setFont(new Font("Rockwell", Font.BOLD, 20));
			button.setForeground(Color.white);
			trainerButtons.add(button);
			panel.add(button);
			isIscritto[n] = false;
			if (r.richiestaPresente(clienteAbbonato, pt)) {
				button.setText(p.getDIP("Personaltrainer", i).getNome() + " "
						+ p.getDIP("Personaltrainer", i).getCognome() + " - richiesto");
				isIscritto[n] = true;
				isRichiestaAttiva = true;
			}
			button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					RichiesteDAO dao = new RichiesteDAO();
					JButton button = (JButton) e.getSource();

					if (!isIscritto[n] && !isRichiestaAttiva) {
						r.aggRichiesta(pt, clienteAbbonato, 0);
						button.setText(p.getDIP("Personaltrainer", n).getNome() + " "
								+ p.getDIP("Personaltrainer", n).getCognome() + " - richiesto");

						dao.insertRichiesta(r.ricarcaRichiesta(clienteAbbonato, pt));

						isIscritto[n] = true;
						isRichiestaAttiva = true;
						r.visualizzaRichieste();
					} else if (isIscritto[n]) {
						button.setText(p.getDIP("Personaltrainer", n).getNome() + " "
								+ p.getDIP("Personaltrainer", n).getCognome());

						dao.deleteRichiesta(r.ricarcaRichiesta(clienteAbbonato, pt));
						r.eliminaRichiesta(r.ricarcaRichiesta(clienteAbbonato, pt));

						isIscritto[n] = false;
						isRichiestaAttiva = false;
						r.visualizzaRichieste();
					} else {
						JOptionPane.showMessageDialog(null,
								"Hai giÃ  una richiesta attiva. Rimuovi la richiesta esistente prima di aggiungerne un'altra.",
								"Errore", JOptionPane.ERROR_MESSAGE);
					}
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
}