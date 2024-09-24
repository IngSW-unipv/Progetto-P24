package Model;

import java.time.LocalDate;

import DB.ClienteAbboDAO;
import DB.DipendenteDAO;
import DB.IscrittoalcorsoDAO;
import DB.RichiesteDAO;

public class Palestra {
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
			if (/*
				 * cliente.getNome().equals(clientiAbbo[i].getCliente().getNome()) // da non
				 * fare controllo su nome e cognome perchè si potrebbe creare con la stessa mail
				 * 2 account &&
				 * cliente.getCognome().equals(clientiAbbo[i].getCliente().getCognome()) &&
				 */ cliente.getMail().equals(clientiAbbo[i].getCliente().getMail())) {
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

	public boolean registraDipendente(Dipendente dipendente) {
		boolean presenteNelDatabase = false;

		for (int i = 0; i < contatoreD; i++) {
			if (/*
				 * dipendente.getNome().equals(dipendenti[i].getNome()) &&
				 * dipendente.getCognome().equals(dipendenti[i].getCognome()) &&
				 */ dipendente.getMail().equals(dipendenti[i].getMail())) {
				presenteNelDatabase = true;
				System.out.println("Il dipendente è già presente nel database. ");
				return false;
			}
		}

		if (dipendente.getEtà() < 18) {
			System.out.println("Il dipendente deve avere almeno 18 anni.");
			return false;
		}

		if (contatoreD < maxD) {
			dipendenti[contatoreD] = dipendente;
			contatoreD++;
		} else {
			System.out.println("La palestra non può iscrivere più di " + maxD + " dipendenti. ");
			return false;
		}

		if (!presenteNelDatabase) {
			DipendenteDAO dao1 = new DipendenteDAO();
			dao1.insertDipendente(dipendente);
			return true;
		} else
			return false;

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

	public Dipendente getDIP2(int n) {
		Dipendente[] dip;
		dip = new Dipendente[contatoreD];
		int j = 0;
		for (int i = 0; i < contatoreD; i++) {

			dip[j] = dipendenti[i];
			j++;

		}
		return dip[n];
	}

	public int getD() {
		return contatoreD;
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

	public Dipendente ricercaCorsista(String nome, String cognome, String mail) {
		Dipendente d = null;
		for (int i = 0; i < contatoreD; i++) {
			if (nome.toLowerCase().equals(dipendenti[i].getNome())
					&& cognome.toLowerCase().equals(dipendenti[i].getCognome())
					&& mail.toLowerCase().equals(dipendenti[i].getMail())) {
				d = dipendenti[i];
				break;
			}
		}
		return d;
	}

	public Dipendente ricercaDip(String nome, String cognome, String mail) {
		Dipendente d = null;
		for (int i = 0; i < contatoreD; i++) {
			if (nome.toLowerCase().equals(dipendenti[i].getNome())
					&& cognome.toLowerCase().equals(dipendenti[i].getCognome())
					&& mail.toLowerCase().equals(dipendenti[i].getMail())) {
				d = dipendenti[i];
				break;
			}
		}
		return d;
	}

	public void eliDip(String nome, String cognome, String mail) {
		Dipendente dipToDelete = ricercaDip(nome, cognome, mail);
		if (dipToDelete != null) {
			for (int i = 0; i < contatoreD; i++) {
				if (dipendenti[i] == dipToDelete) {
					// Rimuovi il dipendente spostando gli elementi successivi
					for (int j = i; j < contatoreD - 1; j++) {
						dipendenti[j] = dipendenti[j + 1];
					}
					// Rimuovi l'ultimo elemento e decrementa il contatore
					dipendenti[contatoreD - 1] = null;
					contatoreD--;
					System.out.println("Dipendente eliminato: " + nome + " " + cognome);
					return;
				}
			}
		} else {
			System.out.println("Il dipendente " + nome + " " + cognome + " non è presente nella lista.");
		}
	}

	public void eliCli(String nome, String cognome, String mail) {
		ClienteAbbonato cliToDelete = ricercaCli(nome, cognome, mail);
		if (cliToDelete != null) {
			for (int i = 0; i < contatoreCA; i++) {
				if (clientiAbbo[i] == cliToDelete) {
					// Rimuovi il dipendente spostando gli elementi successivi
					for (int j = i; j < contatoreCA - 1; j++) {
						clientiAbbo[j] = clientiAbbo[j + 1];
					}
					// Rimuovi l'ultimo elemento e decrementa il contatore
					clientiAbbo[contatoreCA - 1] = null;
					contatoreCA--;
					System.out.println("Cliente abbonato eliminato: " + nome + " " + cognome);
					return;
				}
			}
		} else {
			System.out.println("Il Cliente abbonato " + nome + " " + cognome + " non è presente nella lista.");
		}
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
			if ((clientiAbbo[i].getCliente().getMail().equals(mail)
					&& clientiAbbo[i].getCliente().getPassword().equals(pass))) {
				t = true;
				break;
			}
		}

		return t;
	}

	public boolean esisteCliMail(String mail) {
		boolean t = false;

		for (int i = 0; i < contatoreCA; i++) {
			if (clientiAbbo[i].getCliente().getMail().equals(mail)) {
				t = true;
				break;
			}
		}

		return t;
	}

	public ClienteAbbonato ricercaCli(String mail, String nome, String cognome) {
		int j = -1;

		for (int i = 0; i < contatoreCA; i++) {
			if (/*
				 * clientiAbbo[i].getCliente().getMail().equals(mail.toLowerCase()) &&
				 */ clientiAbbo[i].getCliente().getNome().equals(nome.toLowerCase())
					&& clientiAbbo[i].getCliente().getCognome().equals(cognome.toLowerCase())) {
				j = i;
				break;
			}
		}

		if (j == -1) {
			return null;
		} else
			return clientiAbbo[j];
	}

	public boolean esisteDip(String mail, String pass) {
		boolean t = false;

		for (int i = 0; i < contatoreD; i++) {
			if ((dipendenti[i].getMail().equals(mail) && dipendenti[i].getPassword().equals(pass))) {
				t = true;
				break;
			}
			if (dipendenti[i] == null) {
				break;
			}
		}

		return t;
	}

	public ClienteAbbonato accessoCli(String mail, String pass) {
		int j = -1;
		for (int i = 0; i < contatoreCA; i++) {
			if (clientiAbbo[i].getCliente().getMail().equals(mail)
					&& clientiAbbo[i].getCliente().getPassword().equals(pass)) {
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

	public void eliminaClienteAbbScaduto(String nome, String cognome, String mail, Richieste r, Corsi c) {
		ClienteAbbonato ca = ricercaCli(mail.toLowerCase(), nome, cognome);
		LocalDate dataOdierna = LocalDate.now();
		ClienteAbboDAO dao = new ClienteAbboDAO();
		RichiesteDAO dao1 = new RichiesteDAO();
		IscrittoalcorsoDAO dao2 = new IscrittoalcorsoDAO();

		if (ca != null && ca.getDataScadenza().isBefore(dataOdierna)) {

			if (c.isclPresente(ca)) {
				dao2.deleteIscrizione2(ca);
				c.eliminaIscrizioniCliente(ca);
			} else if (r.clRichieste(ca)) {
				dao1.deleteRichiesta(r.ricarcaRichiestaCl(ca));
				r.eliminaRichiesta(r.ricarcaRichiestaCl(ca));
			}
			eliCli(nome, cognome, mail);
			dao.deleteCliente(nome, cognome, mail);
		}
	}
}
