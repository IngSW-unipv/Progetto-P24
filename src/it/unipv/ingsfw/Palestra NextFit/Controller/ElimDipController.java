package Controller;

import DB.DipendenteDAO;
import DB.IscrittoalcorsoDAO;
import DB.CorsiDAO;
import DB.RichiesteDAO;
import Model.Corsi;
import Model.Corso;
import Model.Dipendente;
import Model.Palestra;
import Model.RichiestaAlPT;
import Model.Richieste;
import View.ElimDipView;

public class ElimDipController {
	private Palestra palestra;
	private Corsi corsi;
	private Richieste richieste;
	private ElimDipView view;

	public ElimDipController(Palestra palestra, Corsi corsi, Richieste richieste, ElimDipView view) {
		this.palestra = palestra;
		this.corsi = corsi;
		this.richieste = richieste;
		this.view = view;
		this.view.setController(this);
	}

	public void deleteDipendente(int index) {
		DipendenteDAO dipdao = new DipendenteDAO();
		IscrittoalcorsoDAO idao = new IscrittoalcorsoDAO();
		CorsiDAO cdao = new CorsiDAO();
		RichiesteDAO rdao = new RichiesteDAO();

		Dipendente dipendente = palestra.getDIP2(index);

		if (dipendente.getTipo().equals("corsista")) {
			for (int j = 0; j < corsi.getC(); j++) {
				Corso corso = corsi.getCorso(j);
				if (dipendente.equals(corso.getCorsista())) {
					if (corsi.iscorsoPresente(corso)) {
						idao.deleteIscrizione1(corso.getNome(), corso.getCorsista().getNome(),
								corso.getCorsista().getCognome(), corso.getCorsista().getMail());
					}
					cdao.deleteCorso(corso.getNome(), dipendente.getNome(), dipendente.getCognome(),
							dipendente.getMail(), corsi, palestra);
				}
			}
		} else if (dipendente.getTipo().equals("personaltrainer")) {
			for (int j = 0; j < richieste.getRichieste().size(); j++) {
				RichiestaAlPT richiesta = richieste.getRichiesteI(j);
				if (dipendente.equals(richiesta.getDipendente())) {
					if (richieste.ptRichieste(dipendente)) {
						rdao.deleteRichiesta(richiesta);
					}
				}
			}
		}

		dipdao.eliminaDip(dipendente.getNome(), dipendente.getCognome(), dipendente.getMail(), palestra);
		view.updatePage();
	}
}
