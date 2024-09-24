package Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import DB.ClienteAbboDAO;
import DB.DipendenteDAO;
import DB.RichiesteDAO;
import Model.Abbonamenti;
import Model.Cliente;
import Model.ClienteAbbonato;
import Model.Dipendente;
import Model.Palestra;
import Model.PersonalTrainer;
import Model.Richieste;

class SceltaPTTest {

	private Palestra p = new Palestra(20, 20);
	private ClienteAbboDAO cdao = new ClienteAbboDAO();
	private RichiesteDAO rdao = new RichiesteDAO();
	private DipendenteDAO ddao = new DipendenteDAO();
	private Richieste r = new Richieste();

	@Test
	void testscelta() {

		cdao.selectAll(p);
		ddao.selectAll(p);
		rdao.selectAll(p, r);

		ClienteAbbonato c1 = p.accessoCli("marco.blu@example.com", "password789");
		PersonalTrainer pt = (PersonalTrainer) p.accessoDip("antonio.marroni@example.com", "topsecret");

		r.aggRichiesta(pt, c1, 0);

		boolean result = true;

		result = rdao.insertRichiesta(r.ricarcaRichiesta(c1, pt));

		assertTrue(result);
	}

}
