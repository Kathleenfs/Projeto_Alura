package br.com.alura.ProjetoAlura.registration;

import java.time.LocalDate;
import java.util.Optional;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alura.ProjetoAlura.course.Course;
import br.com.alura.ProjetoAlura.course.CourseRepository;
import br.com.alura.ProjetoAlura.user.UserRepository;


@Service
public class RegistrationService {
	
	@Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private RegistrationRepository registrationRepository;

    public String registerUser(NewRegistrationDTO newRegistrationDTO) {
    	Optional<Course> courseOptional = courseRepository.findByCode(newRegistrationDTO.getCourseCode());
        if (!courseOptional.isPresent()) {
            return "Course not found.";
        }

        Course course = courseOptional.get();

        if (!course.getStatus().equals("ACTIVE")) {
            return "The course is inactive, enrollment not allowed.";
        }

       
        Optional<User>userOptional = userRepository.findByEmail(newRegistrationDTO.getStudentEmail());
        
        User user  = userOptional.get();

      
        if (registrationRepository.existsByUserAndCourse(user,course)) {
            return "The student is already enrolled in this course.";
        }

        Registration registration = new Registration();
        registration.setUser(user);
        registration.setCourse(course);
        registration.setEnrollmentDate(LocalDate.now());
        registrationRepository.save(registration);
        return "Student successfully enrolled!";
        }

}
