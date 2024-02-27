package NextFit;

public class Esercizio {
	private String nome;
	private int serie;
	private int ripetizioni;
	private int peso;
	private int riposoTraSerie;

	public Esercizio(String nome, int serie, int ripetizioni, int peso, int riposoTraSerie) {
		this.nome = nome;
		this.serie = serie;
		this.ripetizioni = ripetizioni;
		this.peso = peso;
		this.riposoTraSerie = riposoTraSerie;
	}

	public String getNome() {
		return nome;
	}
}
