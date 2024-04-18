package NextFit;

public class Palestra // classe di tipo pure fabbrication -> pattern factory per la creazione dei
						// dipendenti e clienti
{
	private Cliente[] clienti;
	private Dipendente[] dipendenti;
	private ClienteAbbonato[] clientiAbbo;
	private int contatoreC, contatoreD, contatoreCA, maxC, maxD, maxCA;

	public Palestra(int maxC, int maxD) {
		this.maxC = maxC;
		this.maxCA = maxC;
		this.maxD = maxD;
		clienti = new Cliente[maxC];
		dipendenti = new Dipendente[maxD];
		clientiAbbo = new ClienteAbbonato[maxCA];
		contatoreC = 0;
		contatoreCA = 0;
		contatoreD = 0;
	}

	public boolean registraCliente(Cliente cliente) {
		boolean t = false;

		if (contatoreC < maxC && cliente.getEtà() > 17) {
			clienti[contatoreC] = cliente;
			contatoreC++;
			t = true;
		} else if (cliente.getEtà() < 18) {
			System.out.println("La palestra non può iscrivere minorenni. ");
			t = false;
		} else {
			System.out.println("La palestra non può iscrivere più di " + maxC + " clienti. ");
			t = false;
		}

		for (int i = 0; i < contatoreC - 1; i++) {
			if (cliente.getNome().equals(clienti[i].getNome()) && cliente.getCognome().equals(clienti[i].getCognome())
					&& cliente.getMail().equals(clienti[i].getMail())) // cotrollo su nome, cognome e mail che sono la
																		// chiave nel database
			{
				System.out.println("Il cliente è già presente nel database. ");
				contatoreC--; // alla nuova iscrizione verrà sovrascritto il cliente nuovo su quello già
				t = false; // presente
			}
		}

		return t;
	}

	public Dipendente creaDipendente(String nome, String cognome, String mail, String password, int età,
			double stipendio, String tipo) {
		switch (tipo.toLowerCase()) {
		case "personaltrainer":
			return new PersonalTrainer(nome, cognome, mail, password, età, stipendio, tipo);
		case "corsista":
			return new Corsista(nome, cognome, mail, password, età, stipendio, tipo);
		case "fisioterapista":
			return new Fisioterapista(nome, cognome, mail, password, età, stipendio, tipo);
		case "dietista":
			return new Dietista(nome, cognome, mail, password, età, stipendio, tipo);
		case "istruttoredisala":
			return new IstruttorediSala(nome, cognome, mail, password, età, stipendio, tipo);
		default:
			System.out.println("Tipo di dipendente non riconosciuto: " + tipo);
			return null;
		}
	}

	public void registraDipendente(Dipendente dipendente) {
		boolean presenteNelDatabase = false;

		for (int i = 0; i < contatoreD; i++) {
			if (dipendente.getNome().equals(dipendenti[i].getNome())
					&& dipendente.getCognome().equals(dipendenti[i].getCognome())
					&& dipendente.getMail().equals(dipendenti[i].getMail())) {
				presenteNelDatabase = true;
				System.out.println("Il dipendente è già presente nel database. ");
				return;
			}
		}

		if (contatoreD < maxD) {
			dipendenti[contatoreD] = dipendente;
			contatoreD++;
		} else {
			System.out.println("La palestra non può iscrivere più di " + maxD + " dipendenti. ");
			return;
		}

		if (!presenteNelDatabase) {
			DipendenteDAO dao1 = new DipendenteDAO();
			dao1.insertDipendente(dipendente);
		}
	}

	public int getMaxC() {
		return maxC;
	}

	public void setMaxC(int maxC) {
		this.maxC = maxC;
	}

	public void visuDip(String tipo) {
		System.out.println("I " + tipo.toLowerCase() + " sono :");
		for (int i = 0; i < contatoreD; i++) {
			if (tipo.toLowerCase().equals(dipendenti[i].getTipo())) {
				System.out.println(dipendenti[i].getNome() + " " + dipendenti[i].getCognome());
			}
		}
	}

	public void visuListaDip() {
		System.out.println("Lista dipendenti: ");
		for (int i = 0; i < contatoreD; i++) {
			System.out.println(dipendenti[i].getNome() + " " + dipendenti[i].getCognome() + " " + dipendenti[i].tipo);
		}
	}

	public Dipendente getDIP(String tipo, int n) {
		Dipendente[] dip;
		dip = new Dipendente[contatoreD];
		int j = 0;
		for (int i = 0; i < contatoreD; i++) {
			if (tipo.toLowerCase().equals(dipendenti[i].getTipo())) {
				dip[j] = dipendenti[i];
				j++;
			}
		}
		return dip[n];
	}

	public int contaDip(String tipo) {
		int j = 0;
		for (int i = 0; i < contatoreD; i++) {
			if (tipo.toLowerCase().equals(dipendenti[i].getTipo())) {
				j++;
			}
		}
		return j;
	}

	public void abbonaCl(ClienteAbbonato ca) {
		if (contatoreCA < maxCA) {
			clientiAbbo[contatoreCA] = ca;
			contatoreCA++;
		} else {
			System.out.println("Non possono essere abbonati più di " + maxCA + " clienti. ");
		}
	}

	public void visuClAbbo() {
		System.out.println("Lista dei clienti: ");
		for (int i = 0; i < contatoreCA; i++) {
			System.out.println(clientiAbbo[i].getCliente().getNome() + " " + clientiAbbo[i].getCliente().getCognome()
					+ " " + clientiAbbo[i].getDataScadenza());
		}
	}
}
