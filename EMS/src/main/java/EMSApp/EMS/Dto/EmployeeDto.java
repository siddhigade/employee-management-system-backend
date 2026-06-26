package EMSApp.EMS.Dto;

public class EmployeeDto {

    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String designation;
    private Integer salary;

    public Integer getId() {                    // ✅ Integer, not int
        return id;
    }
    public void setId(Integer id) {              // ✅ Integer, not int
        this.id = id;
    }
    public String getFirstName() {                // ✅ capital F
        return firstName;
    }
    public void setFirstName(String firstName) {  // ✅ capital F
        this.firstName = firstName;
    }
    public String getLastName() {                 // ✅ capital L
        return lastName;
    }
    public void setLastName(String lastName) {    // ✅ capital L
        this.lastName = lastName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getDesignation() {
        return designation;
    }
    public void setDesignation(String designation) {
        this.designation = designation;
    }
    public Integer getSalary() {                  // ✅ Integer, not int
        return salary;
    }
    public void setSalary(Integer salary) {        // ✅ Integer, not int
        this.salary = salary;
    }

    public EmployeeDto(Integer id, String firstName, String lastName, String email, String designation, Integer salary) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.designation = designation;
        this.salary = salary;
    }

    public EmployeeDto() {
    }
}