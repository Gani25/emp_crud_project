package com.sprk.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.sql.DataSource;

import com.sprk.model.Employee;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EmployeeDao {

	private DataSource dataSource;

	public EmployeeDao(DataSource dataSource) {

		this.dataSource = dataSource;
	}

	public Connection testConnection() throws SQLException {
		Connection conn = dataSource.getConnection();

		return conn;
	}

	public int saveEmployee(Employee employee) throws Exception {

		Connection conn = dataSource.getConnection();

		String sql = "insert into employee(first_name, last_name, email,gender,address) values (?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);

		ps.setString(1, employee.getFirstName());
		ps.setString(2, employee.getLastName());
		ps.setString(3, employee.getEmail());
		ps.setString(4, employee.getGender());
		ps.setString(5, employee.getAddress());

		int result = ps.executeUpdate();

		closeAll(ps, conn, null);

		return result;
	}

	public Employee findByEmpId(int empId) throws Exception {

		Connection conn = dataSource.getConnection();

		PreparedStatement ps = conn.prepareStatement("Select * from employee where emp_id = ?");

		ps.setInt(1, empId);

		ResultSet rs = ps.executeQuery();

		Employee employee = new Employee();
		if (rs.next()) {
			employee.setEmpId(rs.getInt(1));
			employee.setFirstName(rs.getString(2));
			employee.setLastName(rs.getString("last_name"));
			employee.setEmail(rs.getString("email"));
			employee.setGender(rs.getString("gender"));
			employee.setAddress(rs.getString("address"));
		}
		closeAll(ps, conn, rs);
		return employee;

	}

	public boolean findByEmail(String email) throws Exception {

		Connection conn = dataSource.getConnection();

		PreparedStatement ps = conn.prepareStatement("Select * from employee where email = ?");

		ps.setString(1, email);

		ResultSet rs = ps.executeQuery();

		boolean result = false;
		if (rs.next()) {
			result = true;
		}
		closeAll(ps, conn, rs);
		return result;

	}

	public List<Employee> getAllEmployees() throws Exception {
		Connection conn = dataSource.getConnection();

		PreparedStatement ps = conn.prepareStatement("Select * from employee");

		ResultSet rs = ps.executeQuery();

		List<Employee> employees = new LinkedList<>();

		while (rs.next()) {
			Employee employee = new Employee();

			employee.setEmpId(rs.getInt(1));
			employee.setFirstName(rs.getString(2));
			employee.setLastName(rs.getString("last_name"));
			employee.setEmail(rs.getString("email"));
			employee.setGender(rs.getString("gender"));
			employee.setAddress(rs.getString("address"));
			employees.add(employee);
		}

		return employees;

	}

	private void closeAll(PreparedStatement ps, Connection conn, ResultSet rs) throws Exception {
		if (ps != null) {
			ps.close();
		}
		if (rs != null) {
			rs.close();
		}
		if (conn != null) {
			conn.close();
		}
	}

	public void deleteEmployeeById(int empId) throws Exception {

		Connection conn = dataSource.getConnection();

		String sql = "delete from employee where emp_id = ?";
		PreparedStatement ps = conn.prepareStatement(sql);

		ps.setInt(1, empId);
		ps.executeUpdate();

		closeAll(ps, conn, null);

	}

}
