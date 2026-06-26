package EMSApp.EMS.tools;



import EMSApp.EMS.Dto.EmployeeDto;
import EMSApp.EMS.Service.EmployeeService;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmployeeTool{

    @Autowired
    private EmployeeService employeeService;

    @Tool(description = "Create a new employee record in the system")
    public String createEmployee(
            @ToolParam(description = "Employee's first name") String firstName,
            @ToolParam(description = "Employee's last name") String lastName,
            @ToolParam(description = "Employee's email address") String email,
            @ToolParam(description = "Employee's job designation, e.g. Java Developer") String designation,
            @ToolParam(description = "Employee's salary as a whole number") Integer salary
    ) {
        // ✅ Validation BEFORE touching the database - this is the safety check we discussed
        if (salary == null || salary <= 0 || salary > 10000000) {
            return "Error: Invalid salary amount. Could not create employee.";
        }
        if (firstName == null || firstName.isBlank() || lastName == null || lastName.isBlank()) {
            return "Error: First name and last name are required.";
        }

        EmployeeDto dto = new EmployeeDto(null, firstName, lastName, email, designation, salary);
        EmployeeDto saved = employeeService.createEmployee(dto);

        return "Successfully created employee: " + saved.getFirstName() + " " + saved.getLastName()
                + " (ID: " + saved.getId() + ")";
    }

    @Tool(description = "Update the salary of an existing employee by their ID")
    public String updateSalary(
            @ToolParam(description = "The employee's ID") Integer employeeId,
            @ToolParam(description = "The new salary amount") Integer newSalary
    ) {
        if (newSalary == null || newSalary <= 0) {
            return "Error: Invalid salary amount.";
        }

        try {
            EmployeeDto existing = employeeService.getEmployeeById(employeeId);
            existing.setSalary(newSalary);
            employeeService.updateEmployee(employeeId, existing);
            return "Successfully updated salary for employee ID " + employeeId + " to " + newSalary;
        } catch (Exception e) {
            return "Error: Could not find employee with ID " + employeeId;
        }
    }
}