package in.javed.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.javed.model.Student;
import in.javed.service.StudentService;

@RestController
public class StudentController {
	
	@Autowired
	private StudentService service;
	
	//1.find all
	@GetMapping("/all")
	public ResponseEntity<?> saveStudents(){
		ResponseEntity<?> resp=null;
		try {
			List<Student> list=service.findAllStudents();
			resp=new ResponseEntity<List<Student>>(list,HttpStatus.OK);
			
		} catch (Exception e) {
			resp=new ResponseEntity<String>("Unable to fetch Data",HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
			
		}
		return resp;
	}
	
	
	//.2 save record
	@PostMapping("/save")
	public ResponseEntity<String> saveStudent(@RequestBody Student student){
		
		ResponseEntity<String> resp=null;
		try {
			Integer id=service.saveStudent(student);
			resp=new ResponseEntity<String>("Saved with id:"+id,HttpStatus.OK);
		}catch(Exception e) {
			resp=new ResponseEntity<String>("Unable to Save Data",HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return resp;
	}
	
	
	@GetMapping("/one/{id}")
	public ResponseEntity<?> findOne(@PathVariable("id") Integer id){
		
		ResponseEntity<?> resp=null;
		try {
		     Student s = service.findOneStudent(id);
		     if(s!=null) {
		        resp=new ResponseEntity<Student>(s,HttpStatus.OK);  	
		        }
		     
		     else {
		    	 resp=new ResponseEntity<String>("No Studnet Exists with id",HttpStatus.OK);
		     }  
		} catch (Exception e) {
			resp=new ResponseEntity<String>("Unable to fetch Data",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return resp;
		
	}
	
	
	
	//4.delete
	@GetMapping("/remove/{id}")
	public ResponseEntity<String> deleteById(@PathVariable("id")Integer id){
		ResponseEntity<String> resp=null;
		try {
			boolean exist=service.isStudentExist(id);
			if(exist) {
				service.deleteStudent(id);
				resp=new ResponseEntity<String>("Student Deleted:"+id,HttpStatus.OK);
			}
			else {
				resp=new ResponseEntity<String>("Student not exitst",HttpStatus.BAD_REQUEST);
			}
		}catch(Exception e) {
			resp=new ResponseEntity<String>("Unable to find Student",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return resp;
		
	}
	
	
	
	//5.update the record
	@PutMapping("/update")
	public ResponseEntity<String> updateStudent(@RequestBody Student student){
		ResponseEntity<String> resp=null;
		try {
			
		
		Integer stdId=student.getStdId();
		boolean exist=service.isStudentExist(stdId);
		if(exist) {
			service.updateStudent(student);
			resp=new ResponseEntity<String>("Student Updated",HttpStatus.OK);
		}else {
			resp=new ResponseEntity<String>("Student not Exist",HttpStatus.BAD_REQUEST);
		}
		}catch(Exception e) {
			resp=new ResponseEntity<String>("Unable to find Student",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return resp;
		
	}
	
	
	

}
