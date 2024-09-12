package model;

import main.Payable;

public class Client extends Person implements Payable{
	int memberId;
	public Amount balance;
	final int MEMBER_ID = 456;
	final Amount BALANCE = new Amount(50);
	public Client() {
		balance = BALANCE;
		memberId = MEMBER_ID;
	}
	public boolean pay (Amount amount) {
		balance.setValor(balance.getValor() - amount.getValor());
		System.out.println("Balance actual de la cuenta: " + balance);
		if (balance.getValor() >= 0) {
			return true;
		}
		else {
			System.out.println("Su saldo actual es negativo");
			return false;
			
		}
	}
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	public int getMemberId() {
		return memberId;
	}
}
