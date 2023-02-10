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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "incidencia")
/*@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
property = "idIncidencia",
scope = Incidencia.class)*/
public class Incidencia implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idIncidencia;
	private String nombre;
	private String descripcion;
	private String status;
	private String indicator;

	@JsonIgnoreProperties(value={"incidencias","hibernateLazyInitializer","handler"})
	@ManyToOne(fetch = FetchType.LAZY)
	//@JsonManagedReference(value = "plan_incidencia")
	private PlanSla plan_sla;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "incidencia",cascade = CascadeType.ALL)
	@JsonBackReference(value = "incidencia_ticket")
	@JsonIgnoreProperties(value={"incidencia","hibernateLazyInitializer","handler"},allowSetters = true)
	private List<Ticket> tickets;
	
	

	public Incidencia() {
		this.tickets = new ArrayList<>();
	}

	public Long getIdIncidencia() {
		return idIncidencia;
	}

	public void setIdIncidencia(Long idIncidencia) {
		this.idIncidencia = idIncidencia;
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

	public PlanSla getPlan_sla() {
		return plan_sla;
	}

	public void setPlan_sla(PlanSla plan_sla) {
		this.plan_sla = plan_sla;
	}

	
	public List<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}
	
	
	

}
