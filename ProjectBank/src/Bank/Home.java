//updated on 17-08-21

package Bank;

import java.util.Scanner;

public class Home {
	AccountRegistration accountRegistration;
	AccountSection accountSection;
	Scanner sc=new Scanner(System.in);
	
	String name;
	int availableBalance;
	String accountNo;
	int pinNo;
	
	//manageUser get user input
	void manageUser(){
		System.out.println(".......Home........");
		System.out.println("Enter 1 for login..");
		System.out.println("Enter 2 for Registration");
		int option=sc.nextInt();		
		
		if(option==1) {           //if the input is 1 go for login
			login();
		}else if(option==2) {     //if the input is 2 go for register
			register();
		}else {                   //if the user  not entered 1 or 2 it show invalid and back to manageUser 
			System.out.println("Enter a valid Input..");
			manageUser();
		}
	}
	
	//login method get account number and pin number 
	void login(){
		
		accountSection=new AccountSection();
		
		System.out.println(".........Login..........");
		System.out.println("Enter Account Number : ");
		accountNo=sc.next();
		
		if(accountNo!=null && !accountNo.isEmpty() && accountNo.length()==16 ) { 
			
		System.out.println("Enter pinNo : ");
		pinNo=sc.nextInt();
		
		int pinCount=0,tempPin=pinNo;
		
		//in while loop counting the how many digit entered in pin
		while(tempPin!=0) {
			tempPin=tempPin/10;
			pinCount++;
		}
		
		if(pinCount==4){           //checking pin is 4 digit or not
		
			accountSection.getaccountDetails(accountNo, pinNo,0,"none"); 
		
		if(accountSection.isAccountfound==true) {       //checking if the account is found or not in the database
			
			name=accountSection.accountName;
			availableBalance=accountSection.availableBalance;
			
			System.out.println("Name : "+name);
			System.out.println("Available balance : "+availableBalance);

			mangeTransaction();     
			
		}else {
			System.out.println("No records found");  
			manageUser();                         
		}
		}else {
				System.out.println("Enter 4 Digit Pin Only ");  
				login();
			}
		}else {
			System.out.println("Values Cannot be Null !! try again");
			manageUser();
		}
		}
	
	

	
	//in register method get register input values
	 void register() {
		 
		 accountRegistration=new AccountRegistration();
		 
		System.out.println("........Register.......");
		System.out.println("Enter your name : ");
		String registerAccountName=sc.next();
		
	        if(registerAccountName!=null  && !registerAccountName.isEmpty()) {
			
		System.out.println("Enter your Acno : ");
		String registerAccountNo=sc.next();
		
	        if(registerAccountNo!=null   && !registerAccountNo.isEmpty()&& registerAccountNo.length()==16) {
			
		int genPinNo=accountRegistration.pinGenerator();  //get 4 digit unique pin number
		
		System.out.println("Retype the PinNo : "+genPinNo);  //asking the user to retype the pin
		int registerPin=sc.nextInt();
		
			if(registerPin==genPinNo ) {
	
				accountRegistration.register(registerAccountName,registerAccountNo,registerPin);   //control goes to register method
				
				if(accountRegistration.isAccountRegistered==true) {                  //checking if the registration is complete or not
					
					System.out.println("Account Successfully Created");
					login();  
					
				}else {
					System.out.println("The Account is already Exists..");
					manageUser();
				}
				}
					else {
						System.out.println("Account Number must be only 16 digits");
						manageUser();
					}
				}
				else{
					System.out.println("Values Cannot be null and must have 16 digit!! try again");
					manageUser();
				}                    
		}else {
			System.out.println("Pin Mismatch");
			manageUser();
		}
		}
			
    // in mangeTransaction ask user to deposit or withdraw amount in the database
	 
	void mangeTransaction() {
		
		System.out.println("Press 1 for Depoist");
		System.out.println("Press 2 for withdraw");
		System.out.println("Press 3 for Exit");
		
		int option=sc.nextInt();
		
		if(option==1) {
			transaction("deposit");             //if 1 transAction mode set deposit
		}else if(option==2){
			transaction("withdraw");        //if 2 transAction mode set withdraw
		}else if(option==3){
			manageUser();      //if 3 control goes to mangeUser method
		}else {
			System.out.println("Enter a valid option");
			mangeTransaction();
		}
	}
	
	//in transaction method user will deposit or withdraw amount from databases
	void transaction(String transActionMode) {
		
		System.out.println("......."+transActionMode+"......");
		System.out.println("Enter your "+transActionMode+" amount");
		
		int amount=sc.nextInt();
		if(amount>10) {                          //checking deposit amount greater than 10
		    accountSection.getaccountDetails(accountNo, pinNo, amount, transActionMode);   //control goes to transAction method
		mangeTransaction();
		}else {
			System.out.println("Enter a valid amount");
			mangeTransaction();
		}
	}
	
	
	public static void main(String[] args) {
		Home home=new Home();
		//call mangeUser to start the program
		home.manageUser();	
	}

}
