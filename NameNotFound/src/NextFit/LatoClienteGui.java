package NextFit;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class LatoClienteGui extends JFrame {
	private JButton PT;

	public LatoClienteGui() {
		setTitle("Pagina principale");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel(new GridLayout(1, 1));
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

		PT = new JButton("Personal Trainer");

		buttonPanel.add(PT);

		panel.add(buttonPanel);

		getContentPane().add(panel, BorderLayout.CENTER);

		setSize(480, 640);
		setLocationRelativeTo(null); // Posizioniamo la finestra al centro dello schermo
		setVisible(true);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new LatoClienteGui();
			}
		});
	}
}