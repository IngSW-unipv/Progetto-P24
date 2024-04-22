package NextFit;

public class Corsi {
	private Corso[] corsi;
	private int max, c;

	public Corsi(int max) {
		this.max = max;
		c = 0;
		corsi = new Corso[max];
	}

	public Corso getCorso(int i) {
		Corso corso;
		corso = corsi[i];
		return corso;
	}

	public void aggCorsi(Corso corso) {
	    boolean corsoEsistente = false;
	    for (int i = 0; i < c; i++) {
	        if (corso.getNome().equals(corsi[i].getNome()) && corso.getCorsista().equals(corsi[i].getCorsista())) {
	            corsoEsistente = true;
	            System.out.println("Il corso già esiste.");
	            break;
	        }
	    }
	    
	    if (!corsoEsistente) {
	        corsi[c] = corso;
	        c++;
	        
	        CorsiDAO dao2 = new CorsiDAO();
	        dao2.insertCorso(corso);
	    }
	}
	
	public int getC() {
		return c;
	}

	public void visuCorsi() {
		System.out.println("Lista corsi: ");
		for (int i = 0; i < c; i++) {
			System.out.println(corsi[i].getNome() + " " + corsi[i].getCorsista().getNome() + " " + corsi[i].getCorsista().getCognome());
		}
	}
}
