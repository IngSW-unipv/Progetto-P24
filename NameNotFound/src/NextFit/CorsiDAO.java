package NextFit;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class CorsiDAO {

	private String schema;
	private Connection conn;

	public CorsiDAO() {
		this.schema = "palestra";
	}

	public ArrayList<Corsi> selectAll(Palestra p, Corsi c) {
		ArrayList<Corsi> result = new ArrayList<>();

		DBConnection connessione = new DBConnection();
		connessione.connetti();

		conn = DBConnection.startConnection(conn, schema);

		Statement st1;
		ResultSet rs1;

		try {
			st1 = conn.createStatement();
			String query = "SELECT * from Corsi";
			rs1 = st1.executeQuery(query);

			while (rs1.next()) {

				Dipendente d = p.ricercaDip(rs1.getString(2), rs1.getString(3));
				Corso c0 = new Corso(rs1.getString(1), d, Integer.parseInt(rs1.getString(4)),
						Integer.parseInt(rs1.getString(5)));
				c.aggCorsi(c0);

				result.add(c);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		connessione.closeConnection(conn);
		return result;
	}

	public boolean insertCorso(Corso c) {
		conn = DBConnection.startConnection(conn, schema);

		PreparedStatement st1;
		boolean esito = true;

		try {
			if (esisteDIP(c.getNome(), c.getCorsista().getNome(), c.getCorsista().getCognome())) {
				System.out.println("Il dipendente è già presente nel database.");
				return false;
			}
			String query = "INSERT INTO corsi (nome_corso, nome_corsista, cognome_corsista, max_iscritti, iscritti) VALUES (?, ?, ?, ?, ?)";
			st1 = conn.prepareStatement(query);
			st1.setString(1, c.getNome());
			st1.setString(2, c.getCorsista().getNome());
			st1.setString(3, c.getCorsista().getCognome());
			st1.setInt(4, c.getMaxp());
			st1.setInt(5, c.getNp());
			st1.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			esito = false;
		} finally {
			DBConnection.closeConnection(conn);
		}

		return esito;
	}

	private boolean esisteDIP(String nomecorso, String nomed, String cognomed) {
		try {
			String query = "SELECT COUNT(*) FROM corsi WHERE nome_corso = ? AND nome_corsista = ? AND cognome_corsista = ?";
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, nomecorso);
			statement.setString(2, nomed);
			statement.setString(3, cognomed);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				int count = resultSet.getInt(1);
				return count > 0;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
