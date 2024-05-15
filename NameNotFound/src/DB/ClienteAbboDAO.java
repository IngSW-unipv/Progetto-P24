package DB;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

import NextFit.Abbonamenti;
import NextFit.Cliente;
import NextFit.ClienteAbbonato;
import NextFit.Palestra;

public class ClienteAbboDAO {

	private String schema;
	private Connection conn;

	public ClienteAbboDAO() {
		this.schema = "palestra";
	}

	public ArrayList<ClienteAbbonato> selectAll(Palestra p) {
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

				p.abbonaCl(c1);

				result.add(c1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		connessione.closeConnection(conn);
		return result;
	}

	public boolean insertCliente(Cliente c, ClienteAbbonato ca) {
		conn = DBConnection.startConnection(conn, schema);

		PreparedStatement st1;

		boolean esito = true;

		try {
			String query = "INSERT INTO clienti (nome, cognome, mail, eta, password, scad_abb) VALUES (?, ?, ?, ?, ?, ?)";
			st1 = conn.prepareStatement(query);
			st1.setString(1, c.getNome());
			st1.setString(2, c.getCognome());
			st1.setString(3, c.getMail());
			st1.setInt(4, c.getEtà());
			st1.setString(5, c.getPassword());
			st1.setDate(6, java.sql.Date.valueOf(ca.getDataScadenza()));

			st1.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			esito = false;
		} finally {
			DBConnection.closeConnection(conn);
		}

		return esito;
	}

	public boolean updateScadenzaAbbonamento(String nome, String cognome, String mail, LocalDate nuovaData) {
		conn = DBConnection.startConnection(conn, schema);

		PreparedStatement stmt = null;
		boolean esito = true;

		try {
			String query = "UPDATE clienti SET scad_abb = ? WHERE nome = ? AND cognome = ? AND mail = ?";
			stmt = conn.prepareStatement(query);
			stmt.setDate(1, java.sql.Date.valueOf(nuovaData));
			stmt.setString(2, nome);
			stmt.setString(3, cognome);
			stmt.setString(4, mail);

			int rowsUpdated = stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			esito = false;
		} finally {
			DBConnection.closeConnection(conn);
		}

		return esito;
	}

}
