package DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import NextFit.Abbonamenti;
import NextFit.Cliente;
import NextFit.ClienteAbbonato;
import NextFit.Corsi;
import NextFit.Corsista;
import NextFit.Corso;
import NextFit.Palestra;

public class IscrittoalcorsoDAO {

	private String schema;
	private Connection conn;

	public IscrittoalcorsoDAO() {
		this.schema = "palestra";
	}

	public ArrayList<ClienteAbbonato> selectAll(Palestra p, Corsi c) {
		ArrayList<ClienteAbbonato> result = new ArrayList<>();

		DBConnection connessione = new DBConnection();
		connessione.connetti();

		conn = DBConnection.startConnection(conn, schema);

		Statement st1;
		ResultSet rs1;

		try {
			st1 = conn.createStatement();
			String query = "SELECT cliente_nome, cliente_cognome, cliente_mail, corso_nome, corso_istruttore_nome, corso_istruttore_cognome FROM iscrizioni_corsi";
			rs1 = st1.executeQuery(query);

			while (rs1.next()) {
				ClienteAbbonato ca = p.ricercaCli(rs1.getString(3), rs1.getString(1), rs1.getString(2));
				Corsista cr = (Corsista) p.ricercaCorsista(rs1.getString(5), rs1.getString(6));
				Corso co = c.ricercaCorso(rs1.getString(4), cr);

				c.iscalCorso(ca, co);
				
				result.add(ca);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		connessione.closeConnection(conn);
		return result;
	}

	public boolean insertIscrizione(ClienteAbbonato ca, Corso co) {
		conn = DBConnection.startConnection(conn, schema);

		PreparedStatement st1;

		boolean esito = true;

		try {

			String query = "INSERT INTO iscrizioni_corsi (cliente_nome, cliente_cognome, cliente_mail, corso_nome, corso_istruttore_nome, corso_istruttore_cognome) VALUES (?, ?, ?, ?, ?, ?)";
			st1 = conn.prepareStatement(query);
			st1.setString(1, ca.getCliente().getNome());
			st1.setString(2, ca.getCliente().getCognome());
			st1.setString(3, ca.getCliente().getMail());
			st1.setString(4, co.getNome());
			st1.setString(5, co.getCorsista().getNome());
			st1.setString(6, co.getCorsista().getCognome());

			st1.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			esito = false;
		} finally {
			DBConnection.closeConnection(conn);
		}

		return esito;
	}

	public boolean deleteIscrizione(ClienteAbbonato ca, Corso co) {
		conn = DBConnection.startConnection(conn, schema);

		PreparedStatement st1;

		boolean esito = true;

		try {
			String query = "DELETE FROM iscrizioni_corsi WHERE cliente_nome = ? AND cliente_cognome = ? AND cliente_mail = ? AND corso_nome = ? AND corso_istruttore_nome = ? AND corso_istruttore_cognome = ?";
			st1 = conn.prepareStatement(query);
			st1.setString(1, ca.getCliente().getNome());
			st1.setString(2, ca.getCliente().getCognome());
			st1.setString(3, ca.getCliente().getMail());
			st1.setString(4, co.getNome());
			st1.setString(5, co.getCorsista().getNome());
			st1.setString(6, co.getCorsista().getCognome());

			int rowsAffected = st1.executeUpdate();
			if (rowsAffected == 0) {
				System.out.println("Nessuna riga eliminata. Potrebbe non esistere un'iscrizione corrispondente.");
			} else {
				System.out.println("Iscrizione eliminata con successo.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			esito = false;
		} finally {
			DBConnection.closeConnection(conn);
		}

		return esito;
	}

}
