package View;

import javax.swing.JFrame;
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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import Controller.ElimClienteController;
import Controller.NavigationController;
import Model.ClienteAbbonato;
import Model.Corsi;
import Model.Palestra;
import Model.Proprietario;
import Model.Richieste;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;

public class SerAggView extends JFrame {
	private JButton SCAD, RIN, DIS, back;
	private JLabel NEXTFIT;
	private RinAbbView rinabb;
	private PrimaPaginaView pp;

	public SerAggView(ClienteAbbonato clienteAbbonato, Palestra palestra, LatoClienteView parent,
			Proprietario proprietario, Corsi corsi, Richieste richieste) {
		setTitle("Pagina principale");

		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		rinabb = new RinAbbView(clienteAbbonato, palestra, SerAggView.this);
		rinabb.setVisible(false);
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Color CBACK = new Color(28, 28, 28);

		panel.setBackground(CBACK);

		NEXTFIT = new JLabel("<html><font color='orange'>SERVIZI </font><font color='white'>AGGIUNTIVI</font></html>");
		NEXTFIT.setFont(new Font("Rockwell", Font.BOLD, 40));

		panel.add(NEXTFIT);

		panel.add(Box.createRigidArea(new Dimension(0, 20)));

		SCAD = new JButton("SCADENZA ABBONAMENTO");

		Color CBUT = new Color(40, 40, 40);

		SCAD.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
		SCAD.setBackground(CBUT);
		SCAD.setFont(new Font("Rockwell", Font.BOLD, 20));
		SCAD.setForeground(Color.white);

		SCAD.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				LocalDate scad = clienteAbbonato.getDataScadenza();
				String scads = scad.toString();
				SCAD.setText(scads);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				SCAD.setText("SCADENZA ABBONAMENTO");
			}
		});
		panel.add(SCAD);

		RIN = new JButton("RINNOVO ABBONAMENTO");
		RIN.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
		RIN.setBackground(CBUT);
		RIN.setFont(new Font("Rockwell", Font.BOLD, 20));
		RIN.setForeground(Color.white);

		RIN.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				rinabb.setVisible(true); // Riapre la finestra già esistente
				dispose();

			}
		});
		panel.add(Box.createRigidArea(new Dimension(0, 20)));
		panel.add(RIN);

		DIS = new JButton("DISISCRIVI ABBONAMENTO");
		DIS.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
		DIS.setBackground(CBUT);
		DIS.setFont(new Font("Rockwell", Font.BOLD, 20));
		DIS.setForeground(Color.white);
		pp = new PrimaPaginaView(palestra, proprietario, corsi, richieste);
		pp.setVisible(false);
		ElimClienteController ec = new ElimClienteController();

		DIS.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				int result = JOptionPane.showConfirmDialog(null,
						"Sei sicuro di voler eliminare l'account definitivamente? ", "Conferma",
						JOptionPane.YES_NO_OPTION);

				if (result == JOptionPane.YES_OPTION) {
					ec.eliminaCliente(richieste, corsi, clienteAbbonato, palestra);
					pp.setVisible(true); // Riapre la finestra già esistente
					dispose();
				}

			}
		});
		panel.add(Box.createRigidArea(new Dimension(0, 20)));
		panel.add(DIS);

		// torna indietro con navigation controller
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

		panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		getContentPane().add(panel, BorderLayout.CENTER);

		setSize(480, 640);
		setLocationRelativeTo(null); // Posizioniamo la finestra al centro dello schermo
		setVisible(true);

	}
}