package pe.edu.idat.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//@Entity
public class UsuarioRol {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long usuariorolId;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JsonIgnoreProperties(value={"usuarioRoles","hibernateLazyInitializer","handler"},allowSetters = true)
	private Usuarios usuario;
	
	@ManyToOne
	@JsonIgnoreProperties(value={"usuarioRoles","hibernateLazyInitializer","handler"},allowSetters = true)
	private Role role;
	
	public UsuarioRol() {
		// TODO Auto-generated constructor stub
	}

	public Long getUsuariorolId() {
		return usuariorolId;
	}

	public void setUsuariorolId(Long usuariorolId) {
		this.usuariorolId = usuariorolId;
	}

	public Usuarios getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuarios usuario) {
		this.usuario = usuario;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	

}
