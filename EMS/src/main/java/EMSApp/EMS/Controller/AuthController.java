package EMSApp.EMS.Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import EMSApp.EMS.Serviceimpl.UserServiceImpl;
import EMSApp.EMS.entities.User;
import EMSApp.EMS.repo.UserRepository;
import EMSApp.EMS.util.JwtUtil;






import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import EMSApp.EMS.entities.User;
import EMSApp.EMS.repo.UserRepository;
import EMSApp.EMS.util.JwtUtil;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserServiceImpl userService;

    // ✅ REGISTER (with role)
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {

        // default role if not provided
        if (user.getRole() == null) {
            user.setRole("USER");
        }

        User savedUser = userService.saveUser(user);

        return ResponseEntity.ok(savedUser);
    }

    // ✅ LOGIN (with role inside token)
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {

        User existingUser = userRepository.findByUsername(user.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!userService.checkPassword(user.getPassword(), existingUser.getPassword())) {
        	System.out.println("Entered Password = " + user.getPassword());
        	System.out.println("DB Password = " + existingUser.getPassword());

        	boolean match = userService.checkPassword(
        	        user.getPassword(),
        	        existingUser.getPassword()
        	);

        	System.out.println("Password Match = " + match);
            throw new RuntimeException("Invalid password");
        }

        // 🔥 include role in token
        String token = jwtUtil.generateToken(existingUser.getUsername(), existingUser.getRole());

        return ResponseEntity.ok(Map.of(
                "token", token,
                "role", existingUser.getRole(),
                "username", existingUser.getUsername()
        ));
    }
}