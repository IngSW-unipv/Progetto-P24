package NextFitCliente;

public abstract class Dipendente 
{
	protected String nome, cognome, mail, password;
	protected int età;
	protected double stipendio;
	
	public Dipendente(String nome, String cognome, String mail, String password, int età, double stipendio) 
	{
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.mail = mail;
		this.password = password;
		this.età = età;
		this.stipendio = stipendio;
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

	public double getStipendio() 
	{
		return stipendio;
	}

	public void setStipendio(double stipendio) 
	{
		this.stipendio = stipendio;
	}
	
	
	
}
