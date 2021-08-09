package Bank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

public class AccountRegistration {
	String rAcName;
	String rAcNo;
	Boolean b=false;
	int gPin;
	DbConnection db=new DbConnection();
	void register1(String rAcName, String rAcNo, int rPinNo) {
		this.rAcName=rAcName;
		this.rAcNo=rAcNo;
	}
	void registerValidate(){
	  Connection con1=db.getConnection();
	  Statement s;
	try {
		s = con1.createStatement();
		 ResultSet rs=s.executeQuery("select * from accountDetails2 where AccountNo = "
                 +rAcNo+" AND PinNo = "+gPin);
		b=rs.next();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 
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
	try {
		ps = con2.prepareStatement("insert into accountDetails2 values (?,?,?,?)");
		ps.setString(1, rAcName);
		ps.setString(2, rAcNo);
		ps.setInt(3, gPin);
		ps.setInt(4, 0);
		ps.executeUpdate();
		con2.close();
		System.out.println("Registration Completed");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

		
	}
}
