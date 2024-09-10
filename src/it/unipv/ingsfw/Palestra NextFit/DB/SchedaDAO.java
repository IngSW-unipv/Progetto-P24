package DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import NextFit.Esercizio;
import NextFit.Richieste;
import NextFit.Scheda;

public class SchedaDAO {

	private String schema;
	private Connection conn;

	public ArrayList<Scheda> selectAll(Richieste r) {
		ArrayList<Scheda> result = new ArrayList<>();

		DBConnection connessione = new DBConnection();
		connessione.connetti();

		conn = DBConnection.startConnection(conn, schema);

		Statement st1;
		ResultSet rs1;

		try {
			st1 = conn.createStatement();
			String query = "SELECT * FROM palestra.schede";
			rs1 = st1.executeQuery(query);

			while (rs1.next()) {

				Scheda sc = new Scheda(Integer.parseInt(rs1.getString(1)));
				for (int i = 2; i <= 6; i++) {
					Esercizio es = new Esercizio(rs1.getString(i));
					sc.aggiungiEsercizio(es);
				}
				r.aggScheda(sc);

				result.add(sc);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		DBConnection.closeConnection(conn);
		return result;
	}

	public void insert(Scheda scheda) {
		DBConnection connessione = new DBConnection();
		connessione.connetti();

		conn = DBConnection.startConnection(conn, schema);
		PreparedStatement st1;

		try {

			String query = "INSERT INTO palestra.schede (id_scheda, esercizio1, esercizio2, esercizio3, esercizio4, esercizio5) VALUES (?, ?, ?, ?, ?, ?)";

			st1 = conn.prepareStatement(query);
			st1.setInt(1, scheda.getCod_Scheda());
			st1.setString(2, scheda.getNomeEsercizio(0));
			st1.setString(3, scheda.getNomeEsercizio(1));
			st1.setString(4, scheda.getNomeEsercizio(2));
			st1.setString(5, scheda.getNomeEsercizio(3));
			st1.setString(6, scheda.getNomeEsercizio(4));

			st1.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

		DBConnection.closeConnection(conn);
	}

	public void delete(int id_scheda) {
		DBConnection connessione = new DBConnection();
		connessione.connetti();

		conn = DBConnection.startConnection(conn, schema);

		String query = "DELETE FROM palestra.schede WHERE id_scheda = ?";

		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, id_scheda);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

		DBConnection.closeConnection(conn);
	}

}