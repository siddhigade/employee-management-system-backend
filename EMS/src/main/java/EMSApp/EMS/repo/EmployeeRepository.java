package EMSApp.EMS.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import EMSApp.EMS.entities.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
