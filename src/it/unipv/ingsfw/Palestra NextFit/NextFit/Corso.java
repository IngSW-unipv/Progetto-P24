package NextFit;

public class Corso {
	private String nome;
	private Corsista corsista;
	private int maxp; // massimo di persone di un corso
	private int np; // numero di persone "attuale"

	public Corso(String nome, Dipendente corsista, int maxp, int np) {
		this.nome = nome.toLowerCase();
		this.corsista = (Corsista) corsista;
		if (maxp > np) {
			this.maxp = maxp;
			this.np = np;
		} else {
			this.maxp = maxp;
			this.np = 0;
		}
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Corsista getCorsista() {
		return corsista;
	}

	public void setCorsista(Corsista corsista) {
		this.corsista = corsista;
	}

	public int getMaxp() {
		return maxp;
	}

	public void setMaxp(int maxp) {
		this.maxp = maxp;
	}

	public int getNp() {
		return np;
	}

	public void setNp(int np) {
		this.np = np;
	}

	public void aggPalCorso() {
		np++;
		if (np > maxp) {
			System.out.println("Il corso è pieno, non è possibile aggiungere un iscritto. ");
			np--;
		}
	}

	public void eliPdalCorso() {
		np--;
		if (np < 0) {
			np++;
			System.out.println("Impossibile eliminare clienti dal corso poichè il corso non ne ha. ");
		}
	}
}
