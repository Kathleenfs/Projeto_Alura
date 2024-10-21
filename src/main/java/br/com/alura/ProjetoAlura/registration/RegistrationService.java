package br.com.alura.ProjetoAlura.registration;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alura.ProjetoAlura.NewRegistrationDTO;
import br.com.alura.ProjetoAlura.Registration;
import br.com.alura.ProjetoAlura.RegistrationReportItem;
import br.com.alura.ProjetoAlura.RegistrationRepository;

import br.com.alura.ProjetoAlura.course.Course;
import br.com.alura.ProjetoAlura.course.CourseRepository;
import br.com.alura.ProjetoAlura.user.UserRepository;
import br.com.alura.ProjetoAlura.user.User;


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
    
    public List<RegistrationReportItem> getCoursesWithMostRegistrations() {
        List<Object[]> results = registrationRepository.findCoursesWithMostRegistrations();
        List<RegistrationReportItem> report = new ArrayList<>();

        for (Object[] result : results) {
            String courseName = (String) result[0];
            String courseCode = (String) result[1];
            String instructorName = (String) result[2];
            String instructorEmail = (String) result[3];
            Long totalRegistrations = ((Number) result[4]).longValue();

            report.add(new RegistrationReportItem(
                courseName,
                courseCode,
                instructorName,
                instructorEmail,
                totalRegistrations
            ));
            }
        return report;
        }
  }
