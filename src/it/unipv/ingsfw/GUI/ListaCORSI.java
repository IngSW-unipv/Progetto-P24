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
import DB.IscrittoalcorsoDAO;
import NextFit.ClienteAbbonato;
import NextFit.Corsi;

public class ListaCORSI extends JFrame {

	private ArrayList<JButton> coursesButtons;
	private JPanel panel, backpanel;
	private JButton back;
	private JLabel CORSI;
	private boolean[] isIscritto;

	public ListaCORSI(Corsi co, ClienteAbbonato clienteAbbonato, LatoClienteGui parent) {
		setTitle("Interfaccia Corsi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		isIscritto = new boolean[co.getC()];

		GridLayout gridLayout = new GridLayout(0, 2); // Imposta un layout a due colonne
		gridLayout.setHgap(10); // Imposta lo spazio orizzontale tra i bottoni a 10 pixel
		gridLayout.setVgap(10);
		panel = new JPanel(gridLayout);
		Color CBACK = new Color(28, 28, 28);
		panel.setBackground(CBACK);

		coursesButtons = new ArrayList<>();
		panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		CORSI = new JLabel("<html><font color='orange'>COR</font><font color='white'>SI</font></html>");
		CORSI.setFont(new Font("Rockwell", Font.BOLD, 40));

		panel.add(CORSI);

		backpanel = new JPanel(new FlowLayout());
		backpanel.setBackground(CBACK);
		Color CBUT = new Color(40, 40, 40);
		Color or = new Color(250, 140, 0);

		back = new JButton("indietro");
		back.setBackground(or);
		back.setFont(new Font("Arial", Font.BOLD, 13));

		back.setBorder(BorderFactory.createLineBorder(or, 6, false));
		back.setMaximumSize(new Dimension(100, 30));
		panel.add(new JLabel());

		for (int i = 0; i <= co.getC() - 1; i++) {
			JButton button = new JButton(co.getCorso(i).getNome());
			int n = i;
			button.setBackground(CBUT);
			button.setPreferredSize(new Dimension(200, 100));
			button.setFont(new Font("Rockwell", Font.BOLD, 20));
			button.setForeground(Color.white);
			coursesButtons.add(button);
			panel.add(button);
			isIscritto[n] = false;
			if (co.iscrittoPresente(clienteAbbonato, co.getCorso(n))) {
				button.setText(co.getCorso(n).getNome() + " - iscritto");
				isIscritto[n] = true;
			}
			button.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					CorsiDAO dao = new CorsiDAO();
					IscrittoalcorsoDAO dao1 = new IscrittoalcorsoDAO();

					if (!isIscritto[n]) {
						co.getCorso(n).aggPalCorso();
						System.out.println(co.getCorso(n).getNp());
						JButton button = (JButton) e.getSource();
						button.setText(co.getCorso(n).getNome() + " - iscritto");

						co.aggIsAlCorso(clienteAbbonato, co.getCorso(n));

						dao1.insertIscrizione(clienteAbbonato, co.getCorso(n));
						dao.upIscritti(co.getCorso(n).getNome());

						isIscritto[n] = true;

						co.visuClisCorsi();
					} else {
						co.getCorso(n).eliPdalCorso();
						System.out.println(co.getCorso(n).getNp());
						JButton button = (JButton) e.getSource();
						button.setText(co.getCorso(n).getNome());

						co.elidalCorso(co.trovaIscritto(clienteAbbonato, co.getCorso(n)));

						dao1.deleteIscrizione(clienteAbbonato, co.getCorso(n));
						dao.dwIscritti(co.getCorso(n).getNome());

						isIscritto[n] = false;

						co.visuClisCorsi();
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