package com.app.bank.model;

public class Employee {
	
	private String employee_id;
	private String first_name;
	private String last_name;
	
	public Employee(String employee_id, String first_name, String last_name) {
		super();
		this.employee_id = employee_id;
		this.first_name = first_name;
		this.last_name = last_name;
		
	}

	public String getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(String employee_id) {
		this.employee_id = employee_id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	@Override
	public String toString() {
		return first_name + " " + last_name;
	}
	
	@Override
	public boolean equals(Object o) {
	    if (this == o) return true;
	    if (o == null || getClass() != o.getClass()) return false;
	    Employee that = (Employee) o;
	    return employee_id.equals(that.employee_id) &&
	      first_name.equals(that.first_name) &&
	      last_name.equals(that.last_name);
	}
	
}
