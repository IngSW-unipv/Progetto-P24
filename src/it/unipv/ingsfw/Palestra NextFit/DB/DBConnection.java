package DB;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {

	private static String username;
	private static String password;
	private static String dbURL;
	private static DBConnection conn;

	public Connection connetti() {
		Connection conn = null;

		dbURL = "jdbc:mysql://localhost/palestra";
		username = "root";

		password = "@Riccardo18"; // @Riccardo18 //Giubeagio3

		try {
			conn = DriverManager.getConnection(dbURL, username, password);
			if (conn != null) {
				System.out.println("connessione partita");
			} else {
				System.out.println("connessione non partita");
			}
		} catch (SQLException e) {
			System.out.println("problema " + e.getMessage());
		}
		return conn;
	}

	public static Connection startConnection(Connection conn, String schema) {
		if (isOpen(conn))
			closeConnection(conn);

		try {

			dbURL = String.format(dbURL, schema);
			conn = DriverManager.getConnection(dbURL, username, password);// Apertura connessione

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return conn;
	}

	public static boolean isOpen(Connection conn) {
		if (conn == null)
			return false;
		else
			return true;
	}

	public static Connection closeConnection(Connection conn) {
		if (!isOpen(conn))
			return null;
		try {

			conn.close();
			conn = null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return conn;
	}

}
