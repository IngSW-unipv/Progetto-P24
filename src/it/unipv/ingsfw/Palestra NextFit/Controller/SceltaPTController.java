package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import DB.RichiesteDAO;
import NextFit.ClienteAbbonato;
import NextFit.Palestra;
import NextFit.PersonalTrainer;
import NextFit.Richieste;
import View.ListaPT;
import DB.RichiesteDAO;

public class SceltaPTController {

	public SceltaPTController()
	{

		}
	public void scelta(ActionEvent e,Richieste r,PersonalTrainer pt,ClienteAbbonato clienteAbbonato,int n,Palestra p,boolean isIscritto,boolean isRichiestaAttiva)
	{
		RichiesteDAO dao = new RichiesteDAO();
		JButton button = (JButton) e.getSource();

		
		
		if (!isIscritto && !isRichiestaAttiva) {
			r.aggRichiesta(pt, clienteAbbonato, 0);
			button.setText(p.getDIP("Personaltrainer", n).getNome() + " "
					+ p.getDIP("Personaltrainer", n).getCognome() + " - richiesto");

			dao.insertRichiesta(r.ricarcaRichiesta(clienteAbbonato, pt));

			isIscritto = true;
			isRichiestaAttiva = true;
			r.visualizzaRichieste();
		} else if (isIscritto) {
			button.setText(p.getDIP("Personaltrainer", n).getNome() + " "
					+ p.getDIP("Personaltrainer", n).getCognome());

			dao.deleteRichiesta(r.ricarcaRichiesta(clienteAbbonato, pt));
			r.eliminaRichiesta(r.ricarcaRichiesta(clienteAbbonato, pt));

			isIscritto = false;
			isRichiestaAttiva = false;
			r.visualizzaRichieste();
		} else {
			JOptionPane.showMessageDialog(null,
					"Hai giÃ  una richiesta attiva. Rimuovi la richiesta esistente prima di aggiungerne un'altra.",
					"Errore", JOptionPane.ERROR_MESSAGE);
		}
		
    }
}