package br.com.alura.ProjetoAlura.course;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

import java.time.LocalDate;
@Entity
@Table(name = "courses")
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@Column(length = 10)
	private String code;

	private String instructorEmail;

	private String description;

	private String status;

	private LocalDate inactivationDate;
	
	 @PrePersist
	    public void prePersist() {
	        this.status = "ACTIVE"; 
	        
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