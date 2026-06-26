package EMSApp.EMS.Serviceimpl;

import EMSApp.EMS.Dto.EmployeeDto;
import EMSApp.EMS.Service.ChatService;
import EMSApp.EMS.tools.EmployeeTool;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChatServiceImpl implements ChatService {

    private final ChatClient chatClient;
    private final EmployeeTool employeeTool;

    @Autowired
    public ChatServiceImpl(ChatClient.Builder chatClientBuilder, EmployeeTool employeeTool) {
        this.employeeTool = employeeTool;
        this.chatClient = chatClientBuilder.build();
    }

    @Override
    public String askAboutEmployees(String userQuestion, List<EmployeeDto> employees) {

        String employeeData = employees.stream()
            .map(emp -> String.format(
                "ID: %d, Name: %s %s, Email: %s, Designation: %s, Salary: %d",
                emp.getId(), emp.getFirstName(), emp.getLastName(),
                emp.getEmail(), emp.getDesignation(), emp.getSalary()
            ))
            .collect(Collectors.joining("\n"));

        String prompt = """
            You are an HR assistant for an Employee Management System.
            You can answer questions about employee data, and you can also
            create new employees or update salaries when asked to do so.
            Use the employee data below to answer questions accurately.
            If asked to perform an action (like adding an employee), use the
            available tools rather than just describing what you would do.

            EMPLOYEE DATA:
            %s

            USER REQUEST:
            %s
            """.formatted(employeeData, userQuestion);

        // ✅ .tools() registers your @Tool-annotated methods with this call
        return chatClient.prompt()
                .user(prompt)
                .tools(employeeTool)
                .call()
                .content();
    }
}