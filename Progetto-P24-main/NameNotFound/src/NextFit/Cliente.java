package NextFit;

public class Cliente 
{
	private String nome, cognome, mail, password;
	private int età;
	
	public Cliente(String nome, String cognome, String mail, String password, int età) 
	{
		this.nome = nome;
		this.cognome = cognome;
		this.mail = mail;
		this.password = password;
		this.età = età;
	}

	public String getNome() 
	{
		return nome;
	}

	public void setNome(String nome) 
	{
		this.nome = nome;
	}

	public String getCognome() 
	{
		return cognome;
	}

	public void setCognome(String cognome) 
	{
		this.cognome = cognome;
	}

	public String getMail() 
	{
		return mail;
	}

	public void setMail(String mail) 
	{
		this.mail = mail;
	}

	public String getPassword() 
	{
		return password;
	}

	public void setPassword(String password) 
	{
		this.password = password;
	}

	public int getEtà() 
	{
		return età;
	}

	public void setEtà(int età) 
	{
		this.età = età;
	}
	
}
