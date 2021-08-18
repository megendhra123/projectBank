//updated on 17-08-21

package Bank;

import java.util.Scanner;

public class Home {

	Scanner sc = new Scanner(System.in);

	// manageUser get user input
	void manageUser() {

		System.out.println(".......Home........");
		System.out.println("Enter 1 for login..");
		System.out.println("Enter 2 for Registration");
		int option = sc.nextInt();

		if (option == 1) { // if the input is 1 go for login
			login();
		} else if (option == 2) { // if the input is 2 go for register
			register();
		} else { // if the user not entered 1 or 2 it show invalid and back to manageUser
			System.out.println("Enter a valid Input..");
			manageUser();
		}
	}

	// login method get account number and pin number
	void login() {

		System.out.println(".........Login..........");
		System.out.println("Enter Account Number : ");
		String accountNo = sc.next();

		if (accountNo != null && !accountNo.isEmpty() && accountNo.length() == 16) {
			System.out.println("Enter pinNo : ");
			int pinNo = sc.nextInt();

			if (pinNo > 999 && pinNo < 10000) { // checking pin is 4 digit or not
				AccountSection accountSection = new AccountSection();
				AccountInformation getAccountInformation = accountSection.accountDetails(accountNo, pinNo);

				String accountName = getAccountInformation.getAccountName();
				int availableBalance = getAccountInformation.getAvailableBalance();

				if (accountName != null) {
					System.out.println("Name : " + accountName);
					System.out.println("Available Balance : " + availableBalance);
					manageTransaction(accountNo);
				} else {
					System.out.println("No records found..");
					System.out.println("If you Don't Have an Account Enter 1 to Register");
					System.out.println("Enter 2 to Exit");
					int input = sc.nextInt();
					if (input == 1) {
						register();
					} else {
						manageUser();
					}
				}
			} else {
				System.out.println("Enter 4 Digit Pin Only ");
				login();
			}
		} else {
			System.out.println("Please enter 16 digit valid account number");
			login();
		}
	}

	// in mangeTransaction ask user to deposit or withdraw amount in the database
	void manageTransaction(String accountNo) {
		AccountSection accountSection = new AccountSection();
		System.out.println("Press 1 for Depoist");
		System.out.println("Press 2 for withdraw");
		System.out.println("Press 3 for Exit");
		int option = sc.nextInt();

		if (option == 1) {
			System.out.println("Enter your Deposit Amount : ");
			int depositAmount = sc.nextInt();
			if (depositAmount > 200) {
				accountSection.deposit(depositAmount, accountNo);
			} else {
				System.out.println("deposit amount must be greater than 200");
			}
			manageUser();

		} else if (option == 2) {
			System.out.println("Enter your withdraw Amount : ");
			int withdrawAmount = sc.nextInt();
			if (withdrawAmount > 200) {
				accountSection.withdraw(withdrawAmount, accountNo);
			} else {
				System.out.println("withdraw amount must be greater than 200");
			}
			manageUser();
		} else if (option == 3) {
			manageUser();
		} else {
			System.out.println("Enter a valid option");
			manageUser();
		}

	}

	// in register method get register input values
	void register() {

		AccountRegistration accountRegistration = new AccountRegistration();
		System.out.println("........Register.......");
		System.out.println("Enter your name : ");
		String registerAccountName = sc.next();
		if (registerAccountName != null && !registerAccountName.isEmpty()) {

			System.out.println("Enter your Acno : ");
			String registerAccountNo = sc.next();
			if (registerAccountNo != null && !registerAccountNo.isEmpty() && registerAccountNo.length() == 16) {

				int generatePinNo = accountRegistration.pinGenerator(); // get 4 digit unique pin number

				System.out.println("Retype the PinNo : " + generatePinNo); // asking the user to retype the pin
				int registerPin = sc.nextInt();
				if (registerPin == generatePinNo) {
					accountRegistration.register(registerAccountName, registerAccountNo, registerPin); // control goes
																										// to //
																										// register
																										// method
					manageUser();

				} else {
					System.out.println("Pin Mismatch");
					manageUser();
				}
			} else {
				System.out.println("Please enter your valid Account Name");
			}
		} else {
			System.out.println("Please enter a valid Account Number");
		}
	}

	public static void main(String[] args) {
		Home home = new Home();
		// call mangeUser to start the program
		home.manageUser();
	}

}
