package Bank;

import java.util.Objects;

public class LoginValues {
	String accNo;
	int PinNo;
	
	
	public void setAccountNo(String accNo) {
		this.accNo = Objects.requireNonNull(accNo);
	}
	public String getAccountNo() {
		return this.accNo;
	}
	
	public void setPinNo(int pinNo) {
		PinNo = pinNo;
	}
	public int getPinNo() {
		return PinNo;
	}
	

}
