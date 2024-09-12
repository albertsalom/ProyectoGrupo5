package view;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import exception.LimitLoginException;
import model.Employee;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.security.auth.login.LoginException;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComponent;

import java.awt.Font;

public class LoginView extends JFrame implements ActionListener, KeyListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_2;
	private Employee empleado = new Employee();
	private JButton btnNewButton;
	int contador = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginView frame = new LoginView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 824, 495);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Número de empleado");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(66, 0, 250, 112);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(66, 89, 177, 33);
		contentPane.add(textField);
		textField.setColumns(10);
		
		
		JLabel lblNewLabel_2 = new JLabel("Password");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_2.setBounds(66, 132, 141, 71);
		contentPane.add(lblNewLabel_2);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(66, 194, 177, 33);
		contentPane.add(textField_2);
		
		btnNewButton = new JButton("Aceptar");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.setBounds(146, 256, 97, 33);
		btnNewButton.addActionListener(this);
		contentPane.add(btnNewButton);
		
		
		/*textField.addKeyListener(this);
        textField_2.addKeyListener(this);
        btnNewButton.addKeyListener(this);
        this.addKeyListener(this);
        */
		 contentPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "clickButton");
	        contentPane.getActionMap().put("clickButton", new AbstractAction() {
	            public void actionPerformed(ActionEvent ae) {
	                btnNewButton.doClick();
	            }
	        });
	}
	@Override
	
	public void actionPerformed(ActionEvent e) {
		
		int controlador = 0;
		if (textField.getText().isEmpty() && textField_2.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Indique un usuario y una contraseña", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		else {
			
			try {
				// Intenta convertir el texto a un número
				int usuario = Integer.parseInt(textField.getText());
				empleado.login(usuario, textField_2.getText());
				if (empleado.login(usuario, textField_2.getText()) == true) {
					ShopView shopview = new ShopView();
					shopview.setVisible(true);
					controlador = 1;
				}
				else {
					//error = new JOptionPane();
					JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos", "ERROR", JOptionPane.ERROR_MESSAGE);
					contador ++;
					textField.setText("");
					textField_2.setText("");
				}
				// si contador  mayor 3, throw
				if (contador >= 3) {
					throw new LimitLoginException("Limite de intentos", contador);
				}
			} 
			catch (NumberFormatException ex) {
				// Si ocurre una excepción, el texto no es un número
				JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos", "ERROR", JOptionPane.ERROR_MESSAGE);
				textField.setText("");
				textField_2.setText("");
				contador ++;
				try {
					if (contador >= 3) {
						throw new LimitLoginException("Limite de intentos", contador);
					}
					
				} catch (LimitLoginException ex2) {
					// Si ocurre una excepción, el texto no es un número
					JOptionPane.showMessageDialog(null, ex2.toString(), "ERROR", JOptionPane.ERROR_MESSAGE);
					// salir del programa
					dispose();
				}
			} catch (LimitLoginException ex) {
				// Si ocurre una excepción, el texto no es un número
				JOptionPane.showMessageDialog(null, ex.toString(), "ERROR", JOptionPane.ERROR_MESSAGE);
				// salir del programa
				dispose();
			}	
			
		}		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		/*if (e.getKeyCode() == KeyEvent.VK_ENTER) {
        btnNewButton.doClick(); // Presionar automáticamente el botón "Aceptar"
        }*/
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
