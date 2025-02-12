package com.sprk.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@Getter
//@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
	
	private int empId;
	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private String gender;
	
	private String address;

}
