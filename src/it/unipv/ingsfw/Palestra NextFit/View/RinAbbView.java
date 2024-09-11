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

import Controller.NavigationController;
import Controller.RinAbbController;
import DB.ClienteAbboDAO;
import NextFit.ClienteAbbonato;
import NextFit.Palestra;

public class RinAbbView extends JFrame {
	private JButton mensile, semestrale, annuale, back;
	private JLabel AB;

	public RinAbbView(ClienteAbbonato clienteAbbonato, Palestra palestra, SerAggView parent) {
		setTitle("scelta abbonamenti");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
		ClienteAbboDAO dao = new ClienteAbboDAO();
		RinAbbController rc = new RinAbbController(clienteAbbonato);

		mensile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				rc.aggMensile(dao);

			}
		});

		semestrale.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				rc.aggSemestrale(dao);

			}
		});

		annuale.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				rc.aggAnnuale(dao);
				
			}
		});

		panel.add(mensile);
		panel.add(Box.createRigidArea(new Dimension(0, 20)));
		panel.add(semestrale);
		panel.add(Box.createRigidArea(new Dimension(0, 20)));
		panel.add(annuale);
		panel.add(Box.createRigidArea(new Dimension(0, 20)));

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

			}

		});
		panel.add(Box.createRigidArea(new Dimension(0, 20)));
		panel.add(back);
		getContentPane().add(panel, BorderLayout.CENTER);

		panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		setSize(480, 640);
		setLocationRelativeTo(null); // Posizioniamo la finestra al centro dello schermo
		setVisible(true);
	}

}
