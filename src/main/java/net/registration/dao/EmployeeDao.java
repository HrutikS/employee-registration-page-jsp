package net.registration.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import net.registration.model.Employee;

public class EmployeeDao{

	//to register new employee
	public int registerEmployee(Employee employee) throws ClassNotFoundException{
		
		String registerQuery = "insert into employees"
								+ "(first_name, last_name, username, password, address, contact)"
								+ "values "
								+ "(?,?,?,?,?,?);";
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		Connection con = null;
		PreparedStatement ps = null;
		int res = 0;		
		
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/registration_page_jsp","root","password");
			ps = con.prepareStatement(registerQuery);
			
			ps.setString(1,employee.getFirstName());
			ps.setString(2,employee.getLastName());
			ps.setString(3, employee.getUsername());
			ps.setString(4, employee.getPassword());
			ps.setString(5, employee.getAddress());
			ps.setString(6, employee.getContact());
			System.out.println(ps);
			res = ps.executeUpdate();
			
			if(res != 0) {
				System.out.println("Employee Saved !!");
			}else {
				System.out.println("Employee Save Failed !!");
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				ps.close();
				con.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return res;
	}
	
}
