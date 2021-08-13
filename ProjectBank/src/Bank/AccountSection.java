package Bank;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;

public class AccountSection {
	DbConnection db=new DbConnection();
	AccountInformation accInfo=new AccountInformation();
	
	String accountNo;
	String accountName;
	int pinNo;
	int avalBal;
	boolean isAccfound;
	
	//in accountDetails get account number and pin no from Home.java
	//and check is account is found or not
	void accountDetails(String accountNo,int pinNo){
		
		this.accountNo=accountNo;
		this.pinNo=pinNo;
		
	    Connection  connection = null;
	    Statement s;
	try {
		connection=db.getConnection();  
		s=connection.createStatement();
		
		//selecting records from database by account number and pin number
		ResultSet rs=s.executeQuery("select * from accountDetails2 where AccountNo = "+accountNo+" AND Pin = "+pinNo); 
		
		while(rs.next()) {
			if(rs.getString(1)==null && rs.getInt(4)==0) {   //checking is account is found or not
				isAccfound=false;                            //set isAccfound is false if Account not found
			}else {
			isAccfound=true;                                //if account found
			accInfo.setAccountName(rs.getString(1));      //getting data from database and set Account Information 
			accInfo.setAvailableBalance(rs.getInt(4));
			}
		}
	} catch (SQLException e) {
		System.out.println("ERROR OCCURED : "+e+" ERROR CODE : "+e.getErrorCode());
	}catch (InputMismatchException eInMis) {
		System.out.println("Input Mismatched"+eInMis);		
	}catch (NullPointerException nullExp) {
		System.out.println("Connection Cannot be closed because connection is null "+nullExp.getMessage());
	}
	finally {
		try {
			connection.close();   //try to close the connection
		} catch (SQLException e) {
			System.out.println("ERROR OCCURED during closing the connection ERROR : "+e.getMessage());
		}catch (NullPointerException nullExp) {  //if the connection is null this block will be execute
			System.out.println("Connection Cannot be closed because connection is null "+nullExp.getMessage());
		}
	}
	}
	
	//showing name and available balance 
	void showDetails() {
		accountDetails(accountNo, pinNo);   //call accountDetails method to get updated details after withdraw or deposit
		this.accountName=accInfo.getAccountName();  
		this.avalBal=accInfo.getAvailableBalance();
		System.out.println(".......Account Details......");
		System.out.println("Name :"+accountName);
		System.out.println("Available balance :"+avalBal);
	}
	
	//in deposit section the deposit amount will be inserted in database
	void depoistSection(int depAmt) {
		int totAmt=depAmt+avalBal;     //sum the deposit amount and available balance to totAmt 
		Connection connection=db.getConnection();
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement("update accountDetails2 set Depoist = "+totAmt+
					  " where AccountNo = "+accountNo);       //set the total amount in the database
			ps.executeUpdate();
			System.out.println("Amount depoisted successfully");
		} catch (SQLException e) {
			System.out.println("Amount cannot be depoisted try again "+e);
		}catch (InputMismatchException eInMis) {
			System.out.println("Input Mismatched"+eInMis);
		}catch (NullPointerException nullExp) {
			System.out.println("Connection Cannot be closed because connection is null "+nullExp.getMessage());
		}catch (Exception e) {
			System.out.println("Server Down...");
		}
		finally {
			try {
				connection.close();                 //try to close the connection
			} catch (SQLException e) {
				System.out.println("ERROR OCCURED during closing the connection ERROR : "+e);
			}catch (NullPointerException nullExp) {   
				System.out.println("Connection Cannot be closed because connection is null "+nullExp.getMessage());
			}
		}
	}
	
	void withSection(int witAmt) {
		if(witAmt<avalBal) {   //checking the withdrawn amount must less than the available balance
		int totAmt=avalBal-witAmt;   //less the withdrawn amount from available balance 
		Connection connection=db.getConnection();
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement("update accountDetails2 set Depoist = "+totAmt+
					  " where AccountNo = "+accountNo); //set the total amount in the database
			ps.executeUpdate();
			System.out.println("Amount Withdrawed successful");
		} catch (SQLException e) {
			System.out.println("Amount cannot be withdraw try again "+e);
		}catch (InputMismatchException eInMis) {
			System.out.println("Input Mismatched"+eInMis);
		}catch (NullPointerException nullExp) {
			System.out.println("Connection Cannot be closed because connection is null "+nullExp.getMessage());
		}
		finally {
			try {
				connection.close();     //try to close the connection
			} catch (SQLException e) {
				System.out.println("ERROR OCCURED during closing the connection ERROR : "+e);

			}catch (NullPointerException nullExp) {
				System.out.println("Connection Cannot be closed because connection is null "+nullExp.getMessage());
			}
		}
	}
		else {
			System.out.println("you have insufficent Amount to withdraw");
			
		}
	}
	
	

}
