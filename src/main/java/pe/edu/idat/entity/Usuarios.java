package pe.edu.idat.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
@Entity
@Table(name = "usuarios")
/*@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
property = "idUsuario",
scope = Usuarios.class)*/
public class Usuarios implements Serializable,UserDetails{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idUsuario;
	@NotEmpty(message = "no debe estar vacio")
	@Column(nullable = false, unique = true)
	private String username;
	@NotEmpty(message = "no debe estar vacio")
	private String nombres;
	@NotEmpty(message = "no debe estar vacio")
	private String apellidos;
	
	@NotEmpty(message = "no debe estar vacio")
	@Length(min = 4,message = "debe de tener como minimo 4 caracteres")
	private String password;
	@NotEmpty(message = "no debe estar vacio")
	@Email(message = "No es una direccion de email correcta")
	private String email;
	@Size(max = 7,message = "tiene que ingresar como maximo 7 caracteres")
	private String telefono;
	@Size(max = 9,message = "tiene que ingresar como maximo 9 caracteres")
	private String celular;
	private String status;
	private Date fecha_creado;
	private String indicator;

	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties(value={"usuarios","hibernateLazyInitializer","handler"},allowSetters = true)
	
	private Area area;
	
	@ManyToMany(fetch = FetchType.EAGER,cascade = {CascadeType.MERGE,CascadeType.REFRESH})
	@JoinTable(name = "role_usuarios",joinColumns = @JoinColumn(name="usuario_id"),inverseJoinColumns = @JoinColumn(name="role_id"),uniqueConstraints = {
			@UniqueConstraint(columnNames = {"usuario_id","role_id"})
	})
	@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler"},allowSetters = true)
	
	private Set<Role> usuarioRoles = new HashSet<>();

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "usuario_servicio",joinColumns = @JoinColumn(name="usuario_id"),inverseJoinColumns = @JoinColumn(name="servicio_id"),uniqueConstraints = {
			@UniqueConstraint(columnNames = {"usuario_id","servicio_id"})
	})
	@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler"},allowSetters = true)
	@NotEmpty(message = "no debe ser vacio")
	private Set<Servicio> servicio = new HashSet<>();
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "usuario",cascade = CascadeType.ALL)
	@JsonBackReference(value = "usuario_ticket")
	@JsonIgnoreProperties(value={"usuario","hibernateLazyInitializer","handler"},allowSetters = true)
	private List<Ticket> tickets;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "usuario",cascade = CascadeType.ALL)
	@JsonBackReference(value = "hilo_usuario")
	@JsonIgnoreProperties(value={"usuario","hibernateLazyInitializer","handler"},allowSetters = true)
	private List<HiloTicket> hilo_ticket;

	public Usuarios() {
		
		this.hilo_ticket = new ArrayList<>();
		this.tickets = new ArrayList<>();
		
	}
	
	@PrePersist
	public void prePersist() {
		this.fecha_creado = new Date();
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getFecha_creado() {
		return fecha_creado;
	}

	public void setFecha_creado(Date fecha_creado) {
		this.fecha_creado = fecha_creado;
	}

	public String getIndicator() {
		return indicator;
	}

	public void setIndicator(String indicator) {
		this.indicator = indicator;
	}

	public List<HiloTicket> getHilo_ticket() {
		return hilo_ticket;
	}

	public void setHilo_ticket(List<HiloTicket> hilo_ticket) {
		this.hilo_ticket = hilo_ticket;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public Set<Role> getUsuarioRoles() {
		return usuarioRoles;
	}

	public void setUsuarioRoles(Set<Role> usuarioRoles) {
		this.usuarioRoles = usuarioRoles;
	}

	public Set<Servicio> getServicio() {
		return servicio;
	}

	public void setServicio(Set<Servicio> servicio) {
		this.servicio = servicio;
	}

	public List<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

	
	@Override
	@JsonIgnore
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		Set<Authority> set = new HashSet<>();
		this.usuarioRoles.forEach(userRole->{
			set.add(new Authority(userRole.getNombreRole()));
		});
		return set;
	}

	@Override
	@JsonIgnore
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	@JsonIgnore
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	@JsonIgnore
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	@JsonIgnore
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	
	
	
	
	
	

}
