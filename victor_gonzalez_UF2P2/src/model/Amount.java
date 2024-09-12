package model;

public class Amount {
double value;
String currency;

public Amount(double value){
	this.value = value;
	currency = "€";
}
public double getValor() {
	return value;
}
public void setValor(double value) {
	this.value = value;
}
public String getCurrency() {
	return currency;
}
public String toString() {
	return value + " " + currency;
}
}
