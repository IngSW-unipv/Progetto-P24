package Controller;

import DB.ClienteAbboDAO;
import Model.Cliente;
import Model.ClienteAbbonato;
import Model.Palestra;
import Model.Proprietario;

public class AbbonamentiController {

	private ClienteAbboDAO dao0 = new ClienteAbboDAO();
	private Cliente c;
	private Proprietario p;
	private Palestra pa;

	public AbbonamentiController(Cliente c, Proprietario p, Palestra pa) {
		this.c = c;
		this.p = p;
		this.pa = pa;
	}

	public ClienteAbbonato mensile() {
		ClienteAbbonato ca = new ClienteAbbonato(c, p.getAbbonamentoMensile());

		dao0.insertCliente(c, ca);

		pa.abbonaCl(ca);
		ca.visuClAbb(ca);
		return ca;
	}

	public ClienteAbbonato semestrale() {
		ClienteAbbonato ca = new ClienteAbbonato(c, p.getAbbonamentoSemestrale());

		dao0.insertCliente(c, ca);

		pa.abbonaCl(ca);
		ca.visuClAbb(ca);

		return ca;
	}

	public ClienteAbbonato annuale() {
		ClienteAbbonato ca = new ClienteAbbonato(c, p.getAbbonamentoAnnuale());

		dao0.insertCliente(c, ca);

		pa.abbonaCl(ca);
		ca.visuClAbb(ca);

		return ca;
	}
}