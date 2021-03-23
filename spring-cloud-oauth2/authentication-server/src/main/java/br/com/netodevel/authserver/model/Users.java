package br.com.netodevel.authserver.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "users")
public class Users {

	@Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    @OneToMany(mappedBy = "user")
    private List<Profile> profiles;

    private String email;
    
    private String password;
    
    @Column(name="created_at")
	@Temporal(TemporalType.TIMESTAMP)  
	private Date createdAt;
	
	@Column(name="updated_at")
	@Temporal(TemporalType.TIMESTAMP)  
	private Date updatedAt;

    public Users() {
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Profile> getProfiles() {
		return profiles;
	}

	public void setProfiles(List<Profile> profiles) {
		this.profiles = profiles;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

    public Users(Users user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.profiles = user.getProfiles();
    }

    public Users(String name, List<Profile> profile, String email, String password) {
        this.name = name;
        this.profiles = profile;
        this.email = email;
        this.password = password;
    }

	public Users(String name, String email, String password) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	@PrePersist
	public void setCreatedAt() {
		this.createdAt = new Date();
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	@PreUpdate
	public void setUpdatedAt() {
		this.updatedAt = new Date();
	}

}
