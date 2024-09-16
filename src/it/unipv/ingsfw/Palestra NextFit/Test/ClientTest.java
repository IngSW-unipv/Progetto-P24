package Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import DB.ClienteAbboDAO;
import NextFit.Abbonamenti;
import NextFit.Cliente;
import NextFit.ClienteAbbonato;
import NextFit.Palestra;

class ClientTest {

	private Palestra p = new Palestra(20, 20);
	private ClienteAbboDAO cdao=new ClienteAbboDAO();

	@Test
	public void testRegistrationTrue() {
		Cliente c1 = new Cliente("Matteo", "Grigio", "Paolo@gmail.com", "password", 22);
		boolean result = true;
		result=p.registraCliente(c1);
		
		Abbonamenti a1=new Abbonamenti("Mensile",50.0);
		ClienteAbbonato c2= new ClienteAbbonato(c1,a1);

		cdao.insertCliente(c1, c2);
		assertTrue(result);
	}
	@Test
	public void testRegistrationFalse() {
		Cliente c1 = new Cliente("Matteo", "Grigio", "Matteo@gmail.com", "password", 15);
		boolean result1 = true;
		result1=p.registraCliente(c1);
		
		Abbonamenti a1=new Abbonamenti("Mensile",50.0);
		ClienteAbbonato c2= new ClienteAbbonato(c1,a1);

		cdao.insertCliente(c1, c2);
		assertFalse(result1);
	}

}
