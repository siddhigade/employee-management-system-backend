package EMSApp.EMS.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import EMSApp.EMS.Dto.EmployeeDto;
import EMSApp.EMS.Service.EmployeeService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
     
	@Autowired
	private EmployeeService employeeservice;
     
     @PostMapping
      public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeedto){
    	 System.out.println("RECEIVED DTO: id=" + employeedto.getId() 
         + ", salary=" + employeedto.getSalary());
    	  EmployeeDto savedEmployee=employeeservice.createEmployee(employeedto);
    	  return new ResponseEntity<>(savedEmployee,HttpStatus.CREATED);
      }

     @GetMapping("{id}")
     public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable("id") int employeeId){
    	 EmployeeDto employeedto=employeeservice.getEmployeeById(employeeId);
    	  return ResponseEntity.ok(employeedto);
     }
     
     @GetMapping
     public ResponseEntity<List<EmployeeDto>> getAllEmployees(){
    	 System.out.println("GET ALL EMPLOYEES CALLED");

    	 List<EmployeeDto> employees=employeeservice.getAllEmployees();
    	 
    	 return ResponseEntity.ok(employees);
     }
     
     @PutMapping("{id}")
     public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable("id") int employeeId,@RequestBody EmployeeDto updatedEmployee){
    	 
    	 EmployeeDto employeedto=employeeservice.updateEmployee(employeeId, updatedEmployee);
    	 return ResponseEntity.ok(employeedto);
     }
     
     @DeleteMapping("{id}")
     public ResponseEntity<String> deleteEmployee(@PathVariable("id") int employeeId){
    	 employeeservice.deleteEmployee(employeeId);
         return ResponseEntity.ok("employee deleted");
     }
}

