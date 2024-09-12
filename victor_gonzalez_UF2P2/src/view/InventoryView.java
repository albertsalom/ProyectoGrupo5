package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import main.Shop;
import model.Product;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class InventoryView extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private Shop tienda;

	/**
	 * Create the dialog.
	 */
	public InventoryView(Shop tienda) {
		this.tienda = tienda;
		setBounds(100, 100, 881, 412);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Contenido actual de la tienda: ");
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblNewLabel.setBounds(10, 10, 311, 49);
			contentPanel.add(lblNewLabel);
		}
		{
			StringBuilder inventario = 	new StringBuilder();
			for (Product product : tienda.inventory) {
				if (product != null) {
					inventario.append(product.toString()).append("<br>");
				}
			}
			JLabel lblNewLabel_1 = new JLabel("<html>" + inventario.toString() + "</html>");
			lblNewLabel_1.setVerticalAlignment(SwingConstants.TOP);
			lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblNewLabel_1.setBounds(10, 55, 847, 279);
			contentPanel.add(lblNewLabel_1);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
