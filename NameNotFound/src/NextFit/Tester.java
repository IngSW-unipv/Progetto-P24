package NextFit;

import javax.swing.SwingUtilities;

public class Tester {
	public static void main(String[] args) {
		Palestra NextFit = new Palestra(100, 100); // Passa la capacità massima di clienti alla palestra
		CreaClAbbo ca = new CreaClAbbo(NextFit);
		Proprietario proprietario = new Proprietario();
		NextFit.registraDipendente(
				NextFit.creaDipendente("pino", "pino", "pino", "pino", 19, 98919, "corsista"));
		NextFit.registraDipendente(NextFit.creaDipendente("wino", "pino", "pino", "pino", 19, 98919, "Dietista"));
		NextFit.registraDipendente(
				NextFit.creaDipendente("gino", "tino", "pino", "pino", 19, 98919, "PersonalTrainer"));
		NextFit.registraDipendente(NextFit.creaDipendente("cino", "pino", "pino", "pino", 19, 98919, "Corsista"));
		NextFit.registraDipendente(
				NextFit.creaDipendente("tino", "pino", "pino", "pino", 19, 98919, "PersonalTrainer"));
		NextFit.registraDipendente(
				NextFit.creaDipendente("tidno", "pino", "pino", "pino", 19, 98919, "PersonalTrainer"));
		NextFit.registraDipendente(
				NextFit.creaDipendente("tidgno", "pino", "pino", "pino", 19, 98919, "PersonalTrainer"));
		NextFit.registraDipendente(
				NextFit.creaDipendente("tgno", "pino", "pino", "pino", 19, 98919, "Corsista"));
		NextFit.registraDipendente(
				NextFit.creaDipendente("gno", "pino", "pino", "pino", 19, 98919, "Corsista"));
		NextFit.registraDipendente(
				NextFit.creaDipendente("teidgno", "pino", "pino", "pino", 19, 98919, "Corsista"));
		Corso c0 = new Corso("Pilates", NextFit.getDIP("corsista", 0), 9, 0);
		Corso c1 = new Corso("Boxe", NextFit.getDIP("corsista", 1), 9, 0);
		Corso c2 = new Corso("Nuoto", NextFit.getDIP("corsista", 1), 9, 0);
		Corso c3 = new Corso("Aerobica", NextFit.getDIP("corsista", 3), 9, 0);
		
		Corsi corsi = new Corsi(20);
		corsi.aggCorsi(c0);
		corsi.aggCorsi(c0);
		corsi.aggCorsi(c1);
		corsi.aggCorsi(c2);
		corsi.aggCorsi(c3);
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new PalestraGui(NextFit, proprietario, ca, corsi);
			}
		});
	}
}
