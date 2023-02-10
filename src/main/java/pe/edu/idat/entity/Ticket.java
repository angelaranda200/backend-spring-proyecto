package pe.edu.idat.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "ticket")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idTicket", scope = Ticket.class)
public class Ticket implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idTicket;
	private String vencido;
	private String descripcion;
	private Date fecha_creacion;
	private Date fecha_vencimiento;
	private Date fecha_cierre;
	private Date fecha_actualizacion;
	private String ultimo_mensaje;
	private String ultima_respuesta;
	private String numero;
	
	
	@ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinTable(name = "ticket_adjunto",
				joinColumns = {
						@JoinColumn(name="id_ticket")
				},
				inverseJoinColumns = {
						@JoinColumn(name="id_adjuntoticket")
				})
	private Set<AdjuntoTicket> adjuntotickets;
	

	

	@JsonIgnoreProperties(value = { "tickets", "hibernateLazyInitializer", "handler" },allowSetters = true)
	// @JsonManagedReference(value = "usuario_ticket")
	@ManyToOne(fetch = FetchType.LAZY)
	private Usuarios usuario;

	@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
	// @JsonManagedReference(value = "estado_ticket")
	@ManyToOne(fetch = FetchType.LAZY)
	private EstadoTicket estado_ticket;

	@JsonIgnoreProperties(value = { "tickets", "hibernateLazyInitializer", "handler" })
	// @JsonManagedReference()
	@ManyToOne(fetch = FetchType.LAZY)
	private Prioridad prioridad;

	@JsonIgnoreProperties(value = { "tickets", "hibernateLazyInitializer", "handler" })
	// @JsonManagedReference(value = "incidencia_ticket")
	@ManyToOne(fetch = FetchType.LAZY)
	private Incidencia incidencia;

	@JsonIgnoreProperties(value = { "tickets", "hibernateLazyInitializer", "handler" })
	// @JsonManagedReference(value = "areas_ticket")
	@ManyToOne(fetch = FetchType.LAZY)
	private Area area;

	@JsonIgnoreProperties(value = { "tickets", "hibernateLazyInitializer", "handler" })
	// @JsonManagedReference(value = "tienda_ticket")
	@ManyToOne(fetch = FetchType.LAZY)
	private Tienda tienda;

	/*@OneToMany(fetch = FetchType.LAZY, mappedBy = "tickets", cascade = CascadeType.ALL,orphanRemoval = true)
	@JsonBackReference(value = "hilo_ticket")
	private Set<HiloTicket> hilos_ticket = new HashSet<>();*/
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "ht_ticket",
				joinColumns = {
						@JoinColumn(name="id_ticket")
				},
				inverseJoinColumns = {
						@JoinColumn(name="id_hiloticket")
				})
	//@JsonBackReference(value = "hilo_ticket")
	private Set<HiloTicket> hilos_ticket;

	public Ticket() {
		
	}

	@PrePersist
	public void prePersist() {

		this.fecha_creacion = new Date();
	}

	public Long getIdTicket() {
		return idTicket;
	}

	public void setIdTicket(Long idTicket) {
		this.idTicket = idTicket;
	}

	public String getVencido() {
		return vencido;
	}

	public void setVencido(String vencido) {
		this.vencido = vencido;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFecha_creacion() {
		return fecha_creacion;
	}

	public void setFecha_creacion(Date fecha_creacion) {
		this.fecha_creacion = fecha_creacion;
	}

	public Date getFecha_vencimiento() {
		return fecha_vencimiento;
	}

	public void setFecha_vencimiento(Date fecha_vencimiento) {
		this.fecha_vencimiento = fecha_vencimiento;
	}

	public Date getFecha_cierre() {
		return fecha_cierre;
	}

	public void setFecha_cierre(Date fecha_cierre) {
		this.fecha_cierre = fecha_cierre;
	}

	public Date getFecha_actualizacion() {
		return fecha_actualizacion;
	}

	public void setFecha_actualizacion(Date fecha_actualizacion) {
		this.fecha_actualizacion = fecha_actualizacion;
	}

	public String getUltimo_mensaje() {
		return ultimo_mensaje;
	}

	public void setUltimo_mensaje(String ultimo_mensaje) {
		this.ultimo_mensaje = ultimo_mensaje;
	}

	public String getUltima_respuesta() {
		return ultima_respuesta;
	}

	public void setUltima_respuesta(String ultima_respuesta) {
		this.ultima_respuesta = ultima_respuesta;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	
	public Set<AdjuntoTicket> getAdjuntotickets() {
		return adjuntotickets;
	}

	public void setAdjuntotickets(Set<AdjuntoTicket> adjuntotickets) {
		this.adjuntotickets = adjuntotickets;
	}

	public Usuarios getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuarios usuario) {
		this.usuario = usuario;
	}

	public Set<HiloTicket> getHilos_ticket() {
		return hilos_ticket;
	}

	public void setHilos_ticket(Set<HiloTicket> hilos_ticket) {
		this.hilos_ticket = hilos_ticket;
	}

	public EstadoTicket getEstado_ticket() {
		return estado_ticket;
	}

	public void setEstado_ticket(EstadoTicket estado_ticket) {
		this.estado_ticket = estado_ticket;
	}

	public Prioridad getPrioridad() {
		return prioridad;
	}

	public void setPrioridad(Prioridad prioridad) {
		this.prioridad = prioridad;
	}

	public Incidencia getIncidencia() {
		return incidencia;
	}

	public void setIncidencia(Incidencia incidencia) {
		this.incidencia = incidencia;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public Tienda getTienda() {
		return tienda;
	}

	public void setTienda(Tienda tienda) {
		this.tienda = tienda;
	}

}
