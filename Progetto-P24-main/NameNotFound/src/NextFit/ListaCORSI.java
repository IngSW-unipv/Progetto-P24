package NextFit;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

public class ListaCORSI extends JFrame {

	private ArrayList<JButton> coursesButtons;
	private JPanel buttonPanel;

	public ListaCORSI(Corsi c) {
		setTitle("Interfaccia Corsi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(0, 2)); // 4 bottoni per riga, può cambiare a seconda delle esigenze
		coursesButtons = new ArrayList<>();

		// mettere nel for num max PT
		for (int i = 0; i <= c.getC()  - 1; i++) {
			JButton button = new JButton(
					c.getCorso(i).getNome());
			coursesButtons.add(button);
			buttonPanel.add(button);
		}
		JScrollPane scrollPane = new JScrollPane(buttonPanel);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		getContentPane().add(scrollPane, BorderLayout.CENTER);

		setSize(480, 640);
		setLocationRelativeTo(null);
		setVisible(true);
	}

}