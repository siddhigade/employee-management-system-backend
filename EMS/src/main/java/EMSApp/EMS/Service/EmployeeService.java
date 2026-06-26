package EMSApp.EMS.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import EMSApp.EMS.Dto.EmployeeDto;



public interface EmployeeService {
	  
	EmployeeDto createEmployee(EmployeeDto employeedto);
	
	EmployeeDto getEmployeeById(int employeeId);
	
	List<EmployeeDto> getAllEmployees();
	
	EmployeeDto updateEmployee(int employeeId ,EmployeeDto updatedEmployee);
	
	void deleteEmployee(int employeeId);
}
