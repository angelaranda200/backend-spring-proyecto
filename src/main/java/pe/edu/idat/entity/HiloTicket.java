package pe.edu.idat.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.core.type.TypeReference;

@Entity
@Table(name = "hilo_ticket")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "idHiloTicket",scope = HiloTicket.class)
public class HiloTicket {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idHiloTicket;
	private String contenido;
	private Date fecha_creacion;
	
	/*@ManyToOne(fetch = FetchType.LAZY,targetEntity = Ticket.class)
	@JsonIgnoreProperties(value = { "tickets", "hibernateLazyInitializer", "handler" })
	@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,include = As.WRAPPER_OBJECT,property = "tickets")
	private Ticket  tickets;*/
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties(value = { "hilo_ticket", "hibernateLazyInitializer", "handler" },allowSetters = true)
	private Usuarios usuario;
	
	public HiloTicket() {
		
	}

	public Long getIdHiloTicket() {
		return idHiloTicket;
	}

	public void setIdHiloTicket(Long idHiloTicket) {
		this.idHiloTicket = idHiloTicket;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public Date getFecha_creacion() {
		return fecha_creacion;
	}

	public void setFecha_creacion(Date fecha_creacion) {
		this.fecha_creacion = fecha_creacion;
	}
	
	/*public Ticket getTickets() {
		return tickets;
	}

	public void setTickets(Ticket tickets) {
		this.tickets = tickets;
	}*/

	public Usuarios getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuarios usuario) {
		this.usuario = usuario;
	}

}
