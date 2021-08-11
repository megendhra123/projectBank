package Bank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Random;

public class AccountRegistration {
	String rAcName;
	String rAcNo;
	Boolean b;
	int gPin;
	DbConnection db=new DbConnection();
	void register1(String rAcName, String rAcNo, int rPinNo) {
		this.rAcName=rAcName;
		this.rAcNo=rAcNo;
	}

	void pinGenerator() {
		Random r=new Random();
		int a=r.nextInt(9);
		int b=a*a*a;
		gPin=a*1000+b;
		
	}
	public void completeregister() {
	Connection con2=db.getConnection();
	PreparedStatement ps;
	if(con2!=null) {
	try {
		ps = con2.prepareStatement("insert into accountDetails2 values (?,?,?,?)");
		ps.setString(1, rAcName);
		ps.setString(2, rAcNo);
		ps.setInt(3, gPin);
		ps.setInt(4, 0);
		ps.executeUpdate();
		b=true;
	} catch (SQLException e) {
		if(e.getErrorCode() == 1062 ){
	     b=false;
	    }
	}catch (InputMismatchException eInMis) {
			System.out.println("Input Mismatched"+eInMis);
			
			
	}
	finally {
		try {
			con2.close();
		} catch (SQLException e) {
			System.out.println("ERROR OCCURED during closing the connection ERROR : "+e);
		}
	}
	}else {
		System.out.println("Cannot Connect to Database Try Again..");
	}
	}
}
