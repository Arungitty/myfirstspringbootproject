package com.spring.boot.mysql.connector.demo.controllers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.boot.mysql.connector.demo.models.Worker;
import com.spring.boot.mysql.connector.demo.service.WorkerService;

@RestController
@RequestMapping("/api/worker")
public class WorkerController {
    
	@Autowired
	private WorkerService workerService;

	public WorkerController() {
		super();
	 }
	
	@PostMapping("/create")
	public String create(@RequestBody Worker worker) {
		
		  try {
			if(Boolean.TRUE.equals(this.workerService.addWorker(worker))) {
				return "New worker record added successfully";
			}
		} catch(SQLException ex){
			System.out.println("Exception occured while inserting a new worker record!/n" +ex);
		}
		
		 return "Failed to insert a new worker record";
      }	 
		
	// now do with the help of ResponseEntity object we convert our normal controller method into production grade control method
//	@PostMapping("/create")
//	public ResponseEntity<Worker> create(@RequestBody Worker worker) throws SQLException{
		
	//	try {
	//		if(Boolean.TRUE.equals(this.workerService.addWorker(worker))) {
	//			return new ResponseEntity<>(worker,HttpStatus.CREATED);
	//		}
	//	 } catch(SQLException ex){
	//		 System.out.println("Exception occured while inserting a new worker record!/n" +ex);
	//	 }
		   
	//	return new ResponseEntity<>(worker,HttpStatus.INTERNAL_SERVER_ERROR);
//	 }
	
	@GetMapping("/all")
 	public String getAll() {
		
		try {
			List<Worker> workers= this.workerService.getAllWorkers();
		 
	       return workers.isEmpty() ? "Empty set" : workers.toString();
		 } catch(SQLException ex) {
			System.out.println("Exception occured while fetching all worker record!/n" +ex);
		    }
	
		  return "Something went wrong while fetching all workers records";
	  }  
		  
  //  @GetMapping("/all")
  //  public ResponseEntity<List<Worker>> getAll(){
   // 	try {
    //		List<Worker> workers= this.workerService.getAllWorkers();
    	    
    	//	return workers.isEmpty() ? new ResponseEntity<>(null,HttpStatus.NO_CONTENT) : new ResponseEntity<>(workers,HttpStatus.OK);
    //	 } catch(SQLException ex){
    //		System.out.println("Exception occured while fetching all worker records!/n" +ex);
    //		return  new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
   // 	   }
 //    }		
    	
    @GetMapping("/all")
    public ResponseEntity<List<Worker>> getAllWorker(@RequestParam(required=false) String department){
    	try {
    		List<Worker> workers= new ArrayList<>();
    		
    		if(department== null){
    			workers= this.workerService.getAllWorkers();
    		 } else {
    			 workers= this.workerService.getWorkersByDepartment(department);
   		        }
    		System.out.println(workers.toString());		
    	    return workers.isEmpty() ? new ResponseEntity<>(HttpStatus.NO_CONTENT) 
    	    		: new ResponseEntity<>(workers,HttpStatus.OK);
   	    } catch(Exception ex){
    		System.out.println("Exception occur while fetching worker records!/n" +ex);
    	    return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
     	}
     }	
    	
    @GetMapping("/getid")
    public String get(Integer id){
    	try {
    		Worker worker=this.workerService.getWorker(id);
    		return worker== null ? "Empty set!"	: worker.toString();
    		} catch(SQLException ex){
    		 System.out.println("Exception occured while fetching the record of worker!" +  id + "/n" +ex);
    	   }
           
    	return "Something went wrong while fetching the record of worker!/n" +id ;
    }	  
    
    @PostMapping("/update")
    public String update(Integer id,String department){
    	try {
    		if(Boolean.TRUE.equals(this.workerService.updateWorker(id,department))) {
    	    return String.format("Record of worker id updated successfully", id);
    		 }
    		} catch(SQLException ex){
    		 return  String.format("Exception occured while updating the record of worker whose id is:-" +id +ex);
    	 }
    
       return String.format("Failed to update the record of worker whose id is:-", id);
    }	
   
    @PostMapping("/delete")
    public String delete(Integer id){
    	try {
    		if(Boolean.TRUE.equals(this.workerService.deleteWorker(id))) {
    	    return String.format("Record of worker id deleted successfully", id);
    		 }
    		} catch(SQLException ex){
    		 return  String.format("Exception occured while deleting the record of worker whose id is:-" +id +ex);
    	 }
     
       return String.format("Failed to delete the record of worker whose id is:-", id);
    }

}


	










