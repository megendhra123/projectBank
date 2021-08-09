package Bank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
  static String url="jdbc:mysql://localhost:3306/accounts";
  static String username="root";
  static String pwd="magi";
  
  Connection getConnection() {
	  Connection con = null;
	  try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		 con=DriverManager.getConnection(url, username, pwd);
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return con;
  }
}