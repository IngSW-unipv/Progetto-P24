package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import NextFit.ClienteAbbonato;
import NextFit.Esercizio;
import NextFit.Palestra;
import NextFit.RichiestaAlPT;
import NextFit.Richieste;
import NextFit.Scheda;

public class VisuScheda extends JFrame {
	private JLabel SCHEDA,PT,ES1,ES2,ES3,ES4,ES5;
	
	public VisuScheda(Richieste ri,ClienteAbbonato ca)
	{
		setTitle("schede");
		
		int cod=ri.getCodiceSchedaCliente(ca);
		Scheda scheda=ri.getScheda(cod);
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		Color CBACK = new Color(28, 28, 28);

		panel.setBackground(CBACK);

		SCHEDA = new JLabel("<html><font color='orange'>SCH</font><font color='white'>EDA</font></html>");
		SCHEDA.setFont(new Font("Rockwell", Font.BOLD, 40));
		PT = new JLabel("<html><font color='orange'>SCH</font><font color='white'>EDA</font></html>");
		PT.setFont(new Font("Rockwell", Font.BOLD, 40));
		

		panel.add(SCHEDA);
		
		panel.add(Box.createRigidArea(new Dimension(0, 20)));
		Color CBUT = new Color(40, 40, 40);
		
		ES1 = new JLabel(scheda.getNomeEsercizio(0));
		ES1.setForeground(Color.white);
		ES2 = new JLabel(scheda.getNomeEsercizio(1));
		ES2.setForeground(Color.white);
		ES3 = new JLabel(scheda.getNomeEsercizio(2));
		ES3.setForeground(Color.white);
		ES4 = new JLabel(scheda.getNomeEsercizio(3));
		ES4.setForeground(Color.white);
		ES5 = new JLabel(scheda.getNomeEsercizio(4));
		ES5.setForeground(Color.white);
		
		panel.add(ES1);
		panel.add(Box.createRigidArea(new Dimension(0, 20)));
		panel.add(ES2);
		panel.add(Box.createRigidArea(new Dimension(0, 20)));
		panel.add(ES3);
		panel.add(Box.createRigidArea(new Dimension(0, 20)));
		panel.add(ES4);
		panel.add(Box.createRigidArea(new Dimension(0, 20)));
		panel.add(ES5);
		
		
		
		
		
		
		
		
		panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		getContentPane().add(panel, BorderLayout.CENTER);

		setSize(480, 640); // Impostiamo le dimensioni della finestra
		setLocationRelativeTo(null); // Posizioniamo la finestra al centro dello schermo
		setVisible(true);
	}
		
	}


