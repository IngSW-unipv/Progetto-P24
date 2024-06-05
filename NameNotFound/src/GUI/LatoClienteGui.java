package GUI;

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
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import NextFit.ClienteAbbonato;
import NextFit.Corsi;
import NextFit.Palestra;
import NextFit.Richieste;

public class LatoClienteGui extends JFrame {
	private JButton PT, CORSI, SCHEDA, SERVIZI;
	private ListaCORSI listaCORSI;
	private ListaPT listaPT;
	private SerAgg serAgg;
	private JLabel NEXTFIT, SCAD;
	private boolean controlpt, controlcorsi, controlsa;

	public LatoClienteGui(Corsi co, ClienteAbbonato clienteAbbonato, Palestra palestra, Richieste richieste) {
		setTitle("Pagina principale");

		/*
		 * listaCORSI = new ListaCORSI(co, clienteAbbonato, LatoClienteGui.this); //
		 * creo adesso la gui figlia listaCORSI.setVisible(false);
		 * 
		 * listaPT = new ListaPT(palestra, clienteAbbonato, LatoClienteGui.this,
		 * richieste); // creo adesso la gui figlia listaPT.setVisible(false);
		 * setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 * 
		 * serAgg = new SerAgg(clienteAbbonato, palestra, LatoClienteGui.this);
		 * serAgg.setVisible(false);
		 */

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		Color CBACK = new Color(28, 28, 28);

		panel.setBackground(CBACK);

		NEXTFIT = new JLabel("<html><font color='orange'>NEXT</font><font color='white'>FIT</font></html>");
		NEXTFIT.setFont(new Font("Rockwell", Font.BOLD, 40));

		SCAD = new JLabel("<html><font color='orange'>NEXT</font><font color='white'>FIT</font></html>");

		JPanel labelpanel = new JPanel();

		labelpanel.setBackground(CBACK);
		labelpanel.add(NEXTFIT, BorderLayout.WEST);
		labelpanel.add(SCAD, BorderLayout.EAST);

		panel.add(NEXTFIT);

		panel.add(Box.createRigidArea(new Dimension(0, 20)));

		PT = new JButton("PERSONAL TRAINER");

		Color CBUT = new Color(40, 40, 40);

		PT.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
		PT.setBackground(CBUT);
		PT.setFont(new Font("Rockwell", Font.BOLD, 20));
		PT.setForeground(Color.white);

		controlpt = false;
		PT.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (controlpt == false) {
					listaPT = new ListaPT(palestra, clienteAbbonato, LatoClienteGui.this, richieste); // creo adesso la
																										// gui figlia
					dispose();
					controlpt = true;
				} else {
					listaPT.setVisible(true); // Riapre la finestra già esistente

					dispose();
				}
			}
		});
		panel.add(PT);
		panel.add(Box.createRigidArea(new Dimension(0, 20)));

		getContentPane().add(panel, BorderLayout.CENTER);
		CORSI = new JButton("CORSI");
		CORSI.setFont(new Font("Rockwell", Font.BOLD, 20));
		CORSI.setForeground(Color.white);
		CORSI.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
		CORSI.setBackground(CBUT);
		controlcorsi = false;
		CORSI.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (controlcorsi == false) {
					listaCORSI = new ListaCORSI(co, clienteAbbonato, LatoClienteGui.this); // creo adesso la gui figlia
					dispose();
					controlcorsi = true;
				} else {
					listaCORSI.setVisible(true); // Riapre la finestra già esistente

					dispose();
				}

			}
		});

		panel.add(CORSI);
		panel.add(Box.createRigidArea(new Dimension(0, 20)));
		SCHEDA = new JButton("SCHEDA");
		SCHEDA.setFont(new Font("Rockwell", Font.BOLD, 20));
		SCHEDA.setForeground(Color.white);
		SCHEDA.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
		SCHEDA.setBackground(CBUT);
		SCHEDA.addActionListener(new ActionListener() {

			@Override
		public void actionPerformed(ActionEvent e) {
			
		new VisuScheda(richieste,clienteAbbonato);
			}
	});
		panel.add(SCHEDA);
		panel.add(Box.createRigidArea(new Dimension(0, 20)));
		SERVIZI = new JButton("SERVIZI AGGIUNTIVI");
		SERVIZI.setFont(new Font("Rockwell", Font.BOLD, 20));
		SERVIZI.setForeground(Color.white);
		SERVIZI.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
		SERVIZI.setBackground(CBUT);

		controlsa = false;
		SERVIZI.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (controlsa == false) {
					serAgg = new SerAgg(clienteAbbonato, palestra, LatoClienteGui.this);
					dispose();
					controlsa = true;
				} else {
					serAgg.setVisible(true);

					dispose();
				}
			}
		});

		panel.add(SERVIZI);

		panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		getContentPane().add(panel, BorderLayout.CENTER);

		setSize(480, 640);
		setLocationRelativeTo(null); // Posizioniamo la finestra al centro dello schermo
		setVisible(true);
	}

	/*
	 * public static void main(String[] args) { SwingUtilities.invokeLater(new
	 * Runnable() { public void run() { new LatoClienteGui(); } }); }
	 */
}