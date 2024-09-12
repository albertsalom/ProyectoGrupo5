package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import main.Shop;
import model.Product;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

public class ProductView extends JDialog implements ActionListener{

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private static final int AC_OK = 1;
	private static final int AC_CANCEL = 2;
	String nombre;
	double precio;
	int stock;
	Shop tienda;
	int opcion;

	public ProductView(Shop tienda, int opcion) {
		this.tienda = tienda;
		this.opcion = opcion;
		setModal(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		if (opcion == ShopView.AC_AnyadirStock || opcion == ShopView.AC_AnyadirProducto || opcion == ShopView.AC_EliminarProducto) {
			{
				JLabel lblNombreProducto = new JLabel("Nombre producto:");
				lblNombreProducto.setFont(new Font("Tahoma", Font.PLAIN, 20));
				lblNombreProducto.setBounds(10, 10, 227, 59);
				contentPanel.add(lblNombreProducto);
			}	
		}
		if (opcion == ShopView.AC_AnyadirStock || opcion == ShopView.AC_AnyadirProducto) {
			{
				JLabel lblStockProducto = new JLabel("Stock producto:\r\n");
				lblStockProducto.setFont(new Font("Tahoma", Font.PLAIN, 20));
				lblStockProducto.setBounds(10, 67, 227, 59);
				contentPanel.add(lblStockProducto);
			}			
		}
		if (opcion == ShopView.AC_AnyadirProducto) {
			{
				JLabel lblPrecioProducto = new JLabel("Precio producto:");
				lblPrecioProducto.setFont(new Font("Tahoma", Font.PLAIN, 20));
				lblPrecioProducto.setBounds(10, 125, 227, 59);
				contentPanel.add(lblPrecioProducto);
			}			
		}
		if (opcion == ShopView.AC_AnyadirStock || opcion == ShopView.AC_AnyadirProducto || opcion == ShopView.AC_EliminarProducto){
			{
				textField = new JTextField();
				textField.setBounds(207, 22, 206, 43);
				contentPanel.add(textField);
				textField.setColumns(10);
			}
		}
		if (opcion == ShopView.AC_AnyadirStock || opcion == ShopView.AC_AnyadirProducto){
			{
				textField_1 = new JTextField();
				textField_1.setColumns(10);
				textField_1.setBounds(207, 79, 206, 43);
				contentPanel.add(textField_1);
			}
		}
		if (opcion == ShopView.AC_AnyadirProducto){
			{
				textField_2 = new JTextField();
				textField_2.setColumns(10);
				textField_2.setBounds(207, 137, 206, 43);
				contentPanel.add(textField_2);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand(String.valueOf(AC_OK));
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				okButton.addActionListener(this);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand(String.valueOf(AC_CANCEL));
				buttonPane.add(cancelButton);
				cancelButton.addActionListener(this);
			}
		}		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		switch (Integer.valueOf(e.getActionCommand())) {
		case AC_OK:
			if (opcion == ShopView.AC_AnyadirProducto){
				anyadirProducto();
			}else if (opcion == ShopView.AC_AnyadirStock) {
				anyadirStock();
			}else if (opcion == ShopView.AC_EliminarProducto) {
				eliminarProducto();
			}
			break;
		case AC_CANCEL:
			this.setVisible(false);
			break;	
		default:
			break;
		}
	}

	private void anyadirProducto() {
		nombre = textField.getText();
		String stockStr = textField_1.getText();
		String precioStr = textField_2.getText();
		if (nombre.isEmpty() || precioStr.isEmpty() || stockStr.isEmpty()) {
			JOptionPane.showMessageDialog(null, "COMPLETA TODOS LOS CAMPOS", "ERROR", JOptionPane.ERROR_MESSAGE);
		}else {
			try {
				precio = Double.valueOf(precioStr);
				stock = Integer.valueOf(stockStr);
				if (tienda.findProduct(nombre) != null) {
					JOptionPane.showMessageDialog(null, "ESTE PRODUCTO YA EXISTE EN EL INVENTARIO", "ERROR", JOptionPane.ERROR_MESSAGE);
				}else {
					tienda.addProduct(new Product(nombre, precio, true, stock));
					JOptionPane.showMessageDialog(null, "PRODUCTO AÑADIDO CORRECTAMENTE", "", JOptionPane.INFORMATION_MESSAGE);
					this.setVisible(false);				
				}				
			} catch (NumberFormatException e2) {
				JOptionPane.showMessageDialog(null, "INTRODUCE UN VALOR NÚMERICO EN LOS CAMPOS 'STOCK' Y 'PRECIO'", "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	private void anyadirStock() {
		nombre = textField.getText();
		String stockStr = textField_1.getText();
		Product producto = tienda.findProduct(nombre);
		if (nombre.isEmpty() || stockStr.isEmpty()) {
			JOptionPane.showMessageDialog(null, "COMPLETA TODOS LOS CAMPOS", "ERROR", JOptionPane.ERROR_MESSAGE);
		}else {
			try {
				stock = Integer.valueOf(stockStr);
				if (producto == null) {
					JOptionPane.showMessageDialog(null, "ESTE PRODUCTO NO EXISTE EN EL INVENTARIO", "ERROR", JOptionPane.ERROR_MESSAGE);
				}else {
					producto.setStock(producto.getStock() + stock);
					JOptionPane.showMessageDialog(null, "STOCK AÑADIDO CORRECTAMENTE", "", JOptionPane.INFORMATION_MESSAGE);
					this.setVisible(false);				
				}				
			} catch (NumberFormatException e2) {
				JOptionPane.showMessageDialog(null, "INTRODUCE UN VALOR NÚMERICO EN EL CAMPO 'STOCK'", "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	private void eliminarProducto() {
		nombre = textField.getText();
		Product producto = tienda.findProduct(nombre);
		if (nombre.isEmpty()) {
			JOptionPane.showMessageDialog(null, "COMPLETA TODOS LOS CAMPOS", "ERROR", JOptionPane.ERROR_MESSAGE);
		}else {
			if (producto == null) {
				JOptionPane.showMessageDialog(null, "ESTE PRODUCTO NO EXISTE EN EL INVENTARIO", "ERROR", JOptionPane.ERROR_MESSAGE);
			}else {
				tienda.inventory.remove(producto);
				JOptionPane.showMessageDialog(null, "PRODUCTO ELIMINADO CORRECTAMENTE", "", JOptionPane.INFORMATION_MESSAGE);
				this.setVisible(false);				
			}				
		}
	}
	
}
