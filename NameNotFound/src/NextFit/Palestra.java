package NextFit;

import DB.DipendenteDAO;

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

		boolean clientePresente = false; // Flag per indicare se il cliente è già presente nel database
		boolean t = false;

		for (int i = 0; i < contatoreCA; i++) {
			if (cliente.getNome().equals(clientiAbbo[i].getCliente().getNome()) && cliente.getCognome().equals(clientiAbbo[i].getCliente().getCognome())
					&& cliente.getMail().equals(clientiAbbo[i].getCliente().getMail())) {
				System.out.println("Il cliente è già presente nel database.");
				clientePresente = true; // Imposta il flag a true se il cliente è già presente
				break;
			}
		}

	    if (!clientePresente) {
	        if (contatoreC < maxC && cliente.getEtà() > 17) {
	            clienti[contatoreC] = cliente;
	            contatoreC++;
	            t = true;
	        } else if (cliente.getEtà() < 18) {
	            System.out.println("La palestra non può iscrivere minorenni.");
	        } else {
	            System.out.println("La palestra non può iscrivere più di " + maxC + " clienti.");
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
	
	public Dipendente ricercaDip(String nome, String cognome) {
		Dipendente d = null;
		for (int i = 0; i < contatoreD; i++) {
			if (nome.toLowerCase().equals(dipendenti[i].getNome()) && cognome.toLowerCase().equals(dipendenti[i].getCognome())) {
				d = dipendenti[i];
				break;
			}
		}
		return d;
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

	public boolean esisteCli(String mail, String pass) {
		boolean t = false;

		for (int i = 0; i < contatoreCA; i++) {
			if ((clientiAbbo[i].getCliente().getMail().equals(mail) && clientiAbbo[i].getCliente().getPassword().equals(pass))) {
				t = true;
				break;
			}
		}

		return t;
	}
	
	public boolean esisteDip(String mail, String pass) {
		boolean t = false;

		for (int i = 0; i < contatoreCA; i++) {
			if ((dipendenti[i].getMail().equals(mail) && dipendenti[i].getPassword().equals(pass))) {
				t = true;
				break;
			}
		}

		return t;
	}
	
	
	public ClienteAbbonato accessoCli(String mail, String pass) {
		int j = -1;
		for (int i = 0; i < contatoreCA; i++) {
			if (clientiAbbo[i].getCliente().getMail().equals(mail) && clientiAbbo[i].getCliente().getPassword().equals(pass)) {
				j = i;
				break;
			}
		}

		return clientiAbbo[j];
	}
	
	public Dipendente accessoDip(String mail, String pass) {
		int j = -1;
		for (int i = 0; i < contatoreD; i++) {
			if (dipendenti[i].getMail().equals(mail) && dipendenti[i].getPassword().equals(pass)) {
				j = i;
				break;
			}
		}

		return dipendenti[j];
	}
	
	public String riconosciTipo(Dipendente d) {
		return d.getTipo();
	}
}
