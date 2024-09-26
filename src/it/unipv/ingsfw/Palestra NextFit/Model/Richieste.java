package Model;

import java.util.ArrayList;
import java.util.List;

public class Richieste {

	private List<RichiestaAlPT> richieste;
	private List<Scheda> schede;

	public Richieste() {
		richieste = new ArrayList<>();
		schede = new ArrayList<>();
	}

	public List<Scheda> getSchede() {
		return schede;
	}

	public Scheda aggScheda(Scheda scheda) {
		if (!schedaPresente(scheda.getCod_Scheda())) {
			schede.add(scheda);
		} else {
			System.out.println("La scheda è già presente.");
		}
		return scheda;
	}

	public int getCodiceSchedaCliente(ClienteAbbonato cliente) {
		for (RichiestaAlPT richiesta : richieste) {
			if (richiesta.getCliente().equals(cliente)) {
				return richiesta.getCod_scheda();
			}
		}
		return 0;
	}

	public void eliminaScheda(Scheda scheda) {
		schede.remove(scheda);
	}

	public Scheda getScheda(int cod_scheda) {
		for (Scheda scheda : schede) {
			if (scheda.getCod_Scheda() == cod_scheda) {
				return scheda;
			}
		}
		return null;
	}

	public boolean schedaPresente(int cod_scheda) {
		for (Scheda scheda : schede) {
			if (scheda.getCod_Scheda() == cod_scheda) {
				return true;
			}
		}
		return false;
	}

	public void visualizzaSchede() {
		for (Scheda scheda : schede) {
			System.out.println("Codice Scheda: " + scheda.getCod_Scheda());
			System.out.println("Elenco Esercizi: ");
			for (Esercizio esercizio : scheda.getElencoEsercizi()) {
				System.out.println(" - " + esercizio.getNome());

			}
			System.out.println();
		}
	}

	public RichiestaAlPT aggRichiesta(PersonalTrainer pt, ClienteAbbonato ca, int i) {
		RichiestaAlPT rpt = new RichiestaAlPT(pt, ca, i);

		if (!richiestaPresente(ca, pt)) {
			richieste.add(rpt);
		} else {
			System.out.println("Il cliente ha già fatto la richiesta.");
		}

		return rpt;
	}

	public void modSchedaId(ClienteAbbonato cliente, Dipendente personalTrainer, int i) {
		for (RichiestaAlPT richiesta : richieste) {
			if (richiesta.getCliente().equals(cliente) && richiesta.getDipendente().equals(personalTrainer)) {
				richiesta.setScheda(i);
			}
		}
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
					+ richiesta.getDipendente().getNome() + " " + richiesta.getDipendente().getCognome()
			/* +richiesta.getScheda() */);
		}
	}

	public String getNomeCL(int n, PersonalTrainer pt) {
		RichiestaAlPT richiesta = getRichiestePerPersonalTrainer(pt).get(n);

		return richiesta.getCliente().getCliente().getNome();

	}

	public String getCognomeCL(int n, PersonalTrainer pt) {

		RichiestaAlPT richiesta = getRichiestePerPersonalTrainer(pt).get(n);

		return richiesta.getCliente().getCliente().getCognome();

	}

	public List<RichiestaAlPT> getRichiestePerPersonalTrainer(PersonalTrainer pt) {
		List<RichiestaAlPT> richiesteFiltrate = new ArrayList<>();
		for (RichiestaAlPT richiesta : richieste) {
			if (richiesta.getDipendente().equals(pt)) {
				richiesteFiltrate.add(richiesta);
			}
		}
		return richiesteFiltrate;
	}

	public RichiestaAlPT getrichiestapt(PersonalTrainer pt, int i) {
		return getRichiestePerPersonalTrainer(pt).get(i);

	}

	public int getR(PersonalTrainer pt) {
		int i = 0;
		for (RichiestaAlPT richiesta : richieste) {
			if (richiesta.getDipendente().equals(pt))
				i++;
		}
		return i;
	}

	/*
	 * public boolean richiestaPresente(ClienteAbbonato cliente, Dipendente
	 * personalTrainer, int cod_scheda) { for (RichiestaAlPT richiesta : richieste)
	 * { if (richiesta.getCliente().equals(cliente) &&
	 * richiesta.getDipendente().equals(personalTrainer) && richiesta.getScheda() ==
	 * cod_scheda) { return true; } } return false; }
	 */

	public boolean richiestaPresente(ClienteAbbonato cliente, Dipendente personalTrainer) {
		for (RichiestaAlPT richiesta : richieste) {
			if (richiesta.getCliente().equals(cliente) && richiesta.getDipendente().equals(personalTrainer)) {
				return true;
			}
		}
		return false;
	}

	public RichiestaAlPT ricarcaRichiesta(ClienteAbbonato cliente, Dipendente personalTrainer) {
		for (RichiestaAlPT richiesta : richieste) {
			if (richiesta.getCliente().equals(cliente) && richiesta.getDipendente().equals(personalTrainer)) {
				return richiesta;
			}
		}
		return null;
	}

	public RichiestaAlPT ricarcaRichiestaCl(ClienteAbbonato cliente) {
		for (RichiestaAlPT richiesta : richieste) {
			if (richiesta.getCliente().equals(cliente)) {
				return richiesta;
			}
		}
		return null;
	}

	public boolean clRichieste(ClienteAbbonato cliente) {
		for (RichiestaAlPT richiesta : richieste) {
			if (richiesta.getCliente().equals(cliente)) {
				return true;
			}
		}
		return false;
	}

	public boolean ptRichieste(Dipendente personalTrainer) {
		for (RichiestaAlPT richiesta : richieste) {
			if (richiesta.getDipendente().equals(personalTrainer)) {
				return true;
			}
		}
		return false;
	}

	public RichiestaAlPT getRichiesteI(int index) {
		if (index >= 0 && index < richieste.size()) {
			return richieste.get(index);
		} else {
			System.out.println("Indice fuori dai limiti");
			return null;
		}
	}
}
