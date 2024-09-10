package Controller;

import DB.CorsiDAO;
import DB.IscrittoalcorsoDAO;
import NextFit.Corsi;
import NextFit.Corso;
import NextFit.Palestra;
import View.ElimCorsoView;

public class ElimCorsoController {

	private Corsi corsi;
	private Palestra palestra;
	private ElimCorsoView view;

	public ElimCorsoController(Corsi corsi, Palestra palestra, ElimCorsoView view) {
		this.corsi = corsi;
		this.palestra = palestra;
		this.view = view;
		this.view.setController(this);
	}

	public void deleteCorso(int index) {
		IscrittoalcorsoDAO idao = new IscrittoalcorsoDAO();
		CorsiDAO cdao = new CorsiDAO();

		Corso corso = corsi.getCorso(index);

		if (corso != null && corso.getCorsista() != null) {
			if (corsi.iscorsoPresente(corso)) {
				idao.deleteIscrizione1(corso.getNome(), corso.getCorsista().getNome(), corso.getCorsista().getCognome(),
						corso.getCorsista().getMail());
			}
			cdao.deleteCorso(corso.getNome(), corso.getCorsista().getNome(), corso.getCorsista().getCognome(),
					corso.getCorsista().getMail(), corsi, palestra);
		}
		view.updatePage();
	}
}
