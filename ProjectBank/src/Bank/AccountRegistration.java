//updated on 17-08-21

package Bank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Random;

public class AccountRegistration {
	Boolean isAccountRegistered;
	DbConnection dbConnection;
 
	
	//getting register account name and account number
	void register(String regAccountName, String regAccountNo, int rPinNo) {
		
		dbConnection=DbConnection.getInstance();
		Connection connection=dbConnection.getConnection();
		PreparedStatement ps;
		
		if(regAccountName!=null && regAccountNo!=null && rPinNo!=0) {  //checking accountName and accountNumber not null and pin not 0
		if(connection!=null) {
			
		try {
			ps = connection.prepareStatement("insert into accountDetails2 values (?,?,?,?)");
			ps.setString(1, regAccountName);
			ps.setString(2, regAccountNo);
			ps.setInt(3, rPinNo);
			ps.setInt(4, 0);
			ps.executeUpdate();
			isAccountRegistered=true;
		} 
		catch (SQLException e) {
			if(e.getErrorCode() == 1062 ){
		     isAccountRegistered=false;
		    }
			System.out.println("ERROR : "+e.getErrorCode());
		}
		finally {
			if(connection!=null) {
			try {
				connection.close();          //try to close the connection
			} catch (SQLException e) {
				System.out.println("ERROR OCCURED during closing the connection ERROR : "+e);
			}catch (NullPointerException nullExp) {
				System.out.println("Connection is null "+nullExp.getMessage());
			}
		}
		}
		}
		else {
			System.out.println("Cannot Connect to Database Try Again..");
		}
		}
		else {
			System.out.println("Registration Values are null");
		}
	}
	
    //in pinGenerator method will generate 4 digit random pin 
	int pinGenerator() {
		Random r=new Random();
		int a=r.nextInt(9);
		int b=a*a*a+1;
		int generatePin=(1000+b)*a;
		return generatePin;
	}
}
