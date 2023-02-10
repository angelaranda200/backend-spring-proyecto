package pe.edu.idat.entity;

import java.io.Serializable;



import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;



@Entity
@Table(name = "role")
public class Role implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idRole;
	private String nombreRole;
	
	
	
	public Role() {
		// TODO Auto-generated constructor stub
	}

	
	public Role(String nombreRole) {
		
		this.nombreRole = nombreRole;
	}


	public Long getIdRole() {
		return idRole;
	}


	public void setIdRole(Long idRole) {
		this.idRole = idRole;
	}


	public String getNombreRole() {
		return nombreRole;
	}


	public void setNombreRole(String nombreRole) {
		this.nombreRole = nombreRole;
	}




	
	
	
	
	
	
	
	
	

}
