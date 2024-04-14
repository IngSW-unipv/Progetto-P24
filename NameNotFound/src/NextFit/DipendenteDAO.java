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
	
	public boolean insertDipendente(Dipendente d) {
		conn = DBConnection.startConnection(conn, schema);

		PreparedStatement st1;

		boolean esito = true;

		try {
			String query = "INSERT INTO dipendenti (nome, cognome, mail, eta, password, tipo, stipendio) VALUES (?, ?, ?, ?, ?, ?, ?)";
			st1 = conn.prepareStatement(query);
			st1.setString(1, d.getNome());
			st1.setString(2, d.getCognome());
			st1.setString(3, d.getMail());
			st1.setInt(4, d.getEtà());
			st1.setString(5, d.getPassword());
			st1.setString(6, d.getTipo()); 
			st1.setDouble(7, d.getStipendio());

			st1.executeUpdate(); 

		} catch (Exception e) {
			e.printStackTrace();
			esito = false;
		} finally {
			DBConnection.closeConnection(conn);
		}

		return esito;
	}
}
