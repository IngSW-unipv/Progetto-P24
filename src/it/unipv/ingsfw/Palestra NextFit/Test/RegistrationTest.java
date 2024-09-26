package Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import DB.ClienteAbboDAO;
import Model.Abbonamenti;
import Model.Cliente;
import Model.ClienteAbbonato;
import Model.Palestra;

class RegistrationTest {

	private Palestra p =  Palestra.getInstance();
	private ClienteAbboDAO cdao = new ClienteAbboDAO();

	@Test
	public void testRegistrationTrue() {
		Cliente c1 = new Cliente("Matteo", "Grigio", "Paolo@gmail.com", "password", 22);
		boolean result = true;
		if (p.registraCliente(c1) == true) {
			Abbonamenti a1 = new Abbonamenti("Mensile", 50.0);
			ClienteAbbonato c2 = new ClienteAbbonato(c1, a1);

			cdao.selectAll(p);

			result = cdao.insertCliente(c1, c2);
			assertTrue(result);
		}
	}

	@Test
	public void testRegistrationFalse() {
		Cliente c1 = new Cliente("Matteo", "Grigio", "Matteo@gmail.com", "password", 15);
		boolean result1 = true;
		if (p.registraCliente(c1) == true) {

			Abbonamenti a1 = new Abbonamenti("Mensile", 50.0);
			ClienteAbbonato c2 = new ClienteAbbonato(c1, a1);

			cdao.selectAll(p);

			result1 = cdao.insertCliente(c1, c2);
			assertFalse(result1);
		}
	}

}
