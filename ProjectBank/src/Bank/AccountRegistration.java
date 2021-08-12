package Bank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Random;

public class AccountRegistration {
	String regAccountName;
	String regAccountNo;
	Boolean isAccRegistered;
	int generatePin;
	DbConnection db=new DbConnection();
	void register1(String regAccountName, String regAccountNo, int rPinNo) {
		this.regAccountName=regAccountName;
		this.regAccountNo=regAccountNo;
	}

	void pinGenerator() {
		Random r=new Random();
		int a=r.nextInt(9);
		int b=a*a*a;
		generatePin=a*1000+b;
		
	}
	public void completeregister() {
	Connection con2=db.getConnection();
	PreparedStatement ps;
	if(regAccountName!=null && regAccountNo!=null && generatePin!=0) {
	if(con2!=null) {
	try {
		ps = con2.prepareStatement("insert into accountDetails2 values (?,?,?,?)");
		ps.setString(1, regAccountName);
		ps.setString(2, regAccountNo);
		ps.setInt(3, generatePin);
		ps.setInt(4, 0);
		ps.executeUpdate();
		isAccRegistered=true;
	} catch (SQLException e) {
		if(e.getErrorCode() == 1062 ){
	     isAccRegistered=false;
	    }
	}catch (InputMismatchException eInMis) {
			System.out.println("Input Mismatched"+eInMis);	
	}
	finally {
		try {
			con2.close();
		} catch (SQLException e) {
			System.out.println("ERROR OCCURED during closing the connection ERROR : "+e);
		}catch (NullPointerException nullExp) {
			System.out.println("Connection Cannot be closed because connection is null "+nullExp.getMessage());
		}
	}
	}else {
		System.out.println("Cannot Connect to Database Try Again..");
	}
	}else {
		System.out.println("Registration Values are null");
	}
	}
}
