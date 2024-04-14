package NextFit;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class DipendenteDAO {

	private String schema;
	private Connection conn;

	public DipendenteDAO() {
		this.schema = "palestra";
	}

	public ArrayList<Dipendente> selectAll(Palestra pa) {
		ArrayList<Dipendente> result = new ArrayList<>();

		DBConnection connessione = new DBConnection();
		connessione.connetti();

		conn = DBConnection.startConnection(conn, schema);

		Statement st1;
		ResultSet rs1;

		try {
			st1 = conn.createStatement();
			String query = "SELECT * from dipendenti";
			rs1 = st1.executeQuery(query);

			while (rs1.next()) {

				pa.registraDipendente(pa.creaDipendente(rs1.getString(1), rs1.getString(2), rs1.getString(3), rs1.getString(5),
						Integer.parseInt(rs1.getString(4)), Double.parseDouble(rs1.getString(7)), rs1.getString(6)));
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		connessione.closeConnection(conn);
		return result;
	}
}
