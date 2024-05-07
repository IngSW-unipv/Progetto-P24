package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import DB.CorsiDAO;
import DB.DipendenteDAO;
import DB.IscrittoalcorsoDAO;
import NextFit.Corsi;
import NextFit.Palestra;

public class ElimDipGui extends JFrame {

	private ArrayList<JButton> dipButtons;
	private JPanel panel, backpanel;
	private JButton back;
	private JLabel DIPENDENTI;

	public ElimDipGui(Palestra p,Corsi co,ProprietarioGui parent) {
		setTitle("Interfaccia dipendenti");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	
		GridLayout gridLayout = new GridLayout(0, 2); // Imposta un layout a due colonne
		gridLayout.setHgap(10); // Imposta lo spazio orizzontale tra i bottoni a 10 pixel
		gridLayout.setVgap(10);
		panel = new JPanel(gridLayout);
		Color CBACK = new Color(28, 28, 28);
		panel.setBackground(CBACK);

		dipButtons = new ArrayList<>();
		panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		DIPENDENTI = new JLabel("<html><font color='orange'>DIPEN</font><font color='white'>DENTI</font></html>");
		DIPENDENTI.setFont(new Font("Rockwell", Font.BOLD, 30));

		panel.add(DIPENDENTI);

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

		for (int i = 0; i <= p.getD() - 1; i++) {
			JButton button = new JButton(p.getDIP2(i).getNome()+" "+p.getDIP2(i).getCognome());
			int n = i;
			button.setBackground(CBUT);
			button.setPreferredSize(new Dimension(200, 100));
			button.setFont(new Font("Rockwell", Font.BOLD, 20));
			button.setForeground(Color.white);
			dipButtons.add(button);
			panel.add(button);		
			button.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					DipendenteDAO dipdao=new DipendenteDAO();
					IscrittoalcorsoDAO idao=new IscrittoalcorsoDAO();
					CorsiDAO cdao=new CorsiDAO();
					
					if(p.getDIP2(n).getTipo().equals("corsista"))
					{
						for(int j=0;j<co.getC();j++)
						{
							if(p.getDIP2(n).equals(co.getCorso(j).getCorsista()))
							{
								if(co.iscorsoPresente(co.getCorso(j)))
								{
									idao.deleteIscrizione1(co.getCorso(j).getNome(), p.getDIP2(n).getNome(), p.getDIP2(n).getCognome());
								}
								cdao.deleteCorso(co.getCorso(j).getNome(), p.getDIP2(n).getNome(), p.getDIP2(n).getCognome(), co, p);
							}
						}
						dipdao.eliminaDip(p.getDIP2(n).getNome(), p.getDIP2(n).getCognome(), p.getDIP2(n).getMail(), p);
						panel.remove(button);
					}else
					{
						dipdao.eliminaDip(p.getDIP2(n).getNome(), p.getDIP2(n).getCognome(), p.getDIP2(n).getMail(), p);
						panel.remove(button);
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
