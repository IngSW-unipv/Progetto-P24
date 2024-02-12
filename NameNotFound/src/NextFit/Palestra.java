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

	public void registraCliente(String nome, String cognome, String mail, String password, int età)
	{
		for (int i = 0; i < maxC; i++)
		{
			if (nome.equals(clienti[i].getNome()) && cognome.equals(clienti[i].getCognome()) && mail.equals(clienti[i].getMail())) //cotrollo su nome, cognome e mail che sono la chiave nel database 
			{
				System.out.println("Il cliente è già presente nel database. ");
			}
			else if (contatoreC < maxC && età > 17)
			{
				clienti[contatoreC] = new Cliente(nome, cognome, mail, password, età);
				contatoreC ++;
			}
			else if (età < 18)
				System.out.println("La palestra non può iscrivere minorenni. ");
			else
				System.out.println("La palestra non può iscrivere più di "+ maxC +" clienti. ");
		}
	}
	
	public void registraDipendente (String nome, String cognome, String mail, String password, int età, double stipendio, String tipo)
	{
		for (int i = 0; i < maxC; i++)
		{
			if (nome.equals(dipendenti[i].getNome()) && cognome.equals(dipendenti[i].getCognome()) && mail.equals(dipendenti[i].getMail())) //cotrollo su nome, cognome e mail che sono la chiave nel database 
			{
				System.out.println("Il cliente è già presente nel database. ");
			}
			else if (tipo.equals("PersonalTrainer"))
			{
				if (contatoreD < maxD)
				{
					dipendenti[contatoreD] = new PersonalTrainer(nome, cognome, mail, password, età, stipendio);
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
	}
}
