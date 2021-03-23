package br.com.netodevel.authserver.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "profiles")
public class Profile implements GrantedAuthority {

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

	@Column(name = "name")
    private String name;
	
	@ManyToOne
	@JoinColumn(name = "id_user")
	private Users user;

    public Profile() {
    }

    public Profile(String nome) {
        this.setName(nome);
    }

    public Profile(String string, Users users) {
    	this.name = string;
    	this.user = users;
	}

	@Override
    public String getAuthority() {
        return getName();
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
