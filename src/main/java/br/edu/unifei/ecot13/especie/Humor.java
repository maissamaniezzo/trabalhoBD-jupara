package br.edu.unifei.ecot13.especie;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public abstract class Humor implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3058112472034301524L;
	
	@Id
	@GeneratedValue
	private int codigo;
	
	public abstract void interagir();
}
