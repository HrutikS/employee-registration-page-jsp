package net.registration.controller;

import java.io.IOException;

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
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeeServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at :").append(request.getContextPath());
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/employeeRegister.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
			employeeDao.registerEmployee(employee);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/employeeDetails.jsp");
		dispatcher.forward(request, response);
//		response.sendRedirect("employeedetails.jsp");
	}

}
