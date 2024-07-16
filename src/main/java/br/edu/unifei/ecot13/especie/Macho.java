package br.edu.unifei.ecot13.especie;

import javax.persistence.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
public class Macho extends Genero {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2138192038989211169L;
}
