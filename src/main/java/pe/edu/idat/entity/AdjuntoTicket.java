package pe.edu.idat.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="adjunto_ticket")
public class AdjuntoTicket {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idAdjuntoTicket;
	private String nombrefoto;
	private String tipo;
	@Column(length = 50000000)
	private byte[] picByte;
	
	public AdjuntoTicket() {
		// TODO Auto-generated constructor stub
	}

	public AdjuntoTicket(String nombrefoto, String tipo, byte[] picByte) {
		
		this.nombrefoto = nombrefoto;
		this.tipo = tipo;
		this.picByte = picByte;
	}

	public Long getIdAdjuntoTicket() {
		return idAdjuntoTicket;
	}

	public void setIdAdjuntoTicket(Long idAdjuntoTicket) {
		this.idAdjuntoTicket = idAdjuntoTicket;
	}

	public String getNombrefoto() {
		return nombrefoto;
	}

	public void setNombrefoto(String nombrefoto) {
		this.nombrefoto = nombrefoto;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public byte[] getPicByte() {
		return picByte;
	}

	public void setPicByte(byte[] picByte) {
		this.picByte = picByte;
	}
	
	
	

}
