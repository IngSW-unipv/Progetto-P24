package NextFit;

import javax.swing.SwingUtilities;

public class Test {
	public static void main(String[] args) {
		Palestra NextFit = new Palestra(20, 20);

		NextFit.registraDipendente(
				NextFit.creaDipendente("pino", "pino", "pino", "pino", 19, 98919, "PersonalTrainer"));
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
		System.out.println(NextFit.getDIP("Personaltrainer", 0).getCognome());

		NextFit.visuDip("Personaltrainer");

		System.out.println(NextFit.contaDip("personaltrainer"));
		/*
		 * 
		 * Cliente u1 = new Cliente("pino" , "pino", "pino", "pino", 19); Abbonamenti a
		 * = new Abbonamenti("MENSILE", 234); ClienteAbbonato c = new
		 * ClienteAbbonato(u1,a); c.visuClAbb(c);
		 */
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new ListaPT(NextFit);
			}
		});
	}
}