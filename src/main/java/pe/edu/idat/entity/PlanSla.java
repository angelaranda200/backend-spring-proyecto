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
@Table(name = "plan_sla")
/*@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
property = "idPlanSla",
scope = PlanSla.class)*/
public class PlanSla implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPlanSla;
	private String nombre;
	private String periodo_de_gracia;
	private String status;
	private String indicator;

	@JsonIgnoreProperties(value={"plan_sla","hibernateLazyInitializer","handler"},allowSetters = true)
	@JsonBackReference(value = "plan_incidencia")
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "plan_sla",cascade = CascadeType.ALL)
	private List<Incidencia> incidencias;

	public PlanSla() {
		this.incidencias = new ArrayList<>();
	}

	public Long getIdPlanSla() {
		return idPlanSla;
	}

	public void setIdPlanSla(Long idPlanSla) {
		this.idPlanSla = idPlanSla;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPeriodo_de_gracia() {
		return periodo_de_gracia;
	}

	public void setPeriodo_de_gracia(String periodo_de_gracia) {
		this.periodo_de_gracia = periodo_de_gracia;
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

	public List<Incidencia> getIncidencias() {
		return incidencias;
	}

	public void setIncidencias(List<Incidencia> incidencias) {
		this.incidencias = incidencias;
	}
	
	

}
