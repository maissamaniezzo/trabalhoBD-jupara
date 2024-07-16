package br.edu.unifei.ecot13.especie;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public abstract class Ataque implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3206606064246153683L;
	
	@Id
	@GeneratedValue
	private int codigo;

	public abstract void estilo (Irritado i);
}
