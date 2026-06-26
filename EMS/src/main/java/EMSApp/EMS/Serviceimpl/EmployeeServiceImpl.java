package EMSApp.EMS.Serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import EMSApp.EMS.Dto.EmployeeDto;
import EMSApp.EMS.Mapper.EmployeeMapper;
import EMSApp.EMS.Service.EmployeeService;
import EMSApp.EMS.entities.Employee;
import EMSApp.EMS.repo.EmployeeRepository;
import exception.ResourceNotFoundException;


@Service
public class EmployeeServiceImpl implements EmployeeService{
	
    @Autowired
   private EmployeeRepository employeerepo;
   
   @Override
   public EmployeeDto createEmployee(EmployeeDto employeedto) {
 	  Employee employee =EmployeeMapper.mapToEmployee(employeedto);
 	  Employee saveEmployee=employeerepo.save(employee);
 	  return EmployeeMapper.mapToEmployeeDto(saveEmployee);
 	  
   }
	
	@Override
	public EmployeeDto getEmployeeById( int employeeId) {
		Employee employee=employeerepo.findById(employeeId).orElseThrow(() -> new ResourceNotFoundException("employee with given id not found:"+employeeId));
		     return EmployeeMapper.mapToEmployeeDto(employee);
	}

	@Override
	public List<EmployeeDto> getAllEmployees() {
		List<Employee> employees=employeerepo.findAll();
		return employees.stream().map((employee) -> EmployeeMapper.mapToEmployeeDto(employee)).collect(Collectors.toList());
	}

	@Override
	public EmployeeDto updateEmployee(int employeeId, EmployeeDto updatedEmployee) {
	    Employee employee = employeerepo.findById(employeeId)
	        .orElseThrow(() -> new ResourceNotFoundException("employee with given id not found:" + employeeId));
	    
	    employee.setFirstName(updatedEmployee.getFirstName());  
	    employee.setLastName(updatedEmployee.getLastName());     
	    employee.setEmail(updatedEmployee.getEmail());
	    employee.setDesignation(updatedEmployee.getDesignation());
	    employee.setSalary(updatedEmployee.getSalary());
	    
	    Employee updatedEmployeeObj = employeerepo.save(employee);
	    return EmployeeMapper.mapToEmployeeDto(updatedEmployeeObj);
	}

	@Override
	public void deleteEmployee(int employeeId){
		Employee employee=employeerepo.findById(employeeId).orElseThrow(() -> new ResourceNotFoundException("employee with given id not found:"+employeeId));
		employeerepo.deleteById(employeeId);
		
	}
}
