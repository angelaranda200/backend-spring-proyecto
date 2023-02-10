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
@Table(name = "estado_ticket")
/*@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
property = "idEstadoTicket",
scope = Long.class)*/
public class EstadoTicket implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idEstadoTicket;
	private String nombre;
	private String descripcion;
	private String status;
	private String indicator;

	@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler"},allowSetters = true)
	@JsonBackReference(value = "estado_ticket")
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "estado_ticket", cascade = CascadeType.ALL)
	private List<Ticket> tickets;

	public EstadoTicket() {

		this.tickets = new ArrayList<>();
	}

	public Long getIdEstadoTicket() {
		return idEstadoTicket;
	}

	public void setIdEstadoTicket(Long idEstadoTicket) {
		this.idEstadoTicket = idEstadoTicket;
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

	public List<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

}
