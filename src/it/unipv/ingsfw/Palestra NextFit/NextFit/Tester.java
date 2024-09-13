package NextFit;

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

		/*NextFit.registraDipendente(
				NextFit.creaDipendente("Pino", "Pino", "pino@tmail.com", "pi123no", 23, 2000, "corsista"));
		NextFit.registraDipendente(
				NextFit.creaDipendente("Wino", "Wino", "wino@fmai.com", "wi123no", 24, 1800, "Dietista"));
		NextFit.registraDipendente(
				NextFit.creaDipendente("Gino", "Gino", "gino@email.com", "gi1234no", 32, 540, "PersonalTrainer"));
		NextFit.registraDipendente(
				NextFit.creaDipendente("Cino", "Cino", "cino@smail.com", "ci764no", 19, 675, "Corsista"));
		NextFit.registraDipendente(
				NextFit.creaDipendente("Tino", "Tino", "tino@email.com", "tin233o", 29, 1000, "PersonalTrainer"));
		NextFit.registraDipendente(
				NextFit.creaDipendente("Sino", "Sino", "sino@email.com", "aaa", 39, 1900, "PersonalTrainer"));*/
		
		NextFit.visuClAbbo();
		NextFit.visuListaDip();
		NextFit.visuDip("COrsisTA");

		corsi.visuCorsi();

		corsi.visuClisCorsi();

		r.visualizzaRichieste();

		r.visualizzaSchede();
		
		NextFit.eliminaCliente("m", "r", "mr@mr.com", r, corsi);
		NextFit.eliminaCliente("l", "b", "lb@lb.com", r, corsi);

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new PrimaPaginaView(NextFit, proprietario, corsi, r);
			}
		});

	}
}