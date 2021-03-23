package br.com.netodevel.authserver.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "phones")
public class Phone {

	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String ddd;
	private String number;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private Users user;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getDdd() {
		return ddd;
	}
	
	public void setDdd(String ddd) {
		this.ddd = ddd;
	}
	
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	
}
