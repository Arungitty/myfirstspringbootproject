package com.spring.boot.mysql.connector.demo.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.boot.mysql.connector.demo.dao.WorkerDao;
import com.spring.boot.mysql.connector.demo.models.Worker;

@Service
public class WorkerService {
    
	@Autowired
	private WorkerDao workerRepository;

	
	public WorkerService() {
		super();
	}

	public Boolean addWorker(Worker worker) throws SQLException{
    	
    	return  this.workerRepository.createWorker(worker) > 0;
    }
	
	
	public Worker getWorker(Integer id) throws SQLException{
		
		return this.workerRepository.findWorkersById(id);
	}
	
	
	public List<Worker> getAllWorkers() throws SQLException{
		
		return this.workerRepository.findAllWorkers();
	}
	
	
	public List<Worker> getWorkersByDepartment(String department) throws SQLException{
		
		return this.workerRepository.findWorkersByDepartment(department);
	}
	
	
	public Boolean updateWorker(Integer id, String department) throws SQLException{
		
		Worker worker= this.workerRepository.findWorkersById(id);
		worker.setDepartment(department);
	   
		return this.workerRepository.updateWorker(worker) > 0;
	}
	
	public Integer updateDepartmentWorkerSalaries(String department,Integer bonousFactor) throws SQLException{
		
		Integer recordsUpdated= this.workerRepository.updateSalaryByDepartment(department,bonousFactor);
	    Integer workerCountInDepartment= this.workerRepository.findWorkerCountByDepartment(department);
	    
	    return (recordsUpdated == 0) ? -1 : workerCountInDepartment - recordsUpdated;
	}
	
	public Boolean deleteWorker(Integer id) throws SQLException{
		
		Integer recordsDeleted= this.workerRepository.deleteWorkerById(id);
		
		return recordsDeleted > 0;
	}
	
}	
	
	
	

