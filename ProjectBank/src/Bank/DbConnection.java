//updated on 17-08-21

package Bank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
	private static String url = "jdbc:mysql://localhost:3306/accounts";
	private static String username = "root";
	private static String pwd = "magi";

	private static DbConnection connection = new DbConnection();

	private DbConnection() {
	}

	Connection getConnection() {

		Connection con = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, username, pwd);
		} catch (ClassNotFoundException e) {
			System.out.print("Server not found" + e.getMessage());
		} catch (SQLException e) {
			System.out.print(" Database error !! ");
		} catch (NullPointerException nullPointerException) {
			System.out.print(" Connection is Null");
		}

		return con;
	}

	public static DbConnection getInstance() {
		return connection;
	}
}
