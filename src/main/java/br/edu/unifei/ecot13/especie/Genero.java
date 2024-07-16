package br.edu.unifei.ecot13.especie;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public abstract class Genero implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5925387123706884174L;
	
	@Id
	@GeneratedValue
	private int codigo;
}
