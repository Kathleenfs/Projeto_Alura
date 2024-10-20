package br.com.alura.ProjetoAlura.course;

import jakarta.validation.Valid;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class CourseController {

    
    @Autowired
	private CourseService courseService;

    @PostMapping("/course/new")
	public ResponseEntity<Course> createCourse(@Valid @RequestBody NewCourseDTO newCourse) {
		Course course = courseService.createCourse(newCourse);
		return ResponseEntity.ok(course);
	}

    @PostMapping("/course/{code}/inactive")
	public ResponseEntity<Course> deactivateCourse(@PathVariable("code") String courseCode) {
	Optional<Course> courseOptional = courseService.deactivateCourse(courseCode);
    if (courseOptional.isPresent()) {
    	return ResponseEntity.ok(courseOptional.get());
    } else {
    	return ResponseEntity.notFound().build();
    	}
    }

}
