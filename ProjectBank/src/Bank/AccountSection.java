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
	
	//in accountDetails get account number and pin no from Home.java
	//and check is account is found or not

	String getAccountName() {
		return this.accountName;
	   }
	int getAvailableBalance() {
		return this.availableBalance;
	}
	
   void accountDetails(String accountNo,int pinNo){
    	dbConnection=DbConnection.getInstance();
	    Connection  connection=dbConnection.getConnection();  
		
	    if(connection!=null) {
	try {
		 Statement s=connection.createStatement();
		//selecting records from database by account number and pin number
		 ResultSet rs=s.executeQuery("select * from accountDetails2 where AccountNo = "+accountNo+" AND Pin = "+pinNo); 
			while(rs.next()) {
			this.accountName=rs.getString(1);
			this.availableBalance=rs.getInt(4);
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
    
    void deposit(int depositAmount,String accountNo) {
 	   Connection  connection = dbConnection.getConnection();  
 	   if(connection!=null) {
    	if(depositAmount>=200) {
    		availableBalance+=depositAmount;
    		 try {
    			PreparedStatement ps = connection.prepareStatement("update accountDetails2 set Depoist = "+availableBalance+
				            " where AccountNo = "+accountNo);
				 ps.executeUpdate();
				 System.out.println("Amount deposit successfully");
 				 System.out.println("Deposit amount : "+depositAmount+"  Available Balance : "+availableBalance);
			} catch (SQLException e) {
				System.out.println("ERROR OCCURED : "+e.getMessage());
			} finally {
				if(connection!=null) {
					try {
					connection.close();   //try to close the connection
				} 
				catch (SQLException e) {
					System.out.println("ERROR OCCURED : "+e.getMessage());
				}
				}
			}
    	}else {
    		System.out.println("Deposit amount must greater then 200");
    	}
    }else {
    	System.out.println("Connection is null");
    }
    }
    
    void withdraw(int withdrawAmount,String accountNo) {
  	   Connection  connection = dbConnection.getConnection();  
  	 if(connection!=null) {
     	if(withdrawAmount>=200 && withdrawAmount<availableBalance) {
     		availableBalance-=withdrawAmount;
     		 try {
     			PreparedStatement ps = connection.prepareStatement("update accountDetails2 set Depoist = "+availableBalance+
 				            " where AccountNo = "+accountNo);
 				 ps.executeUpdate();
 				 System.out.println("Amount withdraw successfully");
 				 System.out.println("withdraw amount : "+withdrawAmount+"  Available Balance : "+availableBalance);
 			} catch (SQLException e) {
 				// TODO Auto-generated catch block
 				e.printStackTrace();
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
     	}else {
     		System.out.println("Deposit amount must greater then 200");
     	}
  	}else {
    	System.out.println("Connection is null");
    }
     }
    
}
