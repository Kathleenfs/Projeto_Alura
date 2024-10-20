package br.com.alura.ProjetoAlura.course;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.projeto.course.Course;
import com.example.projeto.course.NewCourseDTO;
import com.example.projeto.courseRepository.CourseRepository;

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
}


