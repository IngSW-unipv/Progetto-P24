package View;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.*;
import NextFit.Corsi;
import NextFit.Corso;
import NextFit.Palestra;
import Controller.ElimCorsoController;
import Controller.NavigationController;

public class ElimCorsoView extends JFrame {
	private ArrayList<JButton> courseButtons;
	private JPanel panel, backPanel;
	private JButton back;
	private JLabel coursesLabel;
	private Palestra palestra;
	private Corsi corsi;
	private ProprietarioView parent;
	private ElimCorsoController controller;

	public ElimCorsoView(Corsi corsi, Palestra palestra, ProprietarioView parent) {
		this.palestra = palestra;
		this.corsi = corsi;
		this.parent = parent;

		setTitle("Interfaccia Corsi");
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		GridLayout gridLayout = new GridLayout(0, 2);
		gridLayout.setHgap(10);
		gridLayout.setVgap(10);
		panel = new JPanel(gridLayout);
		panel.setBackground(new Color(28, 28, 28));
		panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		courseButtons = new ArrayList<>();

		coursesLabel = new JLabel("<html><font color='orange'>COR</font><font color='white'>SI</font></html>");
		coursesLabel.setFont(new Font("Rockwell", Font.BOLD, 30));
		panel.add(coursesLabel);

		backPanel = new JPanel(new FlowLayout());
		backPanel.setBackground(new Color(28, 28, 28));

		back = new JButton("Indietro");
		back.setBackground(new Color(250, 140, 0));
		back.setFont(new Font("Arial", Font.BOLD, 13));
		back.setBorder(BorderFactory.createLineBorder(new Color(250, 140, 0), 6, false));
		back.setMaximumSize(new Dimension(100, 30));
		NavigationController nc = new NavigationController();

		addBackButton();

		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				nc.indietro(parent);
				dispose();
			}
		});

		JScrollPane scrollPane = new JScrollPane(panel);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		getContentPane().add(scrollPane, BorderLayout.CENTER);

		setSize(480, 640);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public void setController(ElimCorsoController controller) {
		this.controller = controller;
		listaCorsi();
	}

	private void addBackButton() {
		panel.add(new JLabel());
		backPanel.add(back);
		panel.add(backPanel);
	}

	private void listaCorsi() {
		for (int i = 0; i < corsi.getC(); i++) {
			Corso corso = corsi.getCorso(i);
			if (corso != null) {
				JButton button = new JButton(corso.getNome());
				button.setBackground(new Color(40, 40, 40));
				button.setPreferredSize(new Dimension(200, 100));
				button.setFont(new Font("Rockwell", Font.BOLD, 20));
				button.setForeground(Color.white);
				courseButtons.add(button);
				panel.add(button);
				final int index = i;
				button.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						controller.deleteCorso(index);
					}
				});
			}
		}
		addBackButton();
	}

	public void updatePage() {
		panel.removeAll();
		panel.add(coursesLabel);
		panel.add(new JLabel());
		courseButtons.clear();
		listaCorsi();
		panel.revalidate();
		panel.repaint();
	}
}
