package net.registration.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.registration.dao.EmployeeDao;
import net.registration.model.Employee;

/**
 * Servlet implementation class EmployeeServlet
 */
@WebServlet("/register")
public class EmployeeServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    
	private EmployeeDao employeeDao = new EmployeeDao();
	
    public EmployeeServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/employeeRegister.jsp");
		dispatcher.forward(request, response);
    }

    //need doPost as jsp has method as post
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		
		// Getting info from request
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String address = request.getParameter("address");
		String contact = request.getParameter("contact");

		// Setting info to employee
		Employee employee = new Employee();
		employee.setFirstName(firstName);
		employee.setLastName(lastName);
		employee.setUsername(username);
		employee.setPassword(password);
		employee.setAddress(address);
		employee.setContact(contact);

		// Passing employee to dao
		try {
			int result = employeeDao.registerEmployee(employee);
			if(result > 0) {
				//to print success message on registration page
				response.setContentType("text/html");
				out.print("<h3 style='color:green' align='center'>User registered successfully !!</h3>");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/employeeRegister.jsp");
				dispatcher.include(request, response);
			}else {
				//to print not success message on registration page
				response.setContentType("text/html");
				out.print("<h3 style='color:red' align='center'>User not registered due to some error !!</h3>");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/employeeRegister.jsp");
				dispatcher.include(request, response);
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			//to print exception message on registration page
			response.setContentType("text/html");
			out.print("<h3 style='color:red' align='center'> Exception occured : "+ e.getMessage() +"  !!</h3>");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/employeeRegister.jsp");
			dispatcher.include(request, response);
		}

	}

}
