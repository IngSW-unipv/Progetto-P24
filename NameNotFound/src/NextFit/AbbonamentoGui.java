package NextFit;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class AbbonamentoGui extends JFrame {
	private JButton mensile, semestrale, annuale;

	public AbbonamentoGui(Cliente c, Proprietario p, Palestra pa) {
		setTitle("scelta abbonamenti");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel(new GridLayout(1, 1));
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

		mensile = new JButton("mensile");
		semestrale = new JButton("semestrale");
		annuale = new JButton("annuale");

		mensile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				ClienteAbbonato ca = new ClienteAbbonato(c, p.getAbbonamentoMensile());
				
				ClienteAbboDAO dao0 = new ClienteAbboDAO();
				dao0.insertCliente(c, ca);
				
				pa.abbonaCl(ca);
			}
		});

		semestrale.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				ClienteAbbonato ca = new ClienteAbbonato(c, p.getAbbonamentoSemestrale());
				
				ClienteAbboDAO dao0 = new ClienteAbboDAO();
				dao0.insertCliente(c, ca);
				
				pa.abbonaCl(ca);
				ca.visuClAbb(ca);

			}
		});

		annuale.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				ClienteAbbonato ca = new ClienteAbbonato(c, p.getAbbonamentoAnnuale());
				
				ClienteAbboDAO dao0 = new ClienteAbboDAO();
				dao0.insertCliente(c, ca);
				
				pa.abbonaCl(ca);
			}
		});

		buttonPanel.add(mensile);
		buttonPanel.add(semestrale);
		buttonPanel.add(annuale);

		buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		panel.add(buttonPanel);

		getContentPane().add(panel, BorderLayout.CENTER);

		setSize(480, 640);
		setLocationRelativeTo(null); // Posizioniamo la finestra al centro dello schermo
		setVisible(true);
	}

}