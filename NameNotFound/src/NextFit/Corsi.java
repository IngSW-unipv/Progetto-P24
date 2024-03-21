package NextFit;

public class Corsi {
	private Corso[] corsi;
	private int max, c;

	public Corsi(int max) {
		this.max = max;
		c = 0;
		corsi = new Corso[max];
	}

	public void aggCorsi(Corso corso) {
		corsi[c] = corso;
		c++;

		for (int i = 0; i < c - 1; i++) {
			if (corso.getCorsista() == corsi[i].getCorsista() && corso.getNome().equals(corsi[i].getNome())) {
				c--;
				System.out.println("Il corso già esiste. ");
			}
		}
	}

	public int getC() {
		return c;
	}
	
}
