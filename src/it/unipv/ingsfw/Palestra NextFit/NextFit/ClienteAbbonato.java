package NextFit;

import java.time.LocalDate;

public class ClienteAbbonato {
	private Cliente cliente;
	private Abbonamenti abbonamento;
	private LocalDate dataScadenza;

	public ClienteAbbonato(Cliente cliente, Abbonamenti abbonamento) {
		this.cliente = cliente;
		this.abbonamento = abbonamento;

		LocalDate dataOdierna = null;
		dataOdierna = LocalDate.now();
		dataScadenza = dataOdierna.plusMonths(abbonamento.getDurata());
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Abbonamenti getAbbonamento() {
		return abbonamento;
	}

	public void setAbbonamento(Abbonamenti abbonamento) {
		this.abbonamento = abbonamento;
	}

	public LocalDate getDataScadenza() {
		return dataScadenza;
	}

	public void aggScad(int n) {	//aggiungere scadenza anche in database
        dataScadenza = dataScadenza.plusMonths(n);
    }
	
	
	public void setDataScadenza(LocalDate dataScadenza) {
		this.dataScadenza = dataScadenza;
	}

	public void visuClAbb(ClienteAbbonato ca) {
		System.out.println("Il cliente: " + ca.getCliente() + " ha l'abbonamento: " + ca.getAbbonamento()
				+ " con scadenza: " + ca.getDataScadenza());
	}
	
}
