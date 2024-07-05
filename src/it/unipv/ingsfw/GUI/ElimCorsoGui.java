package GUI;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.*;
import DB.CorsiDAO;
import DB.IscrittoalcorsoDAO;
import NextFit.Corsi;
import NextFit.Corso;
import NextFit.Palestra;

public class ElimCorsoGui extends JFrame {

	private ArrayList<JButton> courseButtons;
	private JPanel panel, backPanel;
	private JButton back;
	private JLabel coursesLabel;
	private Palestra palestra;
	private Corsi corsi;
	private ProprietarioGui parent;

	public ElimCorsoGui(Corsi corsi, Palestra palestra, ProprietarioGui parent) {
		this.palestra = palestra;
		this.corsi = corsi;
		this.parent = parent;

		setTitle("Interfaccia Corsi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		GridLayout gridLayout = new GridLayout(0, 2);
		gridLayout.setHgap(10);
		gridLayout.setVgap(10);
		panel = new JPanel(gridLayout);
		panel.setBackground(new Color(28, 28, 28));
		panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		courseButtons = new ArrayList<>();

		coursesLabel = new JLabel("<html><font color='orange'>COR</font><font color='white'>SI</font></html>");
		coursesLabel.setFont(new Font("Rockwell", Font.BOLD, 30));
		panel.add(coursesLabel);

		backPanel = new JPanel(new FlowLayout());
		backPanel.setBackground(new Color(28, 28, 28));

		back = new JButton("Indietro");
		back.setBackground(new Color(250, 140, 0));
		back.setFont(new Font("Arial", Font.BOLD, 13));
		back.setBorder(BorderFactory.createLineBorder(new Color(250, 140, 0), 6, false));
		back.setMaximumSize(new Dimension(100, 30));

		addBackButton();

		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				parent.setVisible(true);
				dispose();
			}
		});

		for (int i = 0; i < corsi.getC(); i++) {
			Corso corso = corsi.getCorso(i);
			if (corso != null) {
				JButton button = new JButton(corso.getNome());
				button.setBackground(new Color(40, 40, 40));
				button.setPreferredSize(new Dimension(200, 100));
				button.setFont(new Font("Rockwell", Font.BOLD, 20));
				button.setForeground(Color.white);
				courseButtons.add(button);
				panel.add(button);
				button.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						deleteCorso(courseButtons.indexOf(button));
						updatePage();
					}
				});
			}
		}

		JScrollPane scrollPane = new JScrollPane(panel);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		getContentPane().add(scrollPane, BorderLayout.CENTER);

		panel.add(new JLabel());
		backPanel.add(back);
		panel.add(backPanel);

		setSize(480, 640);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private void addBackButton() {
		panel.add(new JLabel());
		backPanel.add(back);
		panel.add(backPanel);
	}

	private void deleteCorso(int index) {
		IscrittoalcorsoDAO idao = new IscrittoalcorsoDAO();
		CorsiDAO cdao = new CorsiDAO();

		Corso corso = corsi.getCorso(index);

		if (corso != null && corso.getCorsista() != null) {
			if (corsi.iscorsoPresente(corso)) {
				idao.deleteIscrizione1(corso.getNome(), corso.getCorsista().getNome(), corso.getCorsista().getCognome(),
						corso.getCorsista().getMail());
			}
			cdao.deleteCorso(corso.getNome(), corso.getCorsista().getNome(), corso.getCorsista().getCognome(),
					corso.getCorsista().getMail(), corsi, palestra);
		}
	}

	private void updatePage() {
		panel.removeAll();
		panel.add(coursesLabel);
		panel.add(new JLabel());
		for (int i = 0; i < corsi.getC(); i++) {
			Corso corso = corsi.getCorso(i);
			if (corso != null) {
				final int index = i;
				JButton button = new JButton(corso.getNome());
				button.setBackground(new Color(40, 40, 40));
				button.setPreferredSize(new Dimension(200, 100));
				button.setFont(new Font("Rockwell", Font.BOLD, 20));
				button.setForeground(Color.white);
				courseButtons.add(button);
				panel.add(button);
				button.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						deleteCorso(index);
						updatePage();
					}
				});
			}
		}
		addBackButton();
		panel.revalidate();
		panel.repaint();
	}

}
