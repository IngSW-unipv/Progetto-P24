package Controller;

import DB.ClienteAbboDAO;
import DB.IscrittoalcorsoDAO;
import DB.RichiesteDAO;
import Model.ClienteAbbonato;
import Model.Corsi;
import Model.Palestra;
import Model.Richieste;

public class ElimClienteController {

	public void eliminaCliente(Richieste r, Corsi c, ClienteAbbonato ca, Palestra p) {

		ClienteAbboDAO dao = new ClienteAbboDAO();
		RichiesteDAO dao1 = new RichiesteDAO();
		IscrittoalcorsoDAO dao2 = new IscrittoalcorsoDAO();

		if (r.clRichieste(ca)) {
			dao2.deleteIscrizione2(ca);
			c.eliminaIscrizioniCliente(ca);
			dao1.deleteRichiesta(r.ricarcaRichiestaCl(ca));
			r.eliminaRichiesta(r.ricarcaRichiestaCl(ca));
		} else if (c.isclPresente(ca)) {
			dao2.deleteIscrizione2(ca);
			c.eliminaIscrizioniCliente(ca);
		}

		p.eliCli(ca.getCliente().getNome(), ca.getCliente().getCognome(), ca.getCliente().getMail());
		dao.deleteCliente(ca.getCliente().getNome(), ca.getCliente().getCognome(), ca.getCliente().getMail());

	}
}
