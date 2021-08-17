//updated on 17-08-21

package Bank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AccountSection {
	DbConnection dbConnection;
	
	String accountName;
	int availableBalance;
	boolean isAccountfound;
	
	//in accountDetails get account number and pin no from Home.java
	//and check is account is found or not
    public	void getaccountDetails(String accountNo,int pinNo,int amount,String transActionMode){
		
    	dbConnection=DbConnection.getInstance();
	    Connection  connection = null;
	    Statement s;
	    PreparedStatement ps;
	    ResultSet rs = null;
	    connection=dbConnection.getConnection();  
		
	    if(connection!=null) {
	    
	try {
		s=connection.createStatement();
		if(connection!=null) {
		//selecting records from database by account number and pin number
		rs=s.executeQuery("select * from accountDetails2 where AccountNo = "+accountNo+" AND Pin = "+pinNo); 
		
		while(rs.next()) {
			if(rs.getString(1)==null && rs.getInt(4)==0) {   //checking is account is found or not
				isAccountfound=false;                            //set isAccountfound is false if Account not found
			}
			else {
			isAccountfound=true;                                //if account found
			
			accountName=rs.getString(1);
			availableBalance=rs.getInt(4);
			
			}
			
	        switch(transActionMode) {
			
			case "deposit":  
				availableBalance+=amount;         //sum the deposit amount and available balance to totAmt 
				break;
			
			case "withdraw":
				if(amount<availableBalance) {           //checking the withdrawn amount must less than the available balance
				availableBalance-=amount;            //less the withdrawn amount from available balance 
				}else {
					System.out.println("you have insufficent Amount to withdraw");
				}
				break;
				
			default: break;	
			}
	        
	        if(amount!=0) {
	        ps = connection.prepareStatement("update accountDetails2 set Depoist = "+availableBalance+
					                " where AccountNo = "+accountNo);       //set the total amount in the database
			ps.executeUpdate();
			System.out.println("Amount successfully "+transActionMode);
	        }
		}
		}
	} 
	catch (SQLException e) {
		
		if(e.getErrorCode() == 1062 ){
		     System.out.println("Transaction failed");
		    }
		
		System.out.println("ERROR OCCURED : "+e+" ERROR CODE : "+e.getErrorCode());
	}
	finally {
		if(connection!=null) {
		
			try {
			connection.close();   //try to close the connection
		} 
		catch (SQLException e) {
			System.out.println("ERROR OCCURED during closing the connection ERROR : "+e.getMessage());
		}
		}
	}
	}
    }
}
