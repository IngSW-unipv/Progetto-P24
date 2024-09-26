package Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import DB.ClienteAbboDAO;
import DB.CorsiDAO;
import DB.DipendenteDAO;
import DB.IscrittoalcorsoDAO;
import Model.ClienteAbbonato;
import Model.Corsi;
import Model.Corso;
import Model.Palestra;

class SceltaCorsoTest {

	private Palestra p =  Palestra.getInstance();
	private ClienteAbboDAO cdao = new ClienteAbboDAO();
	private DipendenteDAO ddao = new DipendenteDAO();
	private CorsiDAO ccdao = new CorsiDAO();
	private Corsi c=new Corsi(20);
	private IscrittoalcorsoDAO idao=new IscrittoalcorsoDAO();
	
	@Test
	void test() {
		
		cdao.selectAll(p);
		ddao.selectAll(p);
		ccdao.selectAll(p, c);
		
		ClienteAbbonato c1 = p.accessoCli("marco.blu@example.com", "password789");
		
		Corso cc = c.getCorso(0);
		boolean result=true;
		
		result=idao.insertIscrizione(c1, cc);
		
		ccdao.upIscritti(cc.getNome());
		
		assertTrue(result);
	}

}
