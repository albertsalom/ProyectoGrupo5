package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Employee;

public class DaoImpIJDBC implements Dao{
	private Connection connection;
	
	public void connect() throws SQLException {
		String url = "jdbc:mysql://localhost:3306/shop";
		String user = "root";
		String pass = "";
		this.connection = DriverManager.getConnection(url, user, pass);
	}
	public void disconnect() throws SQLException {
		if (connection != null) {
			connection.close();
		}
	}
	public Employee getEmployee(int userNumber, String password) {
		Employee empleado = null;
		String query = "select * from employee where userNumber = ? and password = ? ";
		try (PreparedStatement ps = connection.prepareStatement(query)) { 
			ps.setInt(1,userNumber);
			ps.setString(2,password);
		  	
	        try (ResultSet rs = ps.executeQuery()) {
	        	if (rs.next()) {
	        		empleado =  new Employee(rs.getInt(1), rs.getInt(2), rs.getString(3)); 
	        	}
	        }
	    }  catch (SQLException e) {
			// TODO: handle exception
		}
		
		return empleado; 
	}
}
