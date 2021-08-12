package Bank;

import java.util.Scanner;

public class Home {
	LoginValues logValues=new LoginValues();
	RegisterValues regValues=new RegisterValues();
	AccountRegistration accRegistration=new AccountRegistration();
	AccountSection accountSection=new AccountSection();
	Scanner sc=new Scanner(System.in);
	
	//inputOption1 get user input
	void inputOptions1(){
		System.out.println(".......Home........");
		System.out.println("Enter 1 for login..");
		System.out.println("Enter 2 for Registration");
		int option=sc.nextInt();		
		if(option==1) {           //if the input is 1 go for login
			login();
		}else if(option==2) {     //if the input is 2 go for register
			register();
		}else {                   //if the user  not entered 1 or 2 it show invalid and back to inputOption1 
			System.out.println("Enter a valid Input..");
			inputOptions1();
		}
	}
	
	//login method get account number and pin number 
	void login(){
		System.out.println(".........Login..........");
		System.out.println("Enter Account Number : ");
		String accountNo=sc.next();
		System.out.println("Enter pinNo : ");
		int pinNo=sc.nextInt();
				logValues.setAccountNo(accountNo); 
				logValues.setPinNo(pinNo);
				approveLogin();  // after set account login details control goes to approveLogin
		}
	
	//in aaproveLogin method check the inputs and get details from the database
	void approveLogin(){
		String accountNo=logValues.getAccountNo();
		int pinNo=logValues.getPinNo();
		
		int pinCount=0,tempPin=pinNo;
		
		//in while loop counting the how many digit entered in pin
		while(tempPin!=0) {
			tempPin=tempPin/10;
			pinCount++;
		}
		
		//checking input is null and is empty and account no is 16 
		if(accountNo!=null && !accountNo.isEmpty() && accountNo.length()==16 ) { 
		if(pinCount==4){           //checking pin is 4 digit or not
		accountSection.accountDetails(accountNo, pinNo);   //if four control goes to account details method 
		
		if(accountSection.isAccfound==true) {   //checking if the account is found or not in the database
		accountSection.showDetails();           //it show the account details and 
		inputOptions2();                        //control goes to inputoption2
		}else {
			System.out.println("No records found");  
			inputOptions1();                         
		}
		}else {
				System.out.println("Enter 4 Digit Pin Only ");  
				login();
			}
		}else {
			System.out.println("Values Cannot be Null !! try again");
			inputOptions1();
		}
	}
	
	//in register method get register input values
	 void register() {
		 System.out.println("........Register.......");
		System.out.println("ENter your name : ");
		String regAccountName=null;//change by me
		System.out.println("Enter your Acno : ");
		String regAccountNo=sc.next();
		
		accRegistration.pinGenerator();             //control goes to pinGenerator
		int genPinNo=accRegistration.generatePin;  //get 4 digit unique pin number
		
		System.out.println("Retype the PinNo : "+genPinNo);  //asking the user to retype the pin
		int regPin=sc.nextInt();
			if(regPin==genPinNo ) {                 //checking the generated pin and user input pin are same or not
			regValues.setRegAccname(regAccountName);
			regValues.setRegAccNo(regAccountNo);
			regValues.setRegPinNo(regPin);
			approveRegister();                    // after set account Register details control goes to approveRegister
		}else {
			System.out.println("Pin Mismatch");
			inputOptions1();
		}
		}
			
	 
	//in aaproveRegister method check the inputs and any other records found in the database
	 void approveRegister() {
		String regAccountName= regValues.getRegAccname();
		String regAccountNo= regValues.getRegAccNo();
		int regPinNo=regValues.getRegPinNo();
		if(regAccountName!=null  && !regAccountName.isEmpty()) {        //checking the user input is null,empty or not
			if(regAccountNo!=null && regAccountNo.length()==16  && !regAccountNo.isEmpty()) {   
				
		accRegistration.register1(regAccountName,regAccountNo,regPinNo);   //control goes to register method
		accRegistration.completeregister();
		
		if(accRegistration.isAccRegistered==true) {                  //checking if the registration is complete or not
			System.out.println("Account Successfully Created");
			login();                                  
		}else {
			System.out.println("The Account is already Exists..");
			inputOptions1();
		}
		}
			else {
				System.out.println("Account Number must be only 16 digits");
				inputOptions1();
			}
		}
		else{
			System.out.println("Values Cannot be null !! try again");
			inputOptions1();
		}
	 }
	
    // in inputOption2 ask user to deposit or withdraw amount in the database
	 
	void inputOptions2() {
		System.out.println("Press 1 for Depoist");
		System.out.println("Press 2 for withdraw");
		System.out.println("Press 3 for Exit");
		int option=sc.nextInt();
		if(option==1) {
			depoist();             //if 1 control goes to deposit method
		}else if(option==2){
			withdraw();           //if 2 control goes to withdraw method
		}else if(option==3){
			inputOptions1();      //if 3 control goes to inputOptions1 method

		}else {
			System.out.println("Enter a valid option");
			inputOptions2();
		}
	}
	
	//in deposit method user will deposit amount from databases
	void depoist() {
		System.out.println(".....Depoist......");
		System.out.println("Enter your Depoist amount");
		int depoistAmount=sc.nextInt();
		if(depoistAmount>10) {                          //checking deposit amount greater than 10
		accountSection.depoistSection(depoistAmount);  //control goes to depositSection method
		accountSection.showDetails();
		inputOptions2();
		}else {
			System.out.println("Enter a valid amount");
			depoist();
		}
	}
	
	//in withdraw method user will withdraw amount from database
	void withdraw() {
		System.out.println("......Withdraw......");
		System.out.println("Enter your withdraw amount");
		int withdrawAmount=sc.nextInt();
		if( withdrawAmount>10) {                     //checking withdraw amount greater than 10
		accountSection.withSection(withdrawAmount);  //control goes to withdrawSection method
		accountSection.showDetails();
		inputOptions2();
		}
		else {
			System.out.println("Enter a valid amount");
			withdraw();
		}
		
	}
	
	
	public static void main(String[] args) {
		Home home=new Home();
		//call inputOptions1 to start the program
		home.inputOptions1();	
	}

}
