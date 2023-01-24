package com.spring.boot.mysql.connector.demo.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.boot.mysql.connector.demo.dao.WorkerDao;
import com.spring.boot.mysql.connector.demo.models.Worker;
import com.spring.boot.mysql.connector.demo.utils.DatabaseConnection;

@Repository
public class WorkerRepository implements WorkerDao {
     
	private final Connection connection;      // Data Member
    
	@Autowired
	public WorkerRepository(DatabaseConnection db) {        // Parameterized Constructor
		super();
		this.connection = db.getConnection();
	 }
	
	@Override
	public Integer createWorker(Worker worker) throws SQLException{
	
		String insertWorkerFormat= """
		   INSERT INTO Worker(WORKER_ID,FIRST_NAME,LAST_NAME,SALARY,JOINING_DATE,DEPARTMENT) Values(?,?,?,?,NOW(),?) """;
	      
	    try(PreparedStatement preparedStatement=connection.prepareStatement(insertWorkerFormat);){
	    	preparedStatement.setInt(1,worker.getWorkerId());
	    	preparedStatement.setString(2, worker.getFirstName());
	        preparedStatement.setString(3, worker.getLastName());
	        preparedStatement.setInt(4, worker.getSalary());
	        preparedStatement.setString(5, worker.getDepartment());
	        
	        return preparedStatement.executeUpdate();
	     }   
	  }
				
	@Override
	public Worker findWorkersById(Integer id) throws SQLException{
	
		String getWorkerFormat="""
			SELECT *
			FROM 
			    Worker
			WHERE
			   WORKER_ID=	
				? """;
	
	    try(PreparedStatement preparedStatement=connection.prepareStatement(getWorkerFormat);){
	    	
	    	preparedStatement.setInt(1, id);
	        ResultSet rs= preparedStatement.executeQuery();
	        if(!rs.isBeforeFirst()){
	        	return null;
	        }
	        
	       Worker worker= new Worker();
	       while(rs.next()) {
	    	   worker.setWorkerId(rs.getInt("WORKER_ID"));
	    	   worker.setFirstName(rs.getString("FIRST_NAME"));
	    	   worker.setLastName(rs.getString("LAST_NAME"));
	           worker.setSalary(rs.getInt("SALARY"));
	           worker.setJoiningDate(rs.getTimestamp("JOINING_DATE"));
	           worker.setDepartment(rs.getString("DEPARTMENT"));
	        }
	     
	       return worker;
	   }     
	}
	
    @Override()
	public Integer findWorkerCountByDepartment(String department) throws SQLException{
    	
    	String getWorkerCountByDepartment= """
    			SELECT COUNT (*)
    			   FROM
    			       Worker
    			   WHERE
    			       DEPARTMENT= ? """;    
    			
      try(PreparedStatement preparedStatement=connection.prepareStatement(getWorkerCountByDepartment);){
    	  
    	  preparedStatement.setString(1,department);
          ResultSet rs=preparedStatement.executeQuery();
          
          if(!rs.isBeforeFirst()) {
        	  return null;
          }
      
           rs.next();
           
           return rs.getInt(1);
      }
   }
	
	@Override
	public List<Worker> findWorkersByDepartment(String department) throws SQLException{
	
		String getDepartmentWorkersFormat="""
				SELECT *
				  FROM
				WORKER
				  WHERE
				   DEPARTMENT= ? """; 
		
	    try(PreparedStatement preparedStatement= connection.prepareStatement(getDepartmentWorkersFormat);){
	    	preparedStatement.setString(1, department);
	    	ResultSet rs= preparedStatement.executeQuery();
	   
			List<Worker>workers= new ArrayList<>();
	    
	    	if(!rs.isBeforeFirst()){
	    	  return workers;
	    	}
	    	   
	    	  while(rs.next()){
	    	    Worker worker= new Worker();
	    	    worker.setWorkerId(rs.getInt("WORKER_ID"));
	    	    worker.setFirstName(rs.getString("FIRST_NAME"));
	    	    worker.setLastName(rs.getString("LAST_NAME"));
	    	    worker.setSalary(rs.getInt("SALARY"));
	    	    worker.setJoiningDate(rs.getTimestamp("JOINING_DATE"));
	    	    worker.setDepartment(rs.getString("DEPARTMENT"));
	    	    workers.add(worker);
	    	  }
	    	    return workers;
	         }   
	   }	  
	     
	@Override
	public List<Worker> findAllWorkers() throws SQLException {
		
		String getAllWorkersFormat="""
				SELECT *
				  FROM
				WORKER
				   """; 
		
	    try(PreparedStatement preparedStatement= connection.prepareStatement(getAllWorkersFormat);){
	    	
	    	ResultSet rs= preparedStatement.executeQuery();
	   
			List<Worker>workers= new ArrayList<>();
	    
	    	if(!rs.isBeforeFirst()){
	    	  return workers;
	    	}
	    	   
	    	  while(rs.next()){
	    	    Worker worker= new Worker();
	    	    worker.setWorkerId(rs.getInt("WORKER_ID"));
	    	    worker.setFirstName(rs.getString("FIRST_NAME"));
	    	    worker.setLastName(rs.getString("LAST_NAME"));
	    	    worker.setSalary(rs.getInt("SALARY"));
	    	    worker.setJoiningDate(rs.getTimestamp("JOINING_DATE"));
	    	    worker.setDepartment(rs.getString("DEPARTMENT"));
	    	    workers.add(worker);
	    	  }
	    	    return workers;
	         }   
	    }
	
     @Override
	 public Integer updateWorker(Worker worker) throws SQLException{
		
		String updateWorkerFormat= """
				UPDATE Worker
				 SET
				  WORKER_ID= ?,
				  FIRST_NAME= ?,
				  LAST_NAME= ?,
				  SALARY= ?,
				  JOINING_DATE= ?,
				  DEPARTMENT= ?
				   WHERE
				    WORKER_ID= ? """;
				
		try(PreparedStatement preparedStatement= connection.prepareStatement(updateWorkerFormat);){
			preparedStatement.setInt(1,worker.getWorkerId());
		    preparedStatement.setString(2, worker.getFirstName());
		    preparedStatement.setString(3,worker.getLastName());
		    preparedStatement.setInt(4,worker.getSalary());
		    preparedStatement.setTimestamp(5,worker.getJoiningDate());
		    preparedStatement.setString(6, worker.getDepartment());
		    preparedStatement.setInt(7, worker.getWorkerId());
		    
		    return preparedStatement.executeUpdate(updateWorkerFormat);
		}
	} 
		 
	@Override
	public Integer updateSalaryByDepartment(String department,Integer bonousfactor) throws SQLException{
		
		String updateSalaryByDepartment= """
				UPDATE Worker
				 SET
				SALARY= SALARY * ?
				 WHERE
				DEPATMENT= ? """;
			 	
	  try(PreparedStatement preparedStatement= connection.prepareStatement(updateSalaryByDepartment);){
		  
		  preparedStatement.setInt(1,bonousfactor);
		  preparedStatement.setString(2,department);
	      
		  return preparedStatement.executeUpdate();
	  }
   }			
		 
    @Override
	public Integer deleteWorkerById(Integer workerId) throws SQLException{
		
		String deleteWorkerFormat= """
				DELETE FROM Worker
				  WHERE
				 WORKER_ID= ? """;
				
	   try(PreparedStatement preparedStatement= connection.prepareStatement(deleteWorkerFormat);){
		   
		   preparedStatement.setInt(1,workerId);
	    // preparedStatement.setInt(1,10);  
		   
		   return preparedStatement.executeUpdate();
	   }
	}

}	
		
		
		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

