package com.sprk.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

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
	
	

}
