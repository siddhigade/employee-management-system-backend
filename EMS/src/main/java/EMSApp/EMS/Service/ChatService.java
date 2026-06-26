package EMSApp.EMS.Service;



import EMSApp.EMS.Dto.EmployeeDto;

import java.util.List;

public interface ChatService {
    String askAboutEmployees(String userQuestion, List<EmployeeDto> employees);
}
