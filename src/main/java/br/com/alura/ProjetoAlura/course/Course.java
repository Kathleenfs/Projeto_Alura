 package br.com.alura.ProjetoAlura.course;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;
import jakarta.persistence.Column;

import java.time.LocalDate;
import java.util.List;

import br.com.alura.ProjetoAlura.registration.Registration;



@Entity
@Table(name = "courses")
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@Column(unique = true, nullable = false)
    @Pattern(regexp = "^[a-z]+(-[a-z]+)*$")
	private String code;


	private String instructorEmail;

	private String description;

	private String status;

	private LocalDate inactivationDate;
	
	 @PrePersist
	    public void prePersist() {
	        this.status = "ACTIVE";  
	        
	    }
	 
	 @OneToMany(mappedBy = "course")
	    private List<Registration>  registrations;
	 


	public List<Registration> getRegistrations() {
		return registrations;
	}


	public void setRegistrations(List<Registration> registrations) {
		this.registrations = registrations;
	}


	public Course() {
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getInstructorEmail() {
		return instructorEmail;
	}

	public void setInstructorEmail(String instructorEmail) {
		this.instructorEmail = instructorEmail;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDate getInactivationDate() {
		return inactivationDate;
	}

	public void setInactivationDate(LocalDate inactivationDate) {
		this.inactivationDate = inactivationDate;
	}
	
	
	
	

}
