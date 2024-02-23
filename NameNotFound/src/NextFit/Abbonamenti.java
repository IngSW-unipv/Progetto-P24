package NextFit;


import java.time.LocalDate;

public class Abbonamenti 
{
	public String tipo;
	public double costo;
	public int durata;
	public LocalDate dataScad; 
	
	public Abbonamenti(String tipo, int durata, double costo, LocalDate dataScad)
	{
		if (tipo.toLowerCase().equals("mensile"))
			this.tipo = "mensile";
		else if (tipo.toLowerCase().equals("semestrale"))
			this.tipo = "semestrale";
		else if (tipo.toLowerCase().equals("annuale"))
			this.tipo = "annuale";
		
		this.durata = durata;
		this.costo = costo;
		this.dataScad = dataScad; 
	}

	public String getTipo() 
	{
		return tipo;
	}

	public void setTipo(String tipo) 
	{
		this.tipo = tipo;
	}

	public double getCosto() 
	{
		return costo;
	}

	public void setCosto(double costo, String tipo)
	{
		this.costo = costo;
	}
	
	public void getAbbo()
	{
		System.out.println("Tipo: " + tipo + " Costo: " + costo + "€ Scadenza: " + dataScad);
	}

	public int getDurata() 
	{
		return durata;
	}

	public void setDurata(int durata) 
	{
		this.durata = durata;
	}
	
}