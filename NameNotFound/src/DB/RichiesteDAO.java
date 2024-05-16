package DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import NextFit.ClienteAbbonato;
import NextFit.Palestra;
import NextFit.PersonalTrainer;
import NextFit.RichiestaAlPT;
import NextFit.Richieste;

public class RichiesteDAO {

	private String schema;
	private Connection conn;

	public ArrayList<RichiestaAlPT> selectAll(Palestra p, Richieste r) {
		ArrayList<RichiestaAlPT> result = new ArrayList<>();

		DBConnection connessione = new DBConnection();
		connessione.connetti();

		conn = DBConnection.startConnection(conn, schema);

		Statement st1;
		ResultSet rs1;

		try {
			st1 = conn.createStatement();
			String query = "SELECT nome_pt, cognome_pt, mail_pt, nome_cliente, cognome_cliente, mail_cliente, scheda_id FROM richiestept";
			rs1 = st1.executeQuery(query);

			while (rs1.next()) {
				ClienteAbbonato ca = p.ricercaCli(rs1.getString(6), rs1.getString(4), rs1.getString(5));
				PersonalTrainer pt = (PersonalTrainer) p.ricercaDip(rs1.getString(1), rs1.getString(2),
						rs1.getString(3));
				RichiestaAlPT rpt = null;

				if ((rs1.getString(7) == (null))) {
					rpt = r.aggRichiesta(pt, ca, 0);
				} else if ((rs1.getString(7) != (null))) {
					rpt = r.aggRichiesta(pt, ca, Integer.parseInt(rs1.getString(7)));
				}

				result.add(rpt);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		DBConnection.closeConnection(conn);
		return result;
	}

	public boolean insertRichiesta(RichiestaAlPT rpt, int schedaId) {
		conn = DBConnection.startConnection(conn, schema);

		PreparedStatement st1;

		boolean esito = true;

		try {

			String query = "INSERT INTO richiestePT (nome_pt, cognome_pt, mail_pt, nome_cliente, cognome_cliente, mail_cliente, scheda_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
			st1 = conn.prepareStatement(query);
			st1.setString(1, rpt.getDipendente().getNome());
			st1.setString(2, rpt.getDipendente().getCognome());
			st1.setString(3, rpt.getDipendente().getMail());
			st1.setString(4, rpt.getCliente().getCliente().getNome());
			st1.setString(5, rpt.getCliente().getCliente().getCognome());
			st1.setString(6, rpt.getCliente().getCliente().getMail());
			st1.setInt(7, schedaId);

			st1.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			esito = false;
		} finally {
			DBConnection.closeConnection(conn);
		}

		return esito;
	}

	public boolean deleteRichiesta(RichiestaAlPT richiesta) {
		conn = DBConnection.startConnection(conn, schema);

		PreparedStatement st1;

		boolean esito = true;

		try {
			String query = "DELETE FROM richiestePT WHERE nome_pt = ? AND cognome_pt = ? AND mail_pt = ? AND nome_cliente = ? AND cognome_cliente = ? AND mail_cliente = ?";
			st1 = conn.prepareStatement(query);
			st1.setString(1, richiesta.getDipendente().getNome());
			st1.setString(2, richiesta.getDipendente().getCognome());
			st1.setString(3, richiesta.getDipendente().getMail());
			st1.setString(4, richiesta.getCliente().getCliente().getNome());
			st1.setString(5, richiesta.getCliente().getCliente().getCognome());
			st1.setString(6, richiesta.getCliente().getCliente().getMail());

			int rowsAffected = st1.executeUpdate();
			if (rowsAffected == 0) {
				System.out.println("Nessuna riga eliminata. Potrebbe non esistere una richiesta corrispondente.");
			} else {
				System.out.println("Richiesta eliminata con successo.");
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
