package Model;

import javax.swing.SwingUtilities;

import DB.ClienteAbboDAO;
import DB.CorsiDAO;
import DB.DipendenteDAO;
import DB.IscrittoalcorsoDAO;
import DB.RichiesteDAO;
import DB.SchedaDAO;
import View.PrimaPaginaView;

public class Tester {
	public static void main(String[] args) {
		Palestra NextFit = new Palestra(100, 100);
		Proprietario proprietario = Proprietario.getInstance();
		Corsi corsi = new Corsi(20);
		Richieste r = new Richieste();

		ClienteAbboDAO dao0 = new ClienteAbboDAO();
		DipendenteDAO dao1 = new DipendenteDAO();
		CorsiDAO dao2 = new CorsiDAO();
		IscrittoalcorsoDAO dao3 = new IscrittoalcorsoDAO();
		RichiesteDAO dao4 = new RichiesteDAO();
		SchedaDAO dao5 = new SchedaDAO();

		dao0.selectAll(NextFit);
		dao1.selectAll(NextFit);
		dao2.selectAll(NextFit, corsi);
		dao3.selectAll(NextFit, corsi);
		dao4.selectAll(NextFit, r);
		dao5.selectAll(r);
		
		NextFit.visuClAbbo();
		NextFit.visuListaDip();
		NextFit.visuDip("COrsisTA");

		corsi.visuCorsi();

		corsi.visuClisCorsi();

		r.visualizzaRichieste();

		r.visualizzaSchede();

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new PrimaPaginaView(NextFit, proprietario, corsi, r);
			}
		});

	}
}