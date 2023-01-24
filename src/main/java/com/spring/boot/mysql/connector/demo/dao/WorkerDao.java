package com.spring.boot.mysql.connector.demo.dao;

import java.sql.SQLException;
import java.util.List;

import com.spring.boot.mysql.connector.demo.models.Worker;

public interface WorkerDao {
  
	// create operation
	Integer createWorker(Worker worker) throws SQLException;
	
	// Read/Retrieve operations
	Worker findWorkersById(Integer id) throws SQLException;
	Integer findWorkerCountByDepartment(String department) throws SQLException;
	List<Worker> findWorkersByDepartment(String department) throws SQLException;
	List<Worker> findAllWorkers() throws SQLException;
	
	// Update operations
	Integer updateWorker(Worker worker) throws SQLException;
	Integer updateSalaryByDepartment(String department,Integer bonousFactor) throws SQLException;
	
	// Delete operations
	Integer deleteWorkerById(Integer id) throws SQLException;

	
}



