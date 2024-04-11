package NextFit;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

public class ListaCORSI extends JFrame {

	private ArrayList<JButton> coursesButtons;
	private JPanel buttonPanel,buttonpanel2;
	private JButton back;

	public ListaCORSI(Corsi co,Cliente c,LatoClienteGui parent) {
		setTitle("Interfaccia Corsi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		buttonPanel = new JPanel();
		buttonpanel2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		buttonPanel.setLayout(new GridLayout(0, 2)); // 4 bottoni per riga, può cambiare a seconda delle esigenze
		coursesButtons = new ArrayList<>();
		back = new JButton("indietro");
		
		// mettere nel for num max PT
		for (int i = 0; i <= co.getC()  - 1; i++) {
			JButton button = new JButton(
					co.getCorso(i).getNome());
			int n=i;
			coursesButtons.add(button);
			buttonPanel.add(button);
			button.addActionListener(new ActionListener() {
				 boolean isIscritto = false;
				@Override
				public void actionPerformed(ActionEvent e) {
					if (!isIscritto) {
                        co.getCorso(n).aggPalCorso();
                        System.out.println(co.getCorso(n).getNp());
                        JButton button = (JButton) e.getSource();
                        button.setText(co.getCorso(n).getNome() + " - iscritto");
                        isIscritto=true;
                    } else
                    {
                    	co.getCorso(n).eliPdalCorso();
                        System.out.println(co.getCorso(n).getNp());
                        JButton button = (JButton) e.getSource();
                        button.setText(co.getCorso(n).getNome());
                        isIscritto=false;
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
		
		JScrollPane scrollPane = new JScrollPane(buttonPanel);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		getContentPane().add(scrollPane, BorderLayout.CENTER);

		buttonpanel2.add(back);
		buttonPanel.add(buttonpanel2);
		
		setSize(480, 640);
		setLocationRelativeTo(null);
		setVisible(true);
	}

}