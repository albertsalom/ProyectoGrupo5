package dao;

import java.sql.SQLException;

import model.Employee;

public interface Dao {
public void connect() throws SQLException ;
public void disconnect()throws SQLException ;
public Employee getEmployee(int userNumber, String password) ;
	

}
