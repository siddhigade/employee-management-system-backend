package EMSApp.EMS.Controller;

import EMSApp.EMS.Dto.ChatRequest;
import EMSApp.EMS.Dto.ChatResponse;
import EMSApp.EMS.Dto.EmployeeDto;
import EMSApp.EMS.Service.ChatService;
import EMSApp.EMS.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<ChatResponse> chat(@RequestBody ChatRequest request) {

        // ✅ Step 1: Fetch real employee data from your database
        List<EmployeeDto> employees = employeeService.getAllEmployees();

        // ✅ Step 2: Pass data + question to the AI service
        String aiReply = chatService.askAboutEmployees(request.getMessage(), employees);

        // ✅ Step 3: Return the reply
        return ResponseEntity.ok(new ChatResponse(aiReply));
    }
}