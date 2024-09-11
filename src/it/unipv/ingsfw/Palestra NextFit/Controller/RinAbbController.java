package Controller;

import DB.ClienteAbboDAO;
import NextFit.ClienteAbbonato;

public class RinAbbController {

	private ClienteAbbonato clienteAbbonato;
	private ClienteAbboDAO dao;

	public RinAbbController(ClienteAbbonato clienteAbbonato) {
		this.clienteAbbonato = clienteAbbonato;
		ClienteAbboDAO dao = new ClienteAbboDAO();
	}

	public void aggMensile() {

		clienteAbbonato.aggScad(1);
		dao.updateScadenzaAbbonamento(clienteAbbonato.getCliente().getNome(), clienteAbbonato.getCliente().getCognome(),
				clienteAbbonato.getCliente().getMail(), clienteAbbonato.getDataScadenza());

	}

	public void aggSemestrale() {

		clienteAbbonato.aggScad(6);
		dao.updateScadenzaAbbonamento(clienteAbbonato.getCliente().getNome(), clienteAbbonato.getCliente().getCognome(),
				clienteAbbonato.getCliente().getMail(), clienteAbbonato.getDataScadenza());

	}

	public void aggAnnuale() {

		clienteAbbonato.aggScad(12);
		dao.updateScadenzaAbbonamento(clienteAbbonato.getCliente().getNome(),
				clienteAbbonato.getCliente().getCognome(), clienteAbbonato.getCliente().getMail(),
				clienteAbbonato.getDataScadenza());
		
	}
}
