package NextFit;

public class CreaClAbbo 
{
	private ClienteAbbonato clientiAbbo[];
	private int contatoreCA, maxCA;
	
	public CreaClAbbo (Palestra p) 
	{
		this.maxCA = p.getMaxC();
		clientiAbbo = new ClienteAbbonato[maxCA];
		contatoreCA = 0;
	}
	
	public void abbonaCl(ClienteAbbonato ca)
	{
		if (contatoreCA < maxCA)
		{
			clientiAbbo[contatoreCA] = ca;
			contatoreCA++;
		}
		else
		{
			System.out.println("Non possono essere abbonati più di " + maxCA + " clienti. ");
		}
	}
	
}
