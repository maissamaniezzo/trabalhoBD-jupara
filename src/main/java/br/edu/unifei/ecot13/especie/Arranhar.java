package br.edu.unifei.ecot13.especie;

import javax.persistence.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
public class Arranhar extends Ataque {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5204354298876363475L;

	@Override
	public void estilo (Irritado i) {
		System.out.println("Isso deve doer, para de ser burro e solta ele!");
		i.setTipo(new Segurar());
	}
}