//updated on 17-08-21

package Bank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

public class AccountRegistration {

	// getting register account name and account number
	void register(String registerAccountName, String registerAccountNo, int registerPinNo) {

		DbConnection dbConnection = DbConnection.getInstance();
		Connection connection = dbConnection.getConnection();
		if (connection != null) {
			try {
				PreparedStatement ps = connection.prepareStatement("insert into accountDetails2 values (?,?,?,?)");
				ps.setString(1, registerAccountName);
				ps.setString(2, registerAccountNo);
				ps.setInt(3, registerPinNo);
				ps.setInt(4, 0);
				ps.executeUpdate();
				System.out.println("Account registration completed. Login to continue");
			} catch (SQLException e) {
				if (e.getErrorCode() == 1062) {
					System.out.println("The Account is already exists");
				}
				System.out.println("ERROR : " + e.getErrorCode());
			} finally {
				if (connection != null) {
					try {
						connection.close(); // try to close the connection
					} catch (SQLException e) {
						System.out.println("ERROR OCCURED during closing the connection ERROR : " + e);
					} catch (NullPointerException nullExp) {
						System.out.println("Connection is null " + nullExp.getMessage());
					}
				}
			}
		} else {
			System.out.println("Connection is null");
		}
	}

	// in pinGenerator method will generate 4 digit random pin
	int pinGenerator() {
		Random r = new Random();
		int a = r.nextInt(9);
		int b = a * a * a + 1;
		int generatePin = (1000 + b) * a;
		return generatePin;
	}
}
