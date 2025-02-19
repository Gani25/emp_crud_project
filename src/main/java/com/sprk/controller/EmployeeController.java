package com.sprk.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import com.sprk.dao.EmployeeDao;
import com.sprk.model.Employee;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/employee")
public class EmployeeController extends HttpServlet {

	@Resource(name = "sprk_emp")
	private DataSource dataSource;

	private EmployeeDao employeeDao;

	@Override
	public void init() throws ServletException {
		employeeDao = new EmployeeDao(dataSource);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String firstName = req.getParameter("first_name");
		String lastName = req.getParameter("last_name");
		String email = req.getParameter("email");
		String gender = req.getParameter("gender");
		String address = req.getParameter("address");
		
		Employee employee = new Employee(0, firstName, lastName, email, gender, address);
		int errorCount = 0;
		Map<String, String> errorMessages = new HashMap<>();
		
		if (firstName == null || firstName.isBlank()) {
			
			errorCount++;
			errorMessages.put("firstName", "First Name Cannot Be Empty");
		} 
		if (lastName == null || lastName.isBlank()) {

			errorCount++;
			errorMessages.put("lastName", "Last Name Cannot Be Empty");
		
		
		} 
		if (email == null || email.isBlank()) {
			errorCount++;
			errorMessages.put("email", "Email Cannot Be Empty");
		} 
		if (address == null || address.isBlank()) {
			
			errorCount++;
			errorMessages.put("address", "Address Cannot Be Empty");
		
		}
		
		if(errorCount > 0)
		{
			req.setAttribute("errorMessages", errorMessages);
			req.setAttribute("employee", employee);
			req.getRequestDispatcher("add-emp.jsp").forward(req, resp);
		}
		else {
			boolean res = false;
			try {
				res = employeeDao.findByEmail(email);

			} catch (Exception e) {
				System.out.println(e);
			}

			if (res) {
				// Repeated
				req.setAttribute("errorMsg", "Email already registered");
				req.getRequestDispatcher("add-emp.jsp").forward(req, resp);

			} else {
				
				// System.out.println(employee);
				try {
					int result = employeeDao.saveEmployee(employee);

					if (result > 0) {
						HttpSession session = req.getSession();
						session.setAttribute("successMsg", "Employee Saved Successfully");
						resp.sendRedirect(req.getContextPath() + "/employee/dashboard");
					} else {
						System.out.println("Something bad happen");
					}
				} catch (Exception e) {
					// TODO: handle exception

					System.out.println(e);
				}
			}

		}
	}
}
