package Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import DB.CorsiDAO;
import DB.DipendenteDAO;

import Model.Corsi;
import Model.Corsista;
import Model.Corso;
import Model.Palestra;


class CreaeElimCorsoTest {

	private Palestra p =  Palestra.getInstance();
	private CorsiDAO cdao = new CorsiDAO();
	private Corsi c = new Corsi(20);
	private DipendenteDAO ddao = new DipendenteDAO();

	@Test
	void testCreaCorso() {

		ddao.selectAll(p);
		cdao.selectAll(p, c);

		Corsista cc = (Corsista) p.ricercaCorsista("Chiara", "Neri", "chiara.neri@example.com");

		Corso c1 = new Corso("Atletica", cc, 10, 0);
		boolean result = true;

		result = c.aggCorsi(c1);
		assertTrue(result);
	}

	@Test
	void testElimCorso() {

		ddao.selectAll(p);
		cdao.selectAll(p, c);

		Corsista cc = (Corsista) p.ricercaCorsista("Maria", "Rossi", "maria.rossi@example.com");

		boolean result = true;

		result = cdao.deleteCorso("Yoga", cc.getNome(), cc.getCognome(), cc.getMail(), c, p);
		assertTrue(result);
	}

}
