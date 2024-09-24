package Controller;

import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import DB.RichiesteDAO;
import Model.ClienteAbbonato;
import Model.Palestra;
import Model.PersonalTrainer;
import Model.Richieste;
import View.ListaPTView;

public class SceltaPTController {
	
	private ListaPTView view;	
	
	public SceltaPTController(ListaPTView view) {
		this.view = view;
	}

	public void scelta(ActionEvent e, Richieste r, PersonalTrainer pt, ClienteAbbonato clienteAbbonato, int n,
			Palestra p) {
		RichiesteDAO dao = new RichiesteDAO();
		JButton button = (JButton) e.getSource();

		boolean isIscritto = view.getIsIscritto()[n];
        boolean isRichiestaAttiva = view.getIsRichiestaAttiva();
		
		if (!isIscritto && !isRichiestaAttiva) {
			r.aggRichiesta(pt, clienteAbbonato, 0);
			button.setText(p.getDIP("Personaltrainer", n).getNome() + " " + p.getDIP("Personaltrainer", n).getCognome()
					+ " - richiesto");

			dao.insertRichiesta(r.ricarcaRichiesta(clienteAbbonato, pt));

			view.getIsIscritto()[n] = true;
            view.setIsRichiestaAttiva(true);
			r.visualizzaRichieste();
		} else if (isIscritto) {
			button.setText(
					p.getDIP("Personaltrainer", n).getNome() + " " + p.getDIP("Personaltrainer", n).getCognome());

			dao.deleteRichiesta(r.ricarcaRichiesta(clienteAbbonato, pt));
			r.eliminaRichiesta(r.ricarcaRichiesta(clienteAbbonato, pt));

			view.getIsIscritto()[n] = false;
            view.setIsRichiestaAttiva(false);
			r.visualizzaRichieste();
		} else {
			JOptionPane.showMessageDialog(null,
					"Hai giÃ  una richiesta attiva. Rimuovi la richiesta esistente prima di aggiungerne un'altra.",
					"Errore", JOptionPane.ERROR_MESSAGE);
		}

	}
}