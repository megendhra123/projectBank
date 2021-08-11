package Bank;

import java.util.Scanner;

public class Home {
	LoginValues loginValues=new LoginValues();
	RegisterValues regValues=new RegisterValues();
	AccountRegistration acReg=new AccountRegistration();
	AccountSection acSec=new AccountSection();
	Scanner sc=new Scanner(System.in);
	
	void inputValidation1(){
		System.out.println(".......Home........");
		System.out.println("Enter 1 for login..");
		System.out.println("Enter 2 for Registration");
		int input=sc.nextInt();		
		if(input==1) {
			login();
		}else if(input==2) {
			registerStep();
		}else {
			System.out.println("Enter a valid Input..");
			inputValidation1();
		}
	}
	
	void login(){
		System.out.println(".........Login..........");
		System.out.println("Enter Account Number : ");
		String acNo=sc.next();
		System.out.println("Enter PiNo : ");
		int piNo=sc.nextInt();
		int pinCount=0,tempPin=piNo;
		while(tempPin!=0) {
			tempPin=tempPin/10;
			pinCount++;
		}
		if(acNo.length()==16 && !acNo.isEmpty() && acNo!=null) {
			if(pinCount==4) {
				loginValues.setAcNo(acNo);
				loginValues.setPinNo(piNo);
				loginApprv();
			}else {
				System.out.println("Enter 4 Digit Pin Only ");
				login();
			}
		}else {
			System.out.println("Enter 16 Digit Account Number..");
			login();
		}
	}
	void loginApprv(){
		String acNo=loginValues.getAcNo();
		int pinNo=loginValues.getPinNo();
		
		if(acNo!=null && pinNo!=0) {
		acSec.accountDetails(acNo, pinNo);
		if(acSec.found==true) {
		acSec.showDetails();
		inputvalidation2();
		}else {
			System.out.println("No records found");
			inputValidation1();
		}
		}
		else {
			System.out.println("Values are Null !! try again");
			inputValidation1();
		}
	}
	 void registerStep() {
		 System.out.println("........Register.......");
		System.out.println("ENter your name : ");
		String rAcname=sc.next();
		System.out.println("Enter your Acno : ");
		String rAcNo=sc.next();
		
		acReg.pinGenerator();
		int rPinNo=acReg.gPin;
		
		System.out.println("Retype the PinNo : "+rPinNo);
		int inPin=sc.nextInt();
		
		if(rAcname.length()!=0 && !rAcname.isEmpty() && rAcname!=null) {
		if(rAcNo.length()==16 && !rAcNo.isEmpty() && rAcNo!=null) {
			if(inPin==rPinNo) {
			regValues.setRAcname(rAcname);
			regValues.setRAcNo(rAcNo);
			regValues.setrPinNo(rPinNo);
			apprvRegiste1();
		}else {
			System.out.println("Pin Mismatch");
			inputValidation1();
		}
		}else {
			System.out.println("Enter 16 Digit Account No");
			inputValidation1();
		}	
		}else {
			System.out.println("Enter your Name");
			inputValidation1();
		}
	}
	 void apprvRegiste1() {
		String rAcName= regValues.getRAcname();
		String rAcNo= regValues.getRAcNo();
		int rPinNo=regValues.getrPinNo();
		if(rAcName!=null && rAcNo!=null && rPinNo!=0) {
		acReg.register1(rAcName,rAcNo,rPinNo);
		acReg.completeregister();
		if(acReg.b==true) {
			System.out.println("Account Successfully Created");
			login();
		}else {
			System.out.println("The Account is already Exists..");
			inputValidation1();
		}
		}
		else{
			System.out.println("Values are null !! try again");
			inputValidation1();
		}
	 }
	

	void inputvalidation2() {
		System.out.println("Press 1 for Depoist");
		System.out.println("Press 2 for withdraw");
		System.out.println("Press 3 for Exit");
		int in2=sc.nextInt();
		if(in2==1) {
			depoist();
		}else if(in2==2){
			withdraw();

		}else if(in2==3){
			inputValidation1();

		}else {
			System.out.println("Enter a valid option");
			inputvalidation2();
		}
	}
	
	void depoist() {
		System.out.println(".....Depoist......");
		System.out.println("Enter your Depoist amount");
		int dptAmt=sc.nextInt();
		if(dptAmt!=0 && dptAmt>1) {
		acSec.depoistSection(dptAmt);
		acSec.showDetails();
		inputvalidation2();
		}else {
			System.out.println("Enter a valid amount");
			depoist();
		}
	}
	void withdraw() {
		System.out.println("......Withdraw......");
		System.out.println("Enter your withdraw amount");
		int witAmt=sc.nextInt();
		if(witAmt!=0 && witAmt>1) {
		acSec.withSection(witAmt);
		acSec.showDetails();
		inputvalidation2();
		}
		else {
			System.out.println("Enter a valid amount");
			withdraw();
		}
		
	}
	
	
	public static void main(String[] args) {
		Home home=new Home();
		home.inputValidation1();	
	}

}
