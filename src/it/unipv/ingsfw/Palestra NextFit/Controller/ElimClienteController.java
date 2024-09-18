package Controller;

import DB.ClienteAbboDAO;
import DB.IscrittoalcorsoDAO;
import DB.RichiesteDAO;
import NextFit.ClienteAbbonato;
import NextFit.Corsi;
import NextFit.Richieste;

public class ElimClienteController {

	public void eliminaCliente(Richieste r, Corsi c, ClienteAbbonato ca) {

		ClienteAbboDAO dao = new ClienteAbboDAO();
		RichiesteDAO dao1 = new RichiesteDAO();
		IscrittoalcorsoDAO dao2 = new IscrittoalcorsoDAO();

		if (c.isclPresente(ca)) {
			dao2.deleteIscrizione2(ca);
			c.eliminaIscrizioniCliente(ca);
		} else if (r.clRichieste(ca)) {
			dao1.deleteRichiesta(r.ricarcaRichiestaCl(ca));
			r.eliminaRichiesta(r.ricarcaRichiestaCl(ca));
		}

		dao.deleteCliente(ca.getCliente().getNome(), ca.getCliente().getCognome(), ca.getCliente().getMail());

	}
}
