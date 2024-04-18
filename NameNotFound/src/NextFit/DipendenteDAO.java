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
	        // Check if the employee already exists in the database
	        if (esisteDIP(d.getNome(), d.getCognome(), d.getMail())) {
	            System.out.println("Il dipendente è già presente nel database.");
	            return false;
	        }

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

	private boolean esisteDIP(String nome, String cognome, String email) {
	    try {
	        String query = "SELECT COUNT(*) FROM dipendenti WHERE nome = ? AND cognome = ? AND mail = ?";
	        PreparedStatement statement = conn.prepareStatement(query);
	        statement.setString(1, nome);
	        statement.setString(2, cognome);
	        statement.setString(3, email);
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
