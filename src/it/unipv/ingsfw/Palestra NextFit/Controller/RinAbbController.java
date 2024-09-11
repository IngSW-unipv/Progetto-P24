package Controller;

import DB.ClienteAbboDAO;
import NextFit.ClienteAbbonato;

public class RinAbbController {

	private ClienteAbbonato clienteAbbonato;

	public RinAbbController(ClienteAbbonato clienteAbbonato) {
		this.clienteAbbonato = clienteAbbonato;
	}

	public void aggMensile(ClienteAbboDAO dao) {

		clienteAbbonato.aggScad(1);
		dao.updateScadenzaAbbonamento(clienteAbbonato.getCliente().getNome(), clienteAbbonato.getCliente().getCognome(),
				clienteAbbonato.getCliente().getMail(), clienteAbbonato.getDataScadenza());

	}

	public void aggSemestrale(ClienteAbboDAO dao) {

		clienteAbbonato.aggScad(6);
		dao.updateScadenzaAbbonamento(clienteAbbonato.getCliente().getNome(), clienteAbbonato.getCliente().getCognome(),
				clienteAbbonato.getCliente().getMail(), clienteAbbonato.getDataScadenza());

	}

	public void aggAnnuale(ClienteAbboDAO dao) {

		clienteAbbonato.aggScad(12);
		dao.updateScadenzaAbbonamento(clienteAbbonato.getCliente().getNome(),
				clienteAbbonato.getCliente().getCognome(), clienteAbbonato.getCliente().getMail(),
				clienteAbbonato.getDataScadenza());
		
	}
}
