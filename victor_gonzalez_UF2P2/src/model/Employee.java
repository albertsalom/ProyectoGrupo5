package model;

import java.sql.SQLException;

import dao.Dao;
import dao.DaoImpIJDBC;
import main.Logable;

public class Employee extends Person implements Logable{
	int employeeId;
	int userNumber;
	String password;
	Dao dao = new DaoImpIJDBC();
	
	public Employee(int employeeID, int userNumber, String password) {
		this.employeeId = employeeID;
		this.userNumber = userNumber;
		this.password = password;
	}
	public Employee() {
		
	}

	public boolean login (int user, String password) {
		try {			
			dao.connect();
			Employee empleado = dao.getEmployee(user, password);			
			dao.disconnect();
			if (empleado != null) {
				//if (empleado.getEmployeeUserNumber() == user && empleado.getEmployeePassword() == password) {
					
				//}
				return true;
			} else {
				return false;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	public int getEmployeeUserNumber () {
		return userNumber;
	}
	public int getEmployeeID () {
		return employeeId;
	}
	public String getEmployeePassword () {
		return password;
	}
}
