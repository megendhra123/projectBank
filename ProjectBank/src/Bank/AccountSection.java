package Bank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AccountSection {
	DbConnection db=new DbConnection();
	AccountInformation acInfo=new AccountInformation();
	
	String acNo;
	String name;
	int pinNo;
	int avalbal;
	
	void accountDetails(String acNo,int pinNo){
		
		this.acNo=acNo;
		this.pinNo=pinNo;
		
	    Connection con1=db.getConnection();
	    Statement s;
	try {
		s=con1.createStatement();
		ResultSet rs=s.executeQuery("select * from accountDetails2 where AccountNo = "+acNo+" AND Pin = "+pinNo);
		while(rs.next()) {
			acInfo.setAcName(rs.getString(1));
			acInfo.setAvalbal(rs.getInt(4));
		}
		con1.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	}
	
	void showDetails() {
		accountDetails(acNo, pinNo);
		this.name=acInfo.getAcName();
		this.avalbal=acInfo.getAvalbal();
		System.out.println("Name :"+name);
		System.out.println("Available balance :"+avalbal);
		
	}
	void depoistSection(int depAmt) {
		int totAmt=depAmt+avalbal;
		Connection con2=db.getConnection();
		PreparedStatement ps;
		try {
			ps = con2.prepareStatement("update accountDetails2 set Depoist = "+totAmt+
					  " where AccountNo = "+acNo);
			ps.executeUpdate();
			con2.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	void withSection(int witAmt) {
		if(witAmt<avalbal) {
		int totAmt=avalbal-witAmt;
		Connection con2=db.getConnection();
		PreparedStatement ps;
		try {
			ps = con2.prepareStatement("update accountDetails2 set Depoist = "+totAmt+
					  " where AccountNo = "+acNo);
			ps.executeUpdate();
			con2.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
		else {
			System.out.println("you have insufficent Amount to withdraw");
			
		}
	}
	
	

}
