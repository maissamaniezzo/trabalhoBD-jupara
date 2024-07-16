package br.edu.unifei.ecot13.especie;

import javax.persistence.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
public class Aticado extends Humor {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -993820438379132958L;

	@Override
	public void interagir() {
		System.out.println("E assim, você é escalado e seus bolsos vasculhados");
	}
}
