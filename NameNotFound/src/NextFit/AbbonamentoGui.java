package NextFit;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class AbbonamentoGui extends JFrame 
{
	private JButton mensile,semestrale,annuale;
	public AbbonamentoGui()
{
	setTitle("scelta abbonamenti");
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	JPanel panel = new JPanel(new GridLayout(1, 1));
	JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
	 
	 mensile = new JButton("mensile");
	 semestrale = new JButton("semestrale");
	 annuale = new JButton("annuale");
	 
	
	 buttonPanel.add(mensile);
     buttonPanel.add(semestrale);
     buttonPanel.add(annuale);
    
     buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); 

     panel.add(buttonPanel);
     
     getContentPane().add(panel, BorderLayout.CENTER);
    
    setSize(480,640);
    setLocationRelativeTo(null); // Posizioniamo la finestra al centro dello schermo
    setVisible(true);
}
	public static void main(String[] args) {
		 SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
	                new AbbonamentoGui();
	            }
	        });

	}
}
