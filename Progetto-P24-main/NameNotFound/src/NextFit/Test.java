package NextFit;

import javax.swing.SwingUtilities;

public class Test {
	public static void main(String[] args) {
		Palestra NextFit = new Palestra(20, 20);

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
		
		//System.out.println(NextFit.getDIP("COrsista", 0));
		/*System.out.println(NextFit.getDIP("Personaltrainer", 0).getCognome());

		NextFit.visuDip("corsista");

		System.out.println(NextFit.contaDip("corsista"));
		/*
		 * 
		 * Cliente u1 = new Cliente("pino" , "pino", "pino", "pino", 19); Abbonamenti a
		 * = new Abbonamenti("MENSILE", 234); ClienteAbbonato c = new
		 * ClienteAbbonato(u1,a); c.visuClAbb(c);
		 */
		/*
		 * SwingUtilities.invokeLater(new Runnable() { public void run() { new
		 * ListaPT(NextFit); } });
		 */
		Corso c0 = new Corso("ewg", NextFit.getDIP("corsista", 0), 9, 3);
		Corso c1 = new Corso("ewqg", NextFit.getDIP("corsista", 1), 9, 3);
		Corso c2 = new Corso("eewg", NextFit.getDIP("corsista", 1), 9, 3);
		Corso c3 = new Corso("ewfg", NextFit.getDIP("corsista", 3), 9, 3);
		
		Corsi corsi = new Corsi(20);
		corsi.aggCorsi(c0);
		corsi.aggCorsi(c0);
		corsi.aggCorsi(c1);
		corsi.aggCorsi(c2);
		corsi.aggCorsi(c3);
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new ListaCORSI(corsi);
			}
		});
	}
	
}