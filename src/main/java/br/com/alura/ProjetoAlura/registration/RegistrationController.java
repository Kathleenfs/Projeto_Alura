package br.com.alura.ProjetoAlura.registration;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RegistrationController {
	@Autowired
	private RegistrationService registrationService;
	

    @PostMapping("/registration/new")
    public ResponseEntity<String> registerUser(@Valid @RequestBody NewRegistrationDTO newRegistration) {
    	String res = registrationService.registerUser(newRegistration);
    	if (res.equals("Student successfully enrolled!")) {
    		return ResponseEntity.ok(res);
            } else {
                return ResponseEntity.badRequest().body(res);
                }
    	}

    @GetMapping("/registration/report")
    public ResponseEntity<List<RegistrationReportItem>> report() {
        List<RegistrationReportItem> report = registrationService.getCoursesWithMostRegistrations();
        return ResponseEntity.ok(report);
    	}
    }


