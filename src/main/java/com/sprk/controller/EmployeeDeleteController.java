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
import jakarta.servlet.http.HttpSession;

@WebServlet("/employee/delete")
public class EmployeeDeleteController extends HttpServlet {

	@Resource(name = "sprk_emp")
	private DataSource dataSource;

	private EmployeeDao employeeDao;

	@Override
	public void init() throws ServletException {
		employeeDao = new EmployeeDao(dataSource);
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String empIdStr = req.getParameter("empId");
		HttpSession session = req.getSession();
		
		if(empIdStr.matches("^\\d+$"))
		{
			// Check for correct ID
			int empId = Integer.parseInt(empIdStr);
			Employee existingEmployee = null;
			try {
				existingEmployee = employeeDao.findByEmpId(empId);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(existingEmployee.getEmpId() > 0) {
				// Delete Logic
				try {
					employeeDao.deleteEmployeeById(empId);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				session.setAttribute("successMsg", "Employee Deleted Successfully");
				resp.sendRedirect(req.getContextPath() + "/employee/dashboard");
			}else {
				String message = "Employee with emp id: "+empId+" not found";
				session.setAttribute("errMsg", message);
				resp.sendRedirect(req.getContextPath() + "/employee/dashboard");
				
			}
			
		}else {
			
			session.setAttribute("errMsg", "Id can only be digits");
			resp.sendRedirect(req.getContextPath() + "/employee/dashboard");
			
			
		}
	}
}
