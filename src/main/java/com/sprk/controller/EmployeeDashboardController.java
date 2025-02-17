package com.sprk.controller;

import java.io.IOException;
import java.util.List;

import javax.sql.DataSource;

import com.sprk.dao.EmployeeDao;
import com.sprk.model.Employee;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/employee/dashboard")
public class EmployeeDashboardController extends HttpServlet {
	
	@Resource(name = "sprk_emp")
	private DataSource dataSource;

	private EmployeeDao employeeDao;

	@Override
	public void init() throws ServletException {
		employeeDao = new EmployeeDao(dataSource);
	}
	
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Employee> employees = null;
		try {
			employees = employeeDao.getAllEmployees();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		req.setAttribute("employees", employees);
		
		req.getRequestDispatcher("/emp_dashboard.jsp").forward(req, resp);
	}
}
