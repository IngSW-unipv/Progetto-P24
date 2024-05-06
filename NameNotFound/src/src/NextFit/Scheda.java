package NextFit;

import java.util.ArrayList;
import java.util.List;

public class Scheda {
	private String nome;
	private List<Esercizio> elencoEsercizi;

	public Scheda(String nome) {
		this.nome = nome;
		this.elencoEsercizi = new ArrayList<>();
	}

	public void aggiungiEsercizio(Esercizio esercizio) {
		elencoEsercizi.add(esercizio);
	}

	public String getNome() {
		return nome;
	}

	public List<Esercizio> getElencoEsercizi() {
		return elencoEsercizi;
	}
}
