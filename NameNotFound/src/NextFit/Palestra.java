package NextFit;

public class Palestra // classe di tipo pure fabbrication -> pattern factory per la creazione dei
						// dipendenti e clienti
{
	private Cliente[] clienti;
	private Dipendente[] dipendenti;
	private int contatoreC, contatoreD, maxC, maxD;

	public Palestra(int maxC, int maxD) {
		this.maxC = maxC;
		this.maxD = maxD;
		clienti = new Cliente[maxC];
		dipendenti = new Dipendente[maxD];
		contatoreC = 0;
		contatoreD = 0;
	}

	public void registraCliente(Cliente cliente) {
		if (contatoreC < maxC && cliente.getEtà() > 17) {
			clienti[contatoreC] = cliente;
			contatoreC++;
		} else if (cliente.getEtà() < 18)
			System.out.println("La palestra non può iscrivere minorenni. ");
		else
			System.out.println("La palestra non può iscrivere più di " + maxC + " clienti. ");

		for (int i = 0; i < contatoreC - 1; i++) {
			if (cliente.getNome().equals(clienti[i].getNome()) && cliente.getCognome().equals(clienti[i].getCognome())
					&& cliente.getMail().equals(clienti[i].getMail())) // cotrollo su nome, cognome e mail che sono la
																		// chiave nel database
			{
				System.out.println("Il cliente è già presente nel database. ");
				contatoreC--; // alla nuova iscrizione verrà sovrascritto il cliente nuovo su quello già
								// presente
			}
		}
	}

	public Dipendente creaDipendente(String nome, String cognome, String mail, String password, int età,
			double stipendio, String tipo) {
		if (tipo.toLowerCase().equals("personaltrainer")) {
			Dipendente d = new PersonalTrainer(nome, cognome, mail, password, età, stipendio, tipo);
			return d;
		} else if (tipo.toLowerCase().equals("corsista")) {
			Dipendente d = new PersonalTrainer(nome, cognome, mail, password, età, stipendio, tipo);
			return d;
		} else if (tipo.toLowerCase().equals("fisioterapista")) {
			Dipendente d = new PersonalTrainer(nome, cognome, mail, password, età, stipendio, tipo);
			return d;
		} else if (tipo.toLowerCase().equals("dietista")) {
			Dipendente d = new PersonalTrainer(nome, cognome, mail, password, età, stipendio, tipo);
			return d;
		} else if (tipo.toLowerCase().equals("istruttore di sala")) {
			Dipendente d = new PersonalTrainer(nome, cognome, mail, password, età, stipendio, tipo);
			return d;
		} else
			return null;
	}

	public void registraDipendente(Dipendente dipendente) {
		if (contatoreD < maxD) {
			dipendenti[contatoreD] = dipendente;
			contatoreD++;
		} else
			System.out.println("La palestra non può iscrivere più di " + maxD + " dipendenti. ");

		for (int i = 0; i < contatoreD - 1; i++) {
			if (dipendente.getNome().equals(dipendenti[i].getNome())
					&& dipendente.getCognome().equals(dipendenti[i].getCognome())
					&& dipendente.getMail().equals(dipendenti[i].getMail())) // cotrollo su nome, cognome e mail che
																				// sono la chiave nel database
			{
				System.out.println("Il dipendente è già presente nel database. ");
				contatoreD--;
			}
		}
	}

	public int getMaxC() {
		return maxC;
	}

	public void setMaxC(int maxC) {
		this.maxC = maxC;
	}

	public void visuDip(String tipo) {
		System.out.println("I " + tipo.toLowerCase() + " sono : \n");
		for (int i = 0; i < contatoreD; i++) {
			if (tipo.toLowerCase().equals(dipendenti[i].getTipo())) {
				System.out.println(dipendenti[i].getNome() + " " + dipendenti[i].getCognome() + "\n");
			}
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
}
