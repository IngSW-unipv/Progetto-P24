package Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import DB.DipendenteDAO;
import DB.IscrittoalcorsoDAO;
import Model.Corsista;
import Model.Dipendente;
import Model.Palestra;

class CreaeElimDipTest {

	private Palestra p =  Palestra.getInstance();
	private DipendenteDAO ddao = new DipendenteDAO();

	@Test
	void testDipRegistration() {

		ddao.selectAll(p);

		Dipendente d1 = new Corsista("Carlo", "Gialli", "cg@cg.com", "cgcg8", 23, 2500, "Corsista");

		boolean result = true;

		result = p.registraDipendente(d1);
		assertTrue(result);
	}

	@Test
	void testDipElim() {

		ddao.selectAll(p);
		
		boolean result = true;

		result = ddao.eliminaDip("Giovanni", "Bianchi", "giovanni.bianchi@example.com", p);
		assertTrue(result);
	}

}
