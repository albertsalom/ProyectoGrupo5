package view;

import java.awt.EventQueue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;

import main.Shop;

import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.SwingConstants;

public class ShopView extends JFrame implements ActionListener, KeyListener{

	private static final long serialVersionUID = 1L;
	public static final int AC_ContarCaja = 1;
	public static final int AC_AnyadirProducto = 2;
	public static final int AC_AnyadirStock = 3;
	public static final int AC_MarcarProducto = 4;
	public static final int AC_VerInventario = 5;
	public static final int AC_Venta = 6;
	public static final int AC_VerVentas = 7;
	public static final int AC_VerVentaTotal = 8;
	public static final int AC_EliminarProducto = 9;
	public static final int AC_SalirPrograma = 0;
	private JPanel contentPane;
	private JButton btnContarCaja;
	private JButton btnAnyadirProducto;
	private JButton btnAnyadirStock;
	private JButton btnMarcarProducto;
	private JButton btnVerInventario;
	private JButton btnVenta;
	private JButton btnVerVentas;
	private JButton btnVerVentaTotal;
	private JButton btnEliminarProducto;
	private JButton btnSalirPrograma;
	Shop tienda;

	/**
	 * Create the frame.
	 */
	public ShopView() {
		tienda = new Shop();
		try {
			tienda.loadInventory();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 681, 797);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Seleccion o pulse una opcion:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(10, 24, 300, 57);
		contentPane.add(lblNewLabel);
		
		btnContarCaja = new JButton("1) Contar caja");
		btnContarCaja.setActionCommand(String.valueOf(AC_ContarCaja));
		btnContarCaja.setHorizontalAlignment(SwingConstants.LEFT);
		btnContarCaja.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnContarCaja.setBounds(58, 91, 347, 47);
		btnContarCaja.addActionListener(this);
		contentPane.add(btnContarCaja);
		
		btnAnyadirProducto = new JButton("2) Añadir producto");
		btnAnyadirProducto.setActionCommand(String.valueOf(AC_AnyadirProducto));
		btnAnyadirProducto.setHorizontalAlignment(SwingConstants.LEFT);
		btnAnyadirProducto.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnAnyadirProducto.setBounds(58, 148, 347, 47);
		btnAnyadirProducto.addActionListener(this);
		contentPane.add(btnAnyadirProducto);
		
		btnAnyadirStock = new JButton("3) Añadir stock");
		btnAnyadirStock.setActionCommand(String.valueOf(AC_AnyadirStock));
		btnAnyadirStock.setHorizontalAlignment(SwingConstants.LEFT);
		btnAnyadirStock.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnAnyadirStock.setBounds(58, 205, 347, 47);
		btnAnyadirStock.addActionListener(this);
		contentPane.add(btnAnyadirStock);
		
		btnMarcarProducto = new JButton("4) Marcar producto proxima caducidad");
		btnMarcarProducto.setActionCommand(String.valueOf(AC_MarcarProducto));
		btnMarcarProducto.setHorizontalAlignment(SwingConstants.LEFT);
		btnMarcarProducto.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnMarcarProducto.setBounds(58, 262, 347, 47);
		btnMarcarProducto.addActionListener(this);
		contentPane.add(btnMarcarProducto);
		
		btnVerInventario = new JButton("5) Ver inventario");
		btnVerInventario.setActionCommand(String.valueOf(AC_VerInventario));
		btnVerInventario.setHorizontalAlignment(SwingConstants.LEFT);
		btnVerInventario.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnVerInventario.setBounds(58, 319, 347, 47);
		btnVerInventario.addActionListener(this);
		contentPane.add(btnVerInventario);
		
		btnVenta = new JButton("6) Venta");
		btnVenta.setActionCommand(String.valueOf(AC_Venta));
		btnVenta.setHorizontalAlignment(SwingConstants.LEFT);
		btnVenta.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnVenta.setBounds(58, 376, 347, 47);
		btnVenta.addActionListener(this);
		contentPane.add(btnVenta);
		
		btnVerVentas = new JButton("7) Ver ventas");
		btnVerVentas.setActionCommand(String.valueOf(AC_VerVentas));
		btnVerVentas.setHorizontalAlignment(SwingConstants.LEFT);
		btnVerVentas.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnVerVentas.setBounds(58, 433, 347, 47);
		btnVerVentas.addActionListener(this);
		contentPane.add(btnVerVentas);
		
		btnVerVentaTotal = new JButton("8) Ver venta total");
		btnVerVentaTotal.setActionCommand(String.valueOf(AC_VerVentaTotal));
		btnVerVentaTotal.setHorizontalAlignment(SwingConstants.LEFT);
		btnVerVentaTotal.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnVerVentaTotal.setBounds(58, 490, 347, 47);
		btnVerVentaTotal.addActionListener(this);
		contentPane.add(btnVerVentaTotal);
		
		btnEliminarProducto = new JButton("9) Eliminar producto");
		btnEliminarProducto.setActionCommand(String.valueOf(AC_EliminarProducto));
		btnEliminarProducto.setHorizontalAlignment(SwingConstants.LEFT);
		btnEliminarProducto.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnEliminarProducto.setBounds(58, 547, 347, 47);
		btnEliminarProducto.addActionListener(this);
		contentPane.add(btnEliminarProducto);
		
		btnSalirPrograma = new JButton("0) Salir programa");
		btnSalirPrograma.setActionCommand(String.valueOf(AC_SalirPrograma));
		btnSalirPrograma.setHorizontalAlignment(SwingConstants.LEFT);
		btnSalirPrograma.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnSalirPrograma.setBounds(58, 604, 347, 47);
		btnSalirPrograma.addActionListener(this);
		contentPane.add(btnSalirPrograma);
		
		addKeyListener(this);
		this.setFocusable(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		switch (Integer.valueOf(e.getActionCommand())) {
		case AC_ContarCaja:
			CashView cashView = new CashView(tienda);
			cashView.setVisible(true);
			this.requestFocusInWindow();
			break;
		case AC_AnyadirProducto:
			ProductView productView = new ProductView(tienda, AC_AnyadirProducto);
			productView.setVisible(true);
			this.requestFocusInWindow();
			break;
		case AC_AnyadirStock:
			productView = new ProductView(tienda, AC_AnyadirStock);
			productView.setVisible(true);
			this.requestFocusInWindow();
			break;
		case AC_MarcarProducto:
			
			this.requestFocusInWindow();
			break;
		case AC_VerInventario:
			InventoryView inventoryView = new InventoryView(tienda);
			inventoryView.setVisible(true);
			this.requestFocusInWindow();
			break;
		case AC_Venta:
			
			this.requestFocusInWindow();
			break;
		case AC_VerVentas:
			
			this.requestFocusInWindow();
			break;
		case AC_VerVentaTotal:
			
			this.requestFocusInWindow();
			break;
		case AC_EliminarProducto:
			productView = new ProductView(tienda, AC_EliminarProducto);
			productView.setVisible(true);
			this.requestFocusInWindow();
			break;
		case AC_SalirPrograma:
			dispose();
			break;
		default:
			
			break;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int key = e.getKeyCode();
		switch (key) {
		case KeyEvent.VK_1:
            btnContarCaja.doClick();
            break;
        case KeyEvent.VK_2:
            btnAnyadirProducto.doClick();
            break;
        case KeyEvent.VK_3:
            btnAnyadirStock.doClick();
            break;
        case KeyEvent.VK_4:
            btnMarcarProducto.doClick();
            break;
        case KeyEvent.VK_5:
            btnVerInventario.doClick();
            break;
        case KeyEvent.VK_6:
            btnVenta.doClick();
            break;
        case KeyEvent.VK_7:
            btnVerVentas.doClick();
            break;
        case KeyEvent.VK_8:
            btnVerVentaTotal.doClick();
            break;
        case KeyEvent.VK_9:
            btnEliminarProducto.doClick();
            break;
        case KeyEvent.VK_0:
            btnSalirPrograma.doClick();
            break;
		default:
			
			break;
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
