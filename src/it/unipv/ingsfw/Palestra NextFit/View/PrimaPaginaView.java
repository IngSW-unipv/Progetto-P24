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
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import Model.Corsi;
import Model.Palestra;
import Model.Proprietario;
import Model.Richieste;

public class PrimaPaginaView extends JFrame {
	private JButton LOGIN, REG;
	private JLabel NEXTFIT;

	public PrimaPaginaView(Palestra palestra, Proprietario proprietario, Corsi co, Richieste r) {
		setTitle("Pagina principale");

		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		Color CBACK = new Color(28, 28, 28);

		panel.setBackground(CBACK);

		NEXTFIT = new JLabel("<html><font color='orange'>NEXT</font><font color='white'>FIT</font></html>");
		NEXTFIT.setFont(new Font("Rockwell", Font.BOLD, 40));

		panel.add(NEXTFIT);

		panel.add(Box.createRigidArea(new Dimension(0, 200)));

		LOGIN = new JButton("LOGIN");

		Color CBUT = new Color(40, 40, 40);

		LOGIN.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
		LOGIN.setBackground(CBUT);
		LOGIN.setFont(new Font("Rockwell", Font.BOLD, 20));
		LOGIN.setForeground(Color.white);

		LOGIN.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						new LoginView(palestra, proprietario, co, r);

					}
				});
			}
		});
		panel.add(LOGIN);
		panel.add(Box.createRigidArea(new Dimension(0, 20)));

		getContentPane().add(panel, BorderLayout.CENTER);
		REG = new JButton("REGISTRAZIONE");
		REG.setFont(new Font("Rockwell", Font.BOLD, 20));
		REG.setForeground(Color.white);
		REG.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
		REG.setBackground(CBUT);
		REG.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						new RegView(palestra, proprietario, co, r);

					}
				});

			}
		});

		panel.add(REG);

		panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		getContentPane().add(panel, BorderLayout.CENTER);

		setSize(480, 640);
		setLocationRelativeTo(null); // Posizioniamo la finestra al centro dello schermo
		setVisible(true);
	}
}