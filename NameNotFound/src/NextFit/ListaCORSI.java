package NextFit;

import java.awt.BorderLayout;
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
	private JPanel buttonPanel;

	public ListaCORSI(Corsi co,Cliente c) {
		setTitle("Interfaccia Corsi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(0, 2)); // 4 bottoni per riga, può cambiare a seconda delle esigenze
		coursesButtons = new ArrayList<>();

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
		JScrollPane scrollPane = new JScrollPane(buttonPanel);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		getContentPane().add(scrollPane, BorderLayout.CENTER);

		setSize(480, 640);
		setLocationRelativeTo(null);
		setVisible(true);
	}

}