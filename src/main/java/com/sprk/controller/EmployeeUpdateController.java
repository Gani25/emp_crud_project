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

@WebServlet("/employee/update")
public class EmployeeUpdateController extends HttpServlet {

	@Resource(name = "sprk_emp")
	private DataSource dataSource;

	private EmployeeDao employeeDao;

	@Override
	public void init() throws ServletException {
		employeeDao = new EmployeeDao(dataSource);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String empIdStr = req.getParameter("empId");
		HttpSession session = req.getSession();

		if (empIdStr.matches("^\\d+$")) {
			// Check for correct ID
			int empId = Integer.parseInt(empIdStr);
			Employee existingEmployee = null;
			try {
				existingEmployee = employeeDao.findByEmpId(empId);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (existingEmployee.getEmpId() > 0) {
				// SHow Update Form With prefilled Values in form
				session.setAttribute("existingEmp", existingEmployee);
				req.setAttribute("employee", existingEmployee);
				req.getRequestDispatcher("/update-form.jsp").forward(req, resp);
			} else {
				String message = "Employee with emp id: " + empId + " not found";
				session.setAttribute("errMsg", message);
				resp.sendRedirect(req.getContextPath() + "/employee/dashboard");

			}

		} else {

			session.setAttribute("errMsg", "Id can only be digits");
			resp.sendRedirect(req.getContextPath() + "/employee/dashboard");

		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String firstName = req.getParameter("first_name");
		String lastName = req.getParameter("last_name");
		String email = req.getParameter("email");
		String gender = req.getParameter("gender");
		String address = req.getParameter("address");
		Employee sessionEmployee = (Employee) session.getAttribute("existingEmp");
//		System.out.println(sessionEmployee);
		int empId = sessionEmployee.getEmpId();
//		System.out.println("Id: " + empId);

		Employee employee = new Employee(empId, firstName, lastName, email, gender, address);
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

		if (errorCount > 0) {
			req.setAttribute("errorMessages", errorMessages);
			req.setAttribute("employee", employee);
			req.getRequestDispatcher("/update-form.jsp").forward(req, resp);
		} else {

			// System.out.println(employee);
			try {
				int result = employeeDao.updateEmployee(employee);

				if (result > 0) {

					session.setAttribute("successMsg", "Employee Updated Successfully");
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
