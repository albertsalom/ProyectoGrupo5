package main;

import model.Amount;

public interface Payable {
public default boolean pay(Amount amount) {
	return false;
}
}
