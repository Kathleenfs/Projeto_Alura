package br.com.alura.ProjetoAlura.course;

import jakarta.validation.Valid;

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
    public ResponseEntity createCourse(@PathVariable("code") String courseCode) {
        // TODO: Implementar a Questão 2 - Inativação de Curso aqui...

        return ResponseEntity.ok().build();
    }

}
