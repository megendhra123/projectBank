/**
 * 
 */
package Bank;

public class AccountInformation {
	String accountNo;
	String accountName;
	int availableBalance;

	void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	String getAccountNo() {
		return accountNo;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public int getAvailableBalance() {
		return availableBalance;
	}

	public void setAvailableBalance(int availableBalance) {
		this.availableBalance = availableBalance;
	}
}
