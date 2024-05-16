package NextFit;

import java.util.ArrayList;
import java.util.List;

public class Richieste {

	private List<RichiestaAlPT> richieste;

	public Richieste() {
		richieste = new ArrayList<>();
	}

	public RichiestaAlPT aggRichiesta(PersonalTrainer pt, ClienteAbbonato ca, int i) {
		RichiestaAlPT rpt = new RichiestaAlPT(pt, ca, i);
		
		if (!richiestaPresente(ca, pt, i)) {
			richieste.add(rpt);
			// System.out.println("Cliente iscritto al corso con successo.");
		} else {
			System.out.println("Il cliente ha già fatto la richiesta.");
		}
		
		return rpt;
	}

	public void eliminaRichiesta(RichiestaAlPT richiesta) {
		richieste.remove(richiesta);
	}

	public List<RichiestaAlPT> getRichieste() {
		return richieste;
	}

	public void visualizzaRichieste() {
		for (RichiestaAlPT richiesta : richieste) {
			System.out.println("Cliente: " + richiesta.getCliente().getCliente().getNome() + " "
					+ richiesta.getCliente().getCliente().getCognome() + " - Personal Trainer: "
					+ richiesta.getDipendente().getNome() + " " + richiesta.getDipendente().getCognome());
		}
	}

	public boolean richiestaPresente(ClienteAbbonato cliente, Dipendente personalTrainer, int cod_scheda) {
		for (RichiestaAlPT richiesta : richieste) {
			if (richiesta.getCliente().equals(cliente) && richiesta.getDipendente().equals(personalTrainer)
					&& richiesta.getScheda() == cod_scheda) {
				return true;
			}
		}
		return false;
	}
}
