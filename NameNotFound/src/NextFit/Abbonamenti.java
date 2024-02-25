package NextFit;

public class Abbonamenti {
	public String tipo;
	public double costo;
	public int durata;

	public Abbonamenti(String tipo, double costo) {
		if (tipo.toLowerCase().equals("mensile")) {
			this.tipo = "mensile";
			this.durata = 1;
		} else if (tipo.toLowerCase().equals("semestrale")) {
			this.tipo = "semestrale";
			this.durata = 6;
		} else if (tipo.toLowerCase().equals("annuale")) {
			this.tipo = "annuale";
			this.durata = 12;
		}

		this.costo = costo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public double getCosto() {
		return costo;
	}

	public void setCosto(double costo) {
		this.costo = costo;
	}

	public void getAbbo() {
		System.out.println("Tipo: " + tipo + " Costo: " + costo);
	}

	public int getDurata() {
		return durata;
	}

	public void setDurata(int durata) {
		this.durata = durata;
	}

}
