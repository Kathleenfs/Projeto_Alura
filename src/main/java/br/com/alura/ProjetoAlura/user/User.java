package br.com.alura.ProjetoAlura.user;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

import br.com.alura.ProjetoAlura.registration.Registration;
import br.com.alura.ProjetoAlura.util.EncryptUtil;



@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime createdAt = LocalDateTime.now();
    private String name;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String email;
    private String password;

    @Deprecated
    public User() {}

    public User(String name, String email, Role role, String password) {
        this.name = name;
        this.role = role;
        this.email = email;
        this.password = EncryptUtil.toMD5(password);
    }

    
    @OneToMany(mappedBy = "user")
    private List<Registration>  registrations;
    
    
    
    public List<Registration> getRegistrations() {
		return registrations;
	}

	public void setRegistrations(List<Registration> registrations) {
		this.registrations = registrations;
	}

	public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
    
    

    public void setEmail(String email) {
		this.email = email;
	}

	public Role getRole() {
        return role;
    }

    public String getPassword() {
        return password;
    }
}
