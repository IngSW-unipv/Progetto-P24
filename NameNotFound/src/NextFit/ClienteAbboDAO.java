package NextFit;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class ClienteAbboDAO {

	private String schema = "palestra";
	private Connection conn;

	public ClienteAbboDAO() {
		this.schema = "palestra";
	}

	public ArrayList<ClienteAbbonato> selectAll(CreaClAbbo clabbo) {
		ArrayList<ClienteAbbonato> result = new ArrayList<>();

		DBConnection connessione = new DBConnection();
		connessione.connetti();
		
		conn = DBConnection.startConnection(conn, schema);

		Statement st1;
		ResultSet rs1;

		try {
			st1 = conn.createStatement();
			String query = "SELECT * from Clienti";
			rs1 = st1.executeQuery(query);

			while (rs1.next()) {

				Cliente c0 = new Cliente(rs1.getString(1), rs1.getString(2), rs1.getString(3), rs1.getString(5),
						Integer.parseInt(rs1.getString(4)));
				Abbonamenti a = new Abbonamenti("mensile", 0);
				ClienteAbbonato c1 = new ClienteAbbonato(c0, a);
				c1.setDataScadenza(LocalDate.parse(rs1.getString(6)));
				// problema del creaclienteabbo
				clabbo.abbonaCl(c1);

				result.add(c1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		connessione.closeConnection(conn);
		return result;
	}
}
