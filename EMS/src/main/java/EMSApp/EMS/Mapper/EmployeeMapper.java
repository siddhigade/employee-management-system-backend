package EMSApp.EMS.Mapper;

import EMSApp.EMS.Dto.EmployeeDto;
import EMSApp.EMS.entities.Employee;

public class EmployeeMapper {

    public static EmployeeDto mapToEmployeeDto(Employee employee) {
        return new EmployeeDto(
            employee.getId(),
            employee.getFirstName(),     // ✅ capital F
            employee.getLastName(),      // ✅ capital L
            employee.getEmail(),
            employee.getDesignation(),
            employee.getSalary()
        );
    }

    public static Employee mapToEmployee(EmployeeDto employeedto) {
        return new Employee(
            employeedto.getId(),
            employeedto.getFirstName(),  // ✅ capital F
            employeedto.getLastName(),   // ✅ capital L
            employeedto.getEmail(),
            employeedto.getDesignation(),
            employeedto.getSalary()
        );
    }
}