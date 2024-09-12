package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import main.Shop;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

public class CashView extends JDialog{

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtT;
	Shop tienda;

	
	public CashView(Shop tienda) {
		this.tienda = tienda;
		setModal(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Dinero actual en caja:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(49, 63, 252, 50);
		contentPanel.add(lblNewLabel);
		
		txtT = new JTextField();
		txtT.setText(tienda.showCashSwing());
		txtT.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtT.setBounds(49, 123, 205, 50);
		contentPanel.add(txtT);
		txtT.setColumns(10);
		txtT.setEditable(false);
		
	}

	
	
}
