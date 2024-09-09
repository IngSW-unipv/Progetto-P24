package NextFit;

import java.util.ArrayList;
import java.util.List;

public class Scheda {
	private int cod_scheda;
	private List<Esercizio> elencoEsercizi;

	public Scheda(int cod_scheda) {
		this.cod_scheda = cod_scheda;
		this.elencoEsercizi = new ArrayList<>();
	}

	public void aggiungiEsercizio(Esercizio esercizio) {
		elencoEsercizi.add(esercizio);
	}

	public int getCod_Scheda() {
		return cod_scheda;
	}

	public List<Esercizio> getElencoEsercizi() {
		return elencoEsercizi;
	}

	public String getNomeEsercizio(int posizione) {
		if (posizione < 0 || posizione >= elencoEsercizi.size()) {
			throw new IndexOutOfBoundsException("Posizione non valida");
		}
		return elencoEsercizi.get(posizione).getNome();
	}

}

