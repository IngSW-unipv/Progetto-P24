package NextFit;

public class Proprietario {
	private static Proprietario instance;
	private Abbonamenti abbonamentoMensile;
	private Abbonamenti abbonamentoSemestrale;
	private Abbonamenti abbonamentoAnnuale;
	private String mail, password;

	private Proprietario() {
		this.abbonamentoMensile = new Abbonamenti("Mensile", 50.0);
		this.abbonamentoSemestrale = new Abbonamenti("Semestrale", 250.0);
		this.abbonamentoAnnuale = new Abbonamenti("Annuale", 450.0);
		this.mail = "proprietario@example.com";
		this.password = "IlProp";
	}

	public static Proprietario getInstance() {
		if (instance == null) {
			instance = new Proprietario();
		}
		return instance;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Abbonamenti getAbbonamentoMensile() {
		return abbonamentoMensile;
	}

	public void setAbbonamentoMensile(Abbonamenti abbonamentoMensile) {
		this.abbonamentoMensile = abbonamentoMensile;
	}

	public Abbonamenti getAbbonamentoSemestrale() {
		return abbonamentoSemestrale;
	}

	public void setAbbonamentoSemestrale(Abbonamenti abbonamentoSemestrale) {
		this.abbonamentoSemestrale = abbonamentoSemestrale;
	}

	public Abbonamenti getAbbonamentoAnnuale() {
		return abbonamentoAnnuale;
	}

	public void setAbbonamentoAnnuale(Abbonamenti abbonamentoAnnuale) {
		this.abbonamentoAnnuale = abbonamentoAnnuale;
	}

	public void modificaCosto(String tipo, double costo) {
		if (tipo.toLowerCase().equals("mensile")) {
			abbonamentoMensile.setCosto(costo);
		} else if (tipo.toLowerCase().equals("semestrale")) {
			abbonamentoSemestrale.setCosto(costo);
		} else if (tipo.toLowerCase().equals("annuale")) {
			abbonamentoAnnuale.setCosto(costo);
		}
	}
}
