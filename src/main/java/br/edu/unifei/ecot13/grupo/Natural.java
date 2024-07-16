package br.edu.unifei.ecot13.grupo;

import javax.persistence.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
public class Natural extends Habitat {
	/**
	 * 
	 */
	private static final long serialVersionUID = 969802793541346764L;
	
	private boolean isReservaNatural;
	private String caracteristicaPrincipal;
	
	
	public boolean getIsReservaNatural() {
		return isReservaNatural;
	}
	public void setReservaNatural(boolean isReservaNatural) {
		this.isReservaNatural = isReservaNatural;
	}
	public String getCaracteristicaPrincipal() {
		return caracteristicaPrincipal;
	}
	public void setCaracteristicaPrincipal(String caracteristicaPrincipal) {
		this.caracteristicaPrincipal = caracteristicaPrincipal;
	}
}
