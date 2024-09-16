package Controller;

import DB.RichiesteDAO;
import DB.SchedaDAO;
import NextFit.Esercizio;
import NextFit.Palestra;
import NextFit.RichiestaAlPT;
import NextFit.Richieste;
import NextFit.Scheda;
import View.CreaSchedaView;

public class CreaSchedaController {

	private Richieste ri;
	private RichiestaAlPT r;
	private CreaSchedaView view;
	private int index;

	public CreaSchedaController(Richieste ri, RichiestaAlPT r, CreaSchedaView view, int index) {
		this.ri = ri;
		this.r = r;
		this.view = view;
		this.index = index;
	}

	public void creaScheda() {
		Esercizio e1 = new Esercizio(view.getEs01().getText());
		Esercizio e2 = new Esercizio(view.getEs02().getText());
		Esercizio e3 = new Esercizio(view.getEs03().getText());
		Esercizio e4 = new Esercizio(view.getEs04().getText());
		Esercizio e5 = new Esercizio(view.getEs05().getText());
		Scheda s = new Scheda(ri.getSchede().size() + 1);
		s.aggiungiEsercizio(e1);
		s.aggiungiEsercizio(e2);
		s.aggiungiEsercizio(e3);
		s.aggiungiEsercizio(e4);
		s.aggiungiEsercizio(e5);
		ri.aggScheda(s);
		ri.modSchedaId(ri.getRichiesteI(index).getCliente(), ri.getRichiesteI(index).getDipendente(), ri.getSchede().size() + 1);
		
		SchedaDAO schedao = new SchedaDAO();
		RichiesteDAO ridao = new RichiesteDAO();

		schedao.insert(s);
		ridao.updateSchedaId(r, s.getCod_Scheda());
	}
}