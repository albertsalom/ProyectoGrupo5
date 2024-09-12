package main;

import model.Amount;
import model.Client;
import model.Employee;
import model.Product;
import model.Sale;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

public class Shop {
	private Amount cash;
	//private Product[] inventory;
	public ArrayList<Product> inventory;
	//private Sale[] sales;
	public ArrayList<Sale> sales;
	//int salePosicion;

	final static double TAX_RATE = 1.04;

	public Shop() {
		cash = new Amount(10.0); 
		//inventory = new Product[10];
		inventory = new ArrayList<Product>();
		//sales = new Sale[10];
		sales = new ArrayList<Sale>();
		//salePosicion = 0;
	}

	public static void main(String[] args) throws IOException {
		Shop shop = new Shop();

		shop.loadInventory();

		Scanner scanner = new Scanner(System.in);
		int opcion = 0;
		boolean exit = false;

		initSession();
		
		do {
			System.out.println("\n");
			System.out.println("===========================");
			System.out.println("Menu principal miTienda.com");
			System.out.println("===========================");
			System.out.println("1) Contar caja");
			System.out.println("2) Añadir producto");
			System.out.println("3) Añadir stock");
			System.out.println("4) Marcar producto proxima caducidad");
			System.out.println("5) Ver inventario");
			System.out.println("6) Venta");
			System.out.println("7) Ver ventas");
			System.out.println("8) Ver venta total");
			System.out.println("9) Eliminar producto");
			System.out.println("10) Salir programa");
			System.out.print("Seleccione una opción: ");
			opcion = scanner.nextInt();

			switch (opcion) {
			case 1:
				shop.showCash();
				break;

			case 2:
				shop.addProduct();
				break;

			case 3:
				shop.addStock();
				break;

			case 4:
				shop.setExpired();
				break;

			case 5:
				shop.showInventory();
				break;

			case 6:
				shop.sale();
				break;

			case 7:
				shop.showSales();
				break;
				
			case 8:
				shop.totalVentas();
				break;
				
			case 9:
				shop.removeProduct();
				break;
				
			case 10:
				exit = true;
				break;
				
			case 11:
				shop.loadInventory();
				break;
			}	
			
		} while (!exit);

	}

	/**
	 * load initial inventory to shop
	 * @throws IOException 
	 */
	private static void initSession() {
		Employee empleado = new Employee();
		Scanner scanner = new Scanner(System.in);
		int numeroEmpleado;
		String contrasenyaEmpleado = null;
		do {
			System.out.println("Numero de empleado: ");
			numeroEmpleado = scanner.nextInt();
			System.out.println("Contraseña: ");
			contrasenyaEmpleado = scanner.next();
			empleado.login(numeroEmpleado, contrasenyaEmpleado);
			if (empleado.login(numeroEmpleado, contrasenyaEmpleado) == false) {
				System.out.println("Usuario o Contraseña incorrectos");
			}
			else {
				System.out.println("Login correcto");
			}
		} while (empleado.login(numeroEmpleado, contrasenyaEmpleado) == false);
	}
	public void loadInventory() throws IOException {
		/*
		addProduct(new Product("Manzana", 10.00, true, 10));
		addProduct(new Product("Pera", 20.00, true, 20));
		addProduct(new Product("Hamburguesa", 30.00, true, 30));
		addProduct(new Product("Fresa", 5.00, true, 20));
		*/
		File f = new File(System.getProperty("user.dir") + File.separator + "files/inputInventory.txt");
		FileReader fr = new FileReader(f);
		BufferedReader br = new BufferedReader(fr);
		String linea = br.readLine();
		while (linea != null) {
			String [] resultado = linea.split(";");
			String [] resultadoFinal;
			String nombre = null;
			double precio = 0;
			int stock = 0;
			for (int i = 0; i < resultado.length; i++) {
				resultadoFinal = resultado[i].split(":");
				switch (i) {
				case 0:
					nombre = resultadoFinal[1];
					break;
				case 1:
					precio = Double.parseDouble(resultadoFinal[1]);
					break;
				case 2:
					stock = Integer.parseInt(resultadoFinal[1]);
					break;
				}
			}
			addProduct(new Product(nombre, precio, true, stock));
			
			linea = br.readLine();
		}
	}

	/**
	 * show current total cash
	 */
	private void showCash() {
		
		System.out.println("Dinero actual: " + this.cash.toString());
	}
	
public String showCashSwing() {
		
		return this.cash.toString();
	}

	/**
	 * add a new product to inventory getting data from console
	 */
	public void addProduct() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Nombre: ");
		String name = scanner.nextLine();
		System.out.print("Precio mayorista: ");
		double wholesalerPrice = scanner.nextDouble();
		System.out.print("Stock: ");
		int stock = scanner.nextInt();

		addProduct(new Product(name, wholesalerPrice, true, stock));
	}
	
	/**
	 * add stock for a specific product
	 */
	public void addStock() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Seleccione un nombre de producto: ");
		String name = scanner.next();
		Product product = findProduct(name);

		if (product != null) {
			// ask for stock
			System.out.print("Seleccione la cantidad a añadir: ");
			int stock = scanner.nextInt();
			// update stock product
			product.setStock(product.getStock() + stock);
			System.out.println("El stock del producto " + name + " ha sido actualizado a " + product.getStock());

		} else {
			System.out.println("No se ha encontrado el producto con nombre " + name);
		}
	}
	public void removeProduct() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Seleccione un nombre de producto: ");
		String name = scanner.next();
		Product product = findProduct(name);

		if (product != null) {
			inventory.remove(product);
			System.out.println("Producto (" + product + ") eliminado correctamente");

		} else {
			System.out.println("No se ha encontrado el producto con nombre " + name);
		}

	}
	
	/**
	 * set a product as expired
	 */
	private void setExpired() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Seleccione un nombre de producto: ");
		String name = scanner.next();

		Product product = findProduct(name);
		product.expire();

		if (product != null) {
			System.out.println("El stock del producto " + name + " ha sido actualizado a " + product.getPublicPrice());

		}
	}

	/**
	 * show all inventory
	 */
	public void showInventory() {
		System.out.println("Contenido actual de la tienda:");
		for (Product product : inventory) {
			if (product != null) {
				System.out.println(product);
			}
		}
	}

	/**
	 * make a sale of products to a client
	 */
	public void sale() {
		// ask for client name
		Scanner sc = new Scanner(System.in);
		System.out.println("Realizar venta, escribir nombre cliente");
		String cliente = (String) sc.nextLine();
		Client client = new Client();
		client.setName(cliente);

		// sale product until input name is not 0
		Amount totalAmount = new Amount(0.0);
		LocalDateTime fechaHora = null;
		String name = "";
		ArrayList<Product> productos = new ArrayList<Product>();
		while (!name.equals("0")) {
			System.out.println("Introduce el nombre del producto, escribir 0 para terminar:");
			name = sc.next();
			System.out.println("has introducido " + name);
			if (name.equals("0")) {
				break;
			}
			Product product = findProduct(name);
			boolean productAvailable = false;
			
			if (product != null && product.isAvailable()) {
				productAvailable = true;
				//Añadir opción de elegir cantidad de producto
				System.out.println("Introduce la cantidad de producto que desea comprar");
				int cantidadProducto = sc.nextInt();
				if (cantidadProducto > 0 && cantidadProducto <= product.getStock()) {
					product.setStock(product.getStock() - cantidadProducto);
					//totalAmount += (product.getPublicPrice() * cantidadProducto);
					totalAmount.setValor(totalAmount.getValor() + (product.getPublicPrice().getValor() * cantidadProducto));
					productos.add(product);
					// if no more stock, set as not available to sale
					if (product.getStock() == 0) {
						product.setAvailable(false);
					}
					System.out.println("Producto añadido con éxito");
				}else {
					System.out.println("Stock insuficiente");
					productAvailable = false;
				}
				 
			}
			
			if (!productAvailable) {
				System.out.println("Producto no encontrado o sin stock");
			}
		}

		// show cost total
		
		//totalAmount = totalAmount * TAX_RATE;
		totalAmount.setValor(totalAmount.getValor() * TAX_RATE);
		fechaHora = LocalDateTime.now();
		Sale sale = new Sale(client, productos, totalAmount, fechaHora);
		//sales [salePosicion] = sale;
		sales.add(sale);
		//cash += totalAmount;
		cash.setValor(cash.getValor() + totalAmount.getValor());
		System.out.println("Venta realizada con éxito, total: " + totalAmount);
		client.pay(totalAmount);
		
	}

	/**
	 * show all sales
	 * @throws IOException 
	 */
	
	private void showSales() throws IOException {
		System.out.println("Lista de ventas:");
		for (Sale sale : sales) {
			if (sale != null) {
				System.out.println(sale);
			}
		}
		System.out.println("¿Quieres almacenar la informacion de las ventas?");
		Scanner scanner = new Scanner(System.in);
		String respuesta = scanner.next();
		if (respuesta.equals("si")) {
			almacenarVentas();
		}
	}
	public void almacenarVentas() throws IOException {
		LocalDate fecha = LocalDate.now();
		String rutaCarpeta = System.getProperty("user.dir")+File.separator+"files";
		String rutaArchivo = rutaCarpeta + File.separator + "sales_"+fecha.toString()+".txt";
		LocalDateTime fechaHora = LocalDateTime.now();
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/mm/yyyy hh:mm:ss");
		System.out.println(rutaArchivo);
		File file = new File(rutaArchivo);
		FileWriter fw = new FileWriter(file);
		PrintWriter pw = new PrintWriter(fw);
		int iteracion = 1;
		for (Sale sale : sales) {
			if (sale != null) {
				pw.write(iteracion + ";Client=" + sale.getClient() + ";Date=" + fechaHora.format(formato) + "\n");
				pw.write(iteracion + ";Products=");
				for (int i = 0; i < sale.getProducts().size(); i++) {
					String nombreProducto = sale.getProducts().get(i).getName();
					Amount precioProducto = sale.getProducts().get(i).getPublicPrice();
					
					pw.write(nombreProducto + ", " + precioProducto + ";");
				}
				pw.write("\n");				
				pw.write(iteracion + ";Amount=" + sale.getAmount() + "\n");
				iteracion++;
			}
		}
		pw.close();
	}


	/**
	 * add a product to inventory
	 * 
	 * @param product
	 */
	public void addProduct(Product product) {
		//inventory[numberProducts] = product;
		inventory.add(product);
	}

	/**
	 * check if inventory is full or not
	 
	public boolean isInventoryFull() {
		if (inventory.size() >= 10) {
			return true;
		} else {
			return false;
		}

	}
	*/
	private void totalVentas() {
		Amount totalVentas = new Amount(0.0);
		boolean ventasVacio = true;
		for (int i = 0; i < sales.size(); i++) {
			if (sales.get(i) != null) {
				//totalVentas += sales[i].getAmount();
				totalVentas.setValor(totalVentas.getValor() + (sales.get(i).getAmount()).getValor());
				ventasVacio = false;
			}
		}if (ventasVacio == true) {
			System.out.println("No se han encontrado ventas");
		}else {
			System.out.println("Valor total de ventas: " + totalVentas);
		}
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}
	

	/**
	 * find product by name
	 * 
	 * @param product name
	 */
	public Product findProduct(String name) {
		for (int i = 0; i < inventory.size(); i++) {
			if (inventory.get(i) != null && inventory.get(i).getName().equalsIgnoreCase(name)) {
				return inventory.get(i);
			}
		}
		return null;

	}

}