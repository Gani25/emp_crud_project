package com.sprk.controller;

import java.io.IOException;

import javax.sql.DataSource;

import com.sprk.dao.EmployeeDao;
import com.sprk.model.Employee;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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

		// System.out.println(employee);
		try {
			int result = employeeDao.saveEmployee(employee);
			
			if(result > 0) {
				resp.sendRedirect(req.getContextPath()+"/emp_dashboard.jsp");
			}else {
				System.out.println("Something bad happen");
			}
		} catch (Exception e) {
			// TODO: handle exception
			
			System.out.println(e);
		}
	}

}
