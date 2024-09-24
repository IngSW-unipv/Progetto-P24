package Model;

public class IscrittoalCorso {
	private Corso corso;
	private ClienteAbbonato cliente;

	public IscrittoalCorso(Corso corso, ClienteAbbonato cliente) {
		this.corso = corso;
		this.cliente = cliente;
	}

	public Corso getCorso() {
		return corso;
	}

	public void setCorso(Corso corso) {
		this.corso = corso;
	}

	public ClienteAbbonato getCliente() {
		return cliente;
	}

	public void setCliente(ClienteAbbonato cliente) {
		this.cliente = cliente;
	}

}