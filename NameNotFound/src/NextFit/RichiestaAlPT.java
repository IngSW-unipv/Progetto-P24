package NextFit;

public class RichiestaAlPT {
	private PersonalTrainer pt;
	private ClienteAbbonato cliente;
	private int cod_scheda;

	public RichiestaAlPT(PersonalTrainer dipendente, ClienteAbbonato cliente, int cod_scheda) {
		this.cliente = cliente;
		if (dipendente.getTipo().equals("personaltrainer")) {
			this.pt = dipendente;
		}
		this.cod_scheda = cod_scheda;
	}

	public PersonalTrainer getDipendente() {
		return pt;
	}

	public void setPersonalTrainer(PersonalTrainer dipendente) {
		this.pt = dipendente;
	}

	public ClienteAbbonato getCliente() {
		return cliente;
	}

	public void setCliente(ClienteAbbonato cliente) {
		this.cliente = cliente;
	}

	public int getScheda() {
		return cod_scheda;
	}

	public void setScheda(int scheda) {
		this.cod_scheda = scheda;
	}

	public PersonalTrainer getPt() {
		return pt;
	}

	public void setPt(PersonalTrainer pt) {
		this.pt = pt;
	}

	public int getCod_scheda() {
		return cod_scheda;
	}

	public void setCod_scheda(int cod_scheda) {
		this.cod_scheda = cod_scheda;
	}

}
