package GUI;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import NextFit.Corsi;
import NextFit.Palestra;
import NextFit.Proprietario;
import NextFit.Richieste;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame {
	private JTextField mailField;
	private JButton loginButton;
	private JPasswordField passwordField;
	private JLabel pw, mail, NEXTFIT, errorlabel;

	public Login(Palestra palestra, Proprietario proprietario, Corsi co, Richieste r) {

		setTitle("login palestra");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		Color CBACK = new Color(28, 28, 28);

		panel.setBackground(CBACK);

		NEXTFIT = new JLabel("<html><font color='orange'>LOG</font><font color='white'>IN</font></html>");
		NEXTFIT.setFont(new Font("Rockwell", Font.BOLD, 40));

		panel.add(NEXTFIT);

		panel.add(Box.createRigidArea(new Dimension(0, 20)));

		mailField = new JTextField();
		mailField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
		mailField.setForeground(Color.white);

		passwordField = new JPasswordField();
		passwordField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
		passwordField.setForeground(Color.white);

		Color CBUT = new Color(40, 40, 40);

		Border roundedBorder = BorderFactory.createCompoundBorder(new LineBorder(Color.black, 2, true),
				new EmptyBorder(0, 10, 0, 10) // Margine interno
		);

		mailField.setBorder(roundedBorder);
		mailField.setBackground(CBUT);

		passwordField.setBorder(roundedBorder);
		passwordField.setBackground(CBUT);

		mail = new JLabel("Email:");
		mail.setForeground(Color.white);
		pw = new JLabel("Password:");
		pw.setForeground(Color.white);

		panel.add(Box.createRigidArea(new Dimension(0, 20)));
		panel.add(mail);
		panel.add(Box.createRigidArea(new Dimension(0, 20)));
		panel.add(mailField);
		panel.add(Box.createRigidArea(new Dimension(0, 20)));

		panel.add(Box.createRigidArea(new Dimension(0, 20)));
		panel.add(pw);
		panel.add(Box.createRigidArea(new Dimension(0, 20)));
		panel.add(passwordField);
		panel.add(Box.createRigidArea(new Dimension(0, 20)));

		panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		Color or = new Color(250, 140, 0);

		errorlabel = new JLabel("*Errore: mail o password incorretti");
		errorlabel.setForeground(Color.RED);

		loginButton = new JButton("Entra");

		loginButton.setBackground(or);
		loginButton.setFont(new Font("Arial", Font.BOLD, 13));
		loginButton.setMaximumSize(new Dimension(100, 30));
		loginButton.setBorder(BorderFactory.createLineBorder(or, 6, false));

		loginButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String mail = mailField.getText();
				String password = new String(passwordField.getPassword());

				JPanel buttonPanel = new JPanel();
				buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
				buttonPanel.setBackground(CBACK);
				buttonPanel.add(loginButton);
				buttonPanel.add(errorlabel);

				if (mail.toLowerCase().equals(proprietario.getMail()) && password.equals(proprietario.getPassword())) {
					new ProprietarioGui(proprietario, palestra, co, r);
					buttonPanel.remove(errorlabel);
					panel.add(buttonPanel);
					panel.revalidate();
					panel.repaint();
				}

				if (palestra.esisteCli(mail.toLowerCase(), password) == true) {
					new LatoClienteGui(co, palestra.accessoCli(mail.toLowerCase(), password), palestra, r);
					buttonPanel.remove(errorlabel);
					panel.add(buttonPanel);
					panel.revalidate();
					panel.repaint();
				}

				if (palestra.esisteDip(mail.toLowerCase(), password) == true) {
					if (palestra.accessoDip(mail.toLowerCase(), password).getTipo().equals("personaltrainer")) {
						new LatoPTGui(palestra.accessoDip(mail.toLowerCase(), password), palestra, r);
						buttonPanel.remove(errorlabel);
						panel.add(buttonPanel);
						panel.revalidate();
						panel.repaint();
					} else if (palestra.accessoDip(mail.toLowerCase(), password).getTipo().equals("corsista")) {
						new LatoCorsistaGui(palestra.accessoDip(mail.toLowerCase(), password), palestra, co);
						buttonPanel.remove(errorlabel);
						panel.add(buttonPanel);
						panel.revalidate();
						panel.repaint();
					}
				} else {
					panel.add(buttonPanel);

					// Aggiorna il pannello
					panel.revalidate();
					panel.repaint();
				}

			}

		});
		panel.add(loginButton);

		getContentPane().add(panel, BorderLayout.CENTER);

		setSize(480, 640); // Impostiamo le dimensioni della finestra
		setLocationRelativeTo(null); // Posizioniamo la finestra al centro dello schermo
		setVisible(true);
	}

}
