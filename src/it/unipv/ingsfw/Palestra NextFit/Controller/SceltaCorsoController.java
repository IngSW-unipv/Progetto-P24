package Controller;

import java.awt.event.ActionEvent;

import javax.swing.JButton;

import DB.CorsiDAO;
import DB.IscrittoalcorsoDAO;
import NextFit.ClienteAbbonato;
import NextFit.Corsi;
import View.ListaCorsiView;

public class SceltaCorsoController {

	private ListaCorsiView view;
	
	public SceltaCorsoController(ListaCorsiView view) {
		this.view=view;
	}
	
	public void scelta(ActionEvent e,int n,Corsi co, ClienteAbbonato clienteAbbonato)
	{
		CorsiDAO dao = new CorsiDAO();
		IscrittoalcorsoDAO dao1 = new IscrittoalcorsoDAO();
		JButton button = (JButton) e.getSource();
		
		
		boolean isIscritto = view.getIsIscritto()[n];
		
		if (!isIscritto) {
			co.getCorso(n).aggPalCorso();
			System.out.println(co.getCorso(n).getNp());
			button.setText(co.getCorso(n).getNome() + " - iscritto");

			co.aggIsAlCorso(clienteAbbonato, co.getCorso(n));

			dao1.insertIscrizione(clienteAbbonato, co.getCorso(n));
			dao.upIscritti(co.getCorso(n).getNome());

			view.getIsIscritto()[n] = true;

			co.visuClisCorsi();
		} else {
			co.getCorso(n).eliPdalCorso();
			System.out.println(co.getCorso(n).getNp());
			button.setText(co.getCorso(n).getNome());

			co.elidalCorso(co.trovaIscritto(clienteAbbonato, co.getCorso(n)));

			dao1.deleteIscrizione(clienteAbbonato, co.getCorso(n));
			dao.dwIscritti(co.getCorso(n).getNome());

			view.getIsIscritto()[n] = false;

			co.visuClisCorsi();
		}
	}
	
	
	
	
}
