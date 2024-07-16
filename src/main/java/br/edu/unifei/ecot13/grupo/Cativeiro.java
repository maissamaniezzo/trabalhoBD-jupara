package br.edu.unifei.ecot13.grupo;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Transient;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
public class Cativeiro extends Habitat {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1533871332461761619L;
	
	private boolean isSantuario;
	private Date ultimaInspecao = new Date();
	@Transient
	private SimpleDateFormat sdf1= new SimpleDateFormat("dd/MM/yyyy");
	

	public boolean getIsSantuario() {
		return isSantuario;
	}
	public void setIsSantuario(boolean santuario) {
		this.isSantuario = santuario;
	}
	public String getUltimaInspecao() {
		return sdf1.format(ultimaInspecao);
	}
	public void setUltimaInspecao(Date ultimaInspecao) {
		this.ultimaInspecao = ultimaInspecao;
	}
	public void inspecionar() {
		this.ultimaInspecao = new Date();
	}
}
