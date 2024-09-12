package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.Arrays;

public class Sale {
	Client client;
	//Product[] products;
	ArrayList<Product>products;
	Amount amount;
	LocalDateTime fechaHora;
	
	
	public Sale(Client client, ArrayList<Product> products, Amount amount,LocalDateTime fechaHora) {
		super();
		this.client = client;
		this.products = products;
		this.amount = amount;
		this.fechaHora = fechaHora;
	}
	
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public ArrayList<Product> getProducts() {
		return products;
	}
	public void setProducts(ArrayList<Product> products) {
		this.products = products;
	}
	public Amount getAmount() {
		return amount;
	}
	public void setAmount(Amount amount) {
		this.amount = amount;
	}
  
	@Override
	public String toString() {
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		
		return "Sale [client=" + client.getName() + ", products=" + products.toString() + ", amount=" + amount + ", fecha y hora=" + fechaHora.format(formato) + "]";
	}
	
	
	
	

}
