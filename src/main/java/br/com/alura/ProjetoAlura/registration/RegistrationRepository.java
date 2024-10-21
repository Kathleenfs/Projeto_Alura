package br.com.alura.ProjetoAlura.registration;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.alura.ProjetoAlura.course.Course;
import br.com.alura.ProjetoAlura.user.User;



public interface RegistrationRepository  extends JpaRepository<Registration, Long> {
	boolean existsByUserAndCourse(User user,Course course);
	
	  @Query(value = "SELECT c.name AS courseName, c.code AS courseCode, u.name AS instructorName, " +
              "u.email AS instructorEmail, COUNT(r.id) AS totalRegistrations " +
              "FROM registration r " +
              "JOIN courses c ON r.course_id = c.id " +
              "JOIN user u ON u.email = c.instructorEmail " +
              "WHERE u.role = 'instructor' " +
              "GROUP BY c.name, c.code, u.name, u.email " +
              "ORDER BY COUNT(r.id) DESC", nativeQuery = true)

     List<Object[]> findCoursesWithMostRegistrations();

	}
