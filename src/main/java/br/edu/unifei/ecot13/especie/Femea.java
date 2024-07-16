package br.edu.unifei.ecot13.especie;

import javax.persistence.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
public class Femea extends Genero {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5792325217648463497L;
	
}
