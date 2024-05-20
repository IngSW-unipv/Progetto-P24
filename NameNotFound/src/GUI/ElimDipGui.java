package GUI;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.*;
import DB.CorsiDAO;
import DB.DipendenteDAO;
import DB.IscrittoalcorsoDAO;
import DB.RichiesteDAO;
import NextFit.Corsi;
import NextFit.Corso;
import NextFit.Palestra;
import NextFit.RichiestaAlPT;
import NextFit.Richieste;
import NextFit.Dipendente;

public class ElimDipGui extends JFrame {

	private ArrayList<JButton> dipButtons;
	private JPanel panel, backpanel;
	private JButton back;
	private JLabel DIPENDENTI;
	private Palestra p;
	private Corsi co;
	private Richieste r;
	private ProprietarioGui parent;

	public ElimDipGui(Palestra p, Corsi co, ProprietarioGui parent, Richieste r) {
		this.p = p;
		this.co = co;
		this.parent = parent;
		this.r = r;

		setTitle("Interfaccia dipendenti");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		GridLayout gridLayout = new GridLayout(0, 2);
		gridLayout.setHgap(10);
		gridLayout.setVgap(10);
		panel = new JPanel(gridLayout);
		panel.setBackground(new Color(28, 28, 28));
		panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		dipButtons = new ArrayList<>();

		DIPENDENTI = new JLabel("<html><font color='orange'>DIPEN</font><font color='white'>DENTI</font></html>");
		DIPENDENTI.setFont(new Font("Rockwell", Font.BOLD, 30));
		panel.add(DIPENDENTI);

		backpanel = new JPanel(new FlowLayout());
		backpanel.setBackground(new Color(28, 28, 28));

		back = new JButton("indietro");
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

		for (int i = 0; i < p.getD(); i++) {
			JButton button = new JButton(p.getDIP2(i).getNome() + " " + p.getDIP2(i).getCognome());
			button.setBackground(new Color(40, 40, 40));
			button.setPreferredSize(new Dimension(200, 100));
			button.setFont(new Font("Rockwell", Font.BOLD, 20));
			button.setForeground(Color.white);
			dipButtons.add(button);
			panel.add(button);
			button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					int index = dipButtons.indexOf(button);
					deleteDipendente(index);
					updatePage();
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

	private void deleteDipendente(int index) {
		DipendenteDAO dipdao = new DipendenteDAO();
		IscrittoalcorsoDAO idao = new IscrittoalcorsoDAO();
		CorsiDAO cdao = new CorsiDAO();
		RichiesteDAO rdao = new RichiesteDAO();

		Dipendente dipendente = p.getDIP2(index);

		if (dipendente.getTipo().equals("corsista")) {
			for (int j = 0; j < co.getC(); j++) {
				Corso corso = co.getCorso(j);
				if (dipendente.equals(corso.getCorsista())) {
					if (co.iscorsoPresente(corso)) {
						idao.deleteIscrizione1(corso.getNome(), corso.getCorsista().getNome(),
								corso.getCorsista().getCognome(), corso.getCorsista().getMail());
					}
					cdao.deleteCorso(corso.getNome(), dipendente.getNome(), dipendente.getCognome(),
							dipendente.getMail(), co, p);
				}
			}
		} else if (dipendente.getTipo().equals("personaltrainer")) {
			for (int j = 0; j < r.getRichieste().size(); j++) {
				RichiestaAlPT richiesta = r.getRichiesteI(j);
				if(dipendente.equals(richiesta.getDipendente())) {
					if(r.ptRichieste(dipendente)) {
						rdao.deleteRichiesta(richiesta);
					}
				}
			}
		}

		dipdao.eliminaDip(dipendente.getNome(), dipendente.getCognome(), dipendente.getMail(), p);
	}

	private void updatePage() {
		panel.removeAll();
		panel.add(DIPENDENTI);
		panel.add(new JLabel());
		for (int i = 0; i <= p.getD() - 1; i++) {
			final int n = i;
			JButton button = new JButton(p.getDIP2(i).getNome() + " " + p.getDIP2(i).getCognome());
			button.setBackground(new Color(40, 40, 40));
			button.setPreferredSize(new Dimension(200, 100));
			button.setFont(new Font("Rockwell", Font.BOLD, 20));
			button.setForeground(Color.white);
			dipButtons.add(button);
			panel.add(button);
			button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					deleteDipendente(n);
					updatePage();
				}
			});
		}
		addBackButton();
		panel.revalidate();
		panel.repaint();
	}
}
