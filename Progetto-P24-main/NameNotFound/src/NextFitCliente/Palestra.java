package NextFitCliente;

public class Palestra  //classe di tipo pure fabbrication -> pattern factory per la creazione dei dipendenti e clienti
{
	private Cliente[] clienti;
	private Dipendente[] dipendenti;
	private int contatoreC, contatoreD;
	
	public Palestra() {
		clienti = new Cliente[400];
		dipendenti = new Dipendente[40];
		contatoreC = 0;
		contatoreD = 0;
	}

	public void registraCliente(String nome, String cognome, String mail, String password, int età)
	{
		if (contatoreC < 400)
		{
			clienti[contatoreC] = new Cliente(nome, cognome, mail, password, età);
			contatoreC ++;
		}
	}
	
	public void registraDipendente (String nome, String cognome, String mail, String password, int età, double stipendio)
	{
		
	}
}
