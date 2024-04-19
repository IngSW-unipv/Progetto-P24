package NextFit;

import java.util.ArrayList;

import javax.swing.SwingUtilities;

public class Tester {
	public static void main(String[] args) {
		Palestra NextFit = new Palestra(100, 100); // Passa la capacità massima di clienti alla palestra
		Proprietario proprietario = new Proprietario();
		
		ClienteAbboDAO dao0 = new ClienteAbboDAO();
		DipendenteDAO dao1 = new DipendenteDAO();
		
		dao0.selectAll(NextFit);
		dao1.selectAll(NextFit);
		
		NextFit.registraDipendente(NextFit.creaDipendente("Pino", "Pino", "pino@tmail.com", "pi123no", 23, 2000, "corsista"));
		NextFit.registraDipendente(NextFit.creaDipendente("Wino", "Wino", "wino@fmai.com", "wi123no", 24, 1800, "Dietista"));
		NextFit.registraDipendente(
				NextFit.creaDipendente("Gino", "Gino", "gino@mail.com", "gi1234no", 32, 540, "PersonalTrainer"));
		NextFit.registraDipendente(NextFit.creaDipendente("Cino", "Cino", "cino@smail.com", "ci764no", 19, 675, "Corsista"));
		NextFit.registraDipendente(
				NextFit.creaDipendente("Tino", "Tino", "tino@mail.com", "tin233o", 29, 1000, "PersonalTrainer"));
		NextFit.registraDipendente(
				NextFit.creaDipendente("Sino", "Sino", "Sino@mail.com", "sn233o", 39, 1900, "PersonalTrainer"));
		
		NextFit.visuClAbbo();
		NextFit.visuListaDip();
		NextFit.visuDip("personaltrainer");
		
		Corso c0 = new Corso("Pilates", NextFit.getDIP("corsista", 0), 9, 0);
		Corso c1 = new Corso("Boxe", NextFit.getDIP("corsista", 1), 9, 0);
		Corso c2 = new Corso("Nuoto", NextFit.getDIP("corsista", 1), 9, 0);
		Corso c3 = new Corso("Aerobica", NextFit.getDIP("corsista", 3), 9, 0);
		
		System.out.println(NextFit.accesso("alessia.marroni@example.com", "topsecret"));

		Corsi corsi = new Corsi(20);
		corsi.aggCorsi(c0);
		corsi.aggCorsi(c0);
		corsi.aggCorsi(c1);
		corsi.aggCorsi(c2);
		corsi.aggCorsi(c3);
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new PalestraGui(NextFit, proprietario, corsi);
			}
		});

	}
}
