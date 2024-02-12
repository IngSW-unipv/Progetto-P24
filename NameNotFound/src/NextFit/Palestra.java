package NextFit;

public class Palestra  //classe di tipo pure fabbrication -> pattern factory per la creazione dei dipendenti e clienti
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

	public void registraCliente(Cliente cliente)
	{
		if (contatoreC < maxC && cliente.getEtà() > 17)
			{
				clienti[contatoreC] = cliente;
				contatoreC ++;
			}
			else if (cliente.getEtà() < 18)
				System.out.println("La palestra non può iscrivere minorenni. ");
			else
				System.out.println("La palestra non può iscrivere più di "+ maxC +" clienti. ");
		
		for (int i = 0; i < contatoreC - 1; i++)
			{
				if (cliente.getNome().equals(clienti[i].getNome()) && cliente.getCognome().equals(clienti[i].getCognome()) && cliente.getMail().equals(clienti[i].getMail())) //cotrollo su nome, cognome e mail che sono la chiave nel database 
				{
					System.out.println("Il cliente è già presente nel database. ");
					contatoreC --; //alla nuova iscrizione verrà sovrascritto il cliente nuovo su quello già presente
				}
			}
	}
	
	/*public void registraDipendente (Dipendente dipendente, String tipo)
	{
		for (int i = 0; i < maxC; i++)
		{
			if (dipendente.getNome().equals(dipendenti[i].getNome()) && dipendente.getCognome().equals(dipendenti[i].getCognome()) && dipendente.getMail().equals(dipendenti[i].getMail())) //cotrollo su nome, cognome e mail che sono la chiave nel database 
			{
				System.out.println("Il cliente è già presente nel database. ");
			}
			else if (tipo.equals("PersonalTrainer"))
			{
				if (contatoreD < maxD)
				{
					dipendenti[contatoreD] = new PersonalTrainer(dipendente); !!!!!!!
					contatoreD ++;
				}
				else
					System.out.println("La palestra non può iscrivere più di "+ maxD +" dipendenti. ");
			}
			else if (tipo.equals("Corsista"))
			{
				if (contatoreD < maxD)
				{
					dipendenti[contatoreD] = new Corsista(nome, cognome, mail, password, età, stipendio);
					contatoreD ++;
				}
				else
					System.out.println("La palestra non può iscrivere più di "+ maxD +" dipendenti. ");
			}
			else if (tipo.equals("Fisioterapista"))
			{
				if (contatoreD < maxD)
				{
					dipendenti[contatoreD] = new Fisioterapista(nome, cognome, mail, password, età, stipendio);
					contatoreD ++;
				}
				else
					System.out.println("La palestra non può iscrivere più di "+ maxD +" dipendenti. ");
			}
			else if (tipo.equals("Dietista"))
			{
				if (contatoreD < maxD)
				{
					dipendenti[contatoreD] = new Dietista(nome, cognome, mail, password, età, stipendio);
					contatoreD ++;
				}
				else
					System.out.println("La palestra non può iscrivere più di "+ maxD +" dipendenti. ");
			}
			else if (tipo.equals("Istruttore di sala"))
			{
				if (contatoreD < maxD)
				{
					dipendenti[contatoreD] = new IstruttorediSala(nome, cognome, mail, password, età, stipendio);
					contatoreD ++;
				}
				else
					System.out.println("La palestra non può iscrivere più di "+ maxD +" dipendenti. ");
			}
		}
	}*/
}
