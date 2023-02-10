package pe.edu.idat.entity;

import java.io.Serializable;
import java.util.ArrayList;

import java.util.List;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "departamento")
/*@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
				 property = "idArea",
				 scope = Area.class)*/
public class Area implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idArea;
	private String nombre;
	private String descripcion;
	private String email;
	private String status;
	private String indicator;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "area", cascade = CascadeType.ALL)
	@JsonIgnoreProperties(value={"area","hibernateLazyInitializer","handler"},allowSetters = true)
	@JsonBackReference(value = "areas_ticket")
	private List<Ticket> tickets;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "area", cascade = CascadeType.ALL)
	@JsonIgnoreProperties(value={"area","hibernateLazyInitializer","handler"},allowSetters = true)
	@JsonBackReference(value = "areas_usuarios")
	private List<Usuarios> usuarios;

	public Area() {
		this.tickets = new ArrayList<>();
		this.usuarios = new ArrayList<>();
	}

	public Long getIdArea() {
		return idArea;
	}

	public void setIdArea(Long idArea) {
		this.idArea = idArea;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getIndicator() {
		return indicator;
	}

	public void setIndicator(String indicator) {
		this.indicator = indicator;
	}

	public List<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

	public List<Usuarios> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuarios> usuarios) {
		this.usuarios = usuarios;
	}
	
	

	
	
	

}	
