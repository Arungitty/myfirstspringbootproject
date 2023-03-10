package com.spring.boot.mysql.connector.demo.models;


import java.sql.Timestamp;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown= true)
public class Worker implements Comparable<Worker> {
	
	// All are data member(Data members name same as column name of table worker) 
	Integer workerId;
	String firstName;
	String lastName;
	Integer salary;
	@JsonFormat(pattern="yyyy-MM-dd")
	Timestamp joiningDate;
	String department;
	
	
	public Worker() {      // Non-Parameterized constructor
		super();
	 }


	public Worker(Integer workerId, String firstName, String lastName, Integer salary, Timestamp joiningDate,
			String department) {                  
		super();
		this.workerId = workerId;                  
		this.firstName = firstName;
		this.lastName = lastName;
		this.salary = salary;
		this.joiningDate = joiningDate;
		this.department = department;
	}



    public Integer getWorkerId() {
		return workerId;
	}


    public void setWorkerId(Integer workerId) {
		this.workerId = workerId;
	}


    public String getFirstName() {
		return firstName;
	}

    
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


    public String getLastName() {
		return lastName;
	}


    public void setLastName(String lastName) {
		this.lastName = lastName;
	}

    
	public Integer getSalary() {
		return salary;
	}

    
	public void setSalary(Integer salary) {
		this.salary = salary;
	}


    public Timestamp getJoiningDate() {
		return joiningDate;
	}


	public void setJoiningDate(Timestamp joiningDate) {
		this.joiningDate = joiningDate;
	}


	public String getDepartment() {
		return department;
	}


    public void setDepartment(String department) {
		this.department = department;
	}

    
    @Override
    public int hashCode() {
    	return Objects.hash(department,workerId);
    }
	
    
    @Override
    public boolean equals(Object obj) {
    	if(this==obj)
    		return true;
    	if(obj==null)
    		return false;
    	if(getClass()!=obj.getClass())
    		return false;
        Worker other=(Worker)obj;
        return Objects.equals(department,other.department)&& Objects.equals(workerId,other.workerId);
    
    }

	@Override
	public int compareTo(Worker o) {
		if(this.workerId<o.workerId) {
			return -1;
		}
		if (this.workerId>o.workerId) {
			return 1;
		}
		if(this.equals(o)){
			return 0;
		}
	    
		return this.department.compareTo(o.department);
	}


    @Override
	public String toString() {
		return "Worker Details [workerId=" + workerId + ", firstName=" + firstName + ", lastName=" + lastName + ", salary="
				+ salary + ", joiningDate=" + joiningDate + ", department=" + department + "]";
	}


	

}
	

	

	

	
	
	
	
	
	

