package GUI;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import DB.RichiesteDAO;
import DB.SchedaDAO;
import NextFit.Cliente;
import NextFit.Corsi;
import NextFit.Dipendente;
import NextFit.Esercizio;
import NextFit.Palestra;
import NextFit.Proprietario;
import NextFit.RichiestaAlPT;
import NextFit.Richieste;
import NextFit.Scheda;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreaScheda extends JFrame {

	private JLabel SCHEDE,NOME,es1,es2,es3,es4,es5;
	private JTextField es01,es02,es03,es04,es05;
	private JButton invia;
	
public CreaScheda(Palestra palestra,RichiestaAlPT r,Richieste ri)
{
	setTitle("schede");
	
	JPanel panel = new JPanel();
	panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

	Color CBACK = new Color(28, 28, 28);

	panel.setBackground(CBACK);

	SCHEDE = new JLabel("<html><font color='orange'>SCH</font><font color='white'>EDA</font></html>");
	SCHEDE.setFont(new Font("Rockwell", Font.BOLD, 40));
	NOME = new JLabel("<html><font color='orange'>"+r.getCliente().getCliente().getNome()+"</font><font color='white'>"+" "+r.getCliente().getCliente().getCognome()+"</font></html>");
	NOME.setFont(new Font("Rockwell", Font.BOLD, 20));

	panel.add(SCHEDE);
	panel.add(NOME);
	panel.add(Box.createRigidArea(new Dimension(0, 20)));
	Color CBUT = new Color(40, 40, 40);
	
	es1 = new JLabel("esercizio 1");
	es1.setForeground(Color.white);
	es2 = new JLabel("esercizio 2");
	es2.setForeground(Color.white);
	es3 = new JLabel("esercizio 3");
	es3.setForeground(Color.white);
	es4 = new JLabel("esercizio 4");
	es4.setForeground(Color.white);
	es5 = new JLabel("esercizio 5");
	es5.setForeground(Color.white);
	
	Border roundedBorder = BorderFactory.createCompoundBorder(new LineBorder(Color.black, 2, true),
			new EmptyBorder(0, 10, 0, 10) // Margine interno
	);
	
	es01 = new JTextField();
	es01.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
	es01.setForeground(Color.white);
	es01.setBorder(roundedBorder);
	es01.setBackground(CBUT);
	es02 = new JTextField();
	es02.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
	es02.setForeground(Color.white);
	es02.setBorder(roundedBorder);
	es02.setBackground(CBUT);
	es03 = new JTextField();
	es03.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
	es03.setForeground(Color.white);
	es03.setBorder(roundedBorder);
	es03.setBackground(CBUT);
	es04 = new JTextField();
	es04.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
	es04.setForeground(Color.white);
	es04.setBorder(roundedBorder);
	es04.setBackground(CBUT);
	es05 = new JTextField();
	es05.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
	es05.setForeground(Color.white);
	es05.setBorder(roundedBorder);
	es05.setBackground(CBUT);
	
	panel.add(es1);
	panel.add(Box.createRigidArea(new Dimension(0, 20)));
	panel.add(es01);
	panel.add(Box.createRigidArea(new Dimension(0, 20)));
	panel.add(es2);
	panel.add(Box.createRigidArea(new Dimension(0, 20)));
	panel.add(es02);
	panel.add(Box.createRigidArea(new Dimension(0, 20)));
	panel.add(es3);
	panel.add(Box.createRigidArea(new Dimension(0, 20)));
	panel.add(es03);
	panel.add(Box.createRigidArea(new Dimension(0, 20)));
	panel.add(es4);
	panel.add(Box.createRigidArea(new Dimension(0, 20)));
	panel.add(es04);
	panel.add(Box.createRigidArea(new Dimension(0, 20)));
	panel.add(es5);
	panel.add(Box.createRigidArea(new Dimension(0, 20)));
	panel.add(es05);
	panel.add(Box.createRigidArea(new Dimension(0, 20)));
	
	invia = new JButton("Invia");
	invia.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
	invia.setBackground(CBUT);
	invia.setFont(new Font("Rockwell", Font.BOLD, 20));
	invia.setForeground(Color.white);

	invia.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			Esercizio e1=new Esercizio(es01.getText());
			Esercizio e2=new Esercizio(es02.getText());
			Esercizio e3=new Esercizio(es03.getText());
			Esercizio e4=new Esercizio(es04.getText());
			Esercizio e5=new Esercizio(es05.getText());
			Scheda s=new Scheda(ri.getSchede().size()+1);
			s.aggiungiEsercizio(e1);
			s.aggiungiEsercizio(e2);
			s.aggiungiEsercizio(e3);
			s.aggiungiEsercizio(e4);
			s.aggiungiEsercizio(e5);
			ri.aggScheda(s);
			SchedaDAO schedao=new SchedaDAO();
			RichiesteDAO ridao=new RichiesteDAO();
			
			schedao.insert(s);
			ridao.updateSchedaId(r,s.getCod_Scheda());
		}
	});
	panel.add(invia);
	
	
	panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
	getContentPane().add(panel, BorderLayout.CENTER);

	setSize(480, 640); // Impostiamo le dimensioni della finestra
	setLocationRelativeTo(null); // Posizioniamo la finestra al centro dello schermo
	setVisible(true);
}
	
}
