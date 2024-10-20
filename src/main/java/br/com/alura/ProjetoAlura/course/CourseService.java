package br.com.alura.ProjetoAlura.course;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class CourseService {
	    @Autowired
	    private CourseRepository courseRepository;

	    public Course createCourse(NewCourseDTO newCourse) {
	        Course course = new Course();
			course.setDescription(newCourse.getDescription());
			course.setName(newCourse.getName());
			course.setCode(newCourse.getCode());
			course.setInstructorEmail(newCourse.getInstructorEmail());
			 return courseRepository.save(course);
	   }  
	    
	    public Optional<Course> deactivateCourse(String code) {
	        Optional<Course> courseOptional = courseRepository.findByCode(code);
	        if (courseOptional.isPresent()) {
	            Course course = courseOptional.get();
	            course.setStatus("inactive");
	            course.setInactivationDate(LocalDate.now()); 
	            courseRepository.save(course); 
	        }
	        return courseOptional;
	    }
}


