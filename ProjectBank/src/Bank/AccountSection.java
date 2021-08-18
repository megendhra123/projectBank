//updated on 17-08-21

package Bank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AccountSection {
	DbConnection dbConnection;

	AccountInformation accountDetails(String accountNo, int pinNo) {
		AccountInformation accountInformation = new AccountInformation();
		dbConnection = DbConnection.getInstance();
		Connection connection = dbConnection.getConnection();

		if (connection != null) {
			try {
				Statement s = connection.createStatement();
				// selecting records from database by account number and pin number
				ResultSet rs = s.executeQuery(
						"select * from accountDetails2 where AccountNo = " + accountNo + " AND Pin = " + pinNo);
				while (rs.next()) {
					accountInformation.setAccountName(rs.getString(1));
					accountInformation.setAvailableBalance(rs.getInt(4));
				}
			} catch (SQLException e) {

				if (e.getErrorCode() == 1062) {
					System.out.println("Transaction failed");
				}

				System.out.println("ERROR OCCURED : " + e + " ERROR CODE : " + e.getErrorCode());
			} finally {
				if (connection != null) {
					try {
						connection.close(); // try to close the connection
					} catch (SQLException e) {
						System.out.println("ERROR OCCURED during closing the connection ERROR : " + e.getMessage());
					}
				}
			}
		} else {
			System.out.println("Connection is null");
		}
		return accountInformation;
	}

	void deposit(int depositAmount, String accountNo) {
		dbConnection = DbConnection.getInstance();
		Connection connection = dbConnection.getConnection();
		if (connection != null) {
			int availableBalance = 0;
			try {
				Statement s = connection.createStatement();
				ResultSet rs = s.executeQuery("select * from accountDetails2 where AccountNo = " + accountNo);
				while (rs.next()) {
					availableBalance = rs.getInt(4); // getting available balance from database
					availableBalance += depositAmount; // adding deposit amount into available balance
				}
				PreparedStatement ps = connection.prepareStatement(
						"update accountDetails2 set Depoist = " + availableBalance + " where AccountNo = " + accountNo);
				ps.executeUpdate();
				System.out.println("Amount deposit successfully");
				System.out.println("Deposit amount : " + depositAmount + "  Available Balance : " + availableBalance);
			} catch (SQLException e) {
				System.out.println("ERROR OCCURED : " + e.getMessage());
			} finally {
				if (connection != null) {
					try {
						connection.close(); // try to close the connection
					} catch (SQLException e) {
						System.out.println("ERROR OCCURED : " + e.getMessage());
					}
				}
			}
		} else {
			System.out.println("Connection is null");
		}
	}

	void withdraw(int withdrawAmount, String accountNo) {
		dbConnection = DbConnection.getInstance();
		Connection connection = dbConnection.getConnection();
		if (connection != null) {

			int availableBalance = 0;
			try {
				Statement s = connection.createStatement();
				ResultSet rs = s.executeQuery("select * from accountDetails2 where AccountNo = " + accountNo);
				while (rs.next()) {
					availableBalance = rs.getInt(4); // getting available balance from database
				}
				if (withdrawAmount < availableBalance) { // if withdraw amount greater than available balance
					availableBalance -= withdrawAmount; // than minus withdraw amount from available balance
					PreparedStatement ps = connection.prepareStatement("update accountDetails2 set Depoist = "
							+ availableBalance + " where AccountNo = " + accountNo);
					ps.executeUpdate();

					System.out.println("Amount withdraw successfully");
					System.out.println(
							"withdraw amount : " + withdrawAmount + "  Available Balance : " + availableBalance);
				} else {
					System.out.println("You have in sufficent amount to withdraw");
				}
			} catch (SQLException e) {
				System.out.println("ERROR OCCURED : " + e.getMessage());
			} finally {
				if (connection != null) {
					try {
						connection.close(); // try to close the connection
					} catch (SQLException e) {
						System.out.println("ERROR OCCURED during closing the connection ERROR : " + e.getMessage());
					}
				}
			}
		} else {
			System.out.println("Connection is null");
		}
	}
}
