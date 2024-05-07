package NextFit;

import javax.swing.SwingUtilities;

import DB.ClienteAbboDAO;
import DB.CorsiDAO;
import DB.DipendenteDAO;
import DB.IscrittoalcorsoDAO;
import GUI.PrimaPagina;

public class Tester {
	public static void main(String[] args) {
		Palestra NextFit = new Palestra(100, 100);
		Proprietario proprietario = Proprietario.getInstance();
		Corsi corsi = new Corsi(20);

		ClienteAbboDAO dao0 = new ClienteAbboDAO();
		DipendenteDAO dao1 = new DipendenteDAO();
		CorsiDAO dao2 = new CorsiDAO();
		IscrittoalcorsoDAO dao3 = new IscrittoalcorsoDAO();

		dao0.selectAll(NextFit);
		dao1.selectAll(NextFit);
		dao2.selectAll(NextFit, corsi);
		dao3.selectAll(NextFit, corsi);

		NextFit.registraDipendente(
				NextFit.creaDipendente("Pino", "Pino", "pino@tmail.com", "pi123no", 23, 2000, "corsista"));
		NextFit.registraDipendente(
				NextFit.creaDipendente("Wino", "Wino", "wino@fmai.com", "wi123no", 24, 1800, "Dietista"));
		NextFit.registraDipendente(
				NextFit.creaDipendente("Gino", "Gino", "gino@mail.com", "gi1234no", 32, 540, "PersonalTrainer"));
		NextFit.registraDipendente(
				NextFit.creaDipendente("Cino", "Cino", "cino@smail.com", "ci764no", 19, 675, "Corsista"));
		NextFit.registraDipendente(
				NextFit.creaDipendente("Tino", "Tino", "tino@mail.com", "tin233o", 29, 1000, "PersonalTrainer"));
		NextFit.registraDipendente(
				NextFit.creaDipendente("Sino", "Sino", "Sino@mail.com", "sn233o", 39, 1900, "PersonalTrainer"));

		NextFit.visuClAbbo();
		NextFit.visuListaDip();
		NextFit.visuDip("COrsisTA");

		corsi.visuCorsi();

		corsi.visuClisCorsi();

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new PrimaPagina(NextFit, proprietario, corsi);
			}
		});

	}
}