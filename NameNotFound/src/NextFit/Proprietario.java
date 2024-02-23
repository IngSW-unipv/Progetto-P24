package NextFit;


import java.time.LocalDate;

public class Proprietario 
{
	public void creaAbbonamento(String tipo, double costo) //voglio cambiare tutto voglio che il proprietario crea i tre tipi di abbo con i costi e che poi quando l'utente sceglie la tipo gli venga restituita la data di scadenza
	{
		LocalDate dataIn = LocalDate.now();
		LocalDate dataScad;
		
        switch (tipo.toLowerCase()) 
        {
            case "mensile":
            	dataScad = dataIn.plusMonths(1);
            	Abbonamenti a = new Abbonamenti("Mensile", 1, costo, dataScad);
                a.getAbbo();
            case "semestrale":
            	dataScad = dataIn.plusMonths(6);
            	Abbonamenti a1 = new Abbonamenti("Semestrale", 6, costo, dataScad);
                a1.getAbbo();
            case "annuale":
            	dataScad = dataIn.plusMonths(12);
            	Abbonamenti a2 = new Abbonamenti("Annuale", 12, costo, dataScad);
                a2.getAbbo();
            default:
                throw new IllegalArgumentException("Tipo di abbonamento non valido");
        }
	}
}
