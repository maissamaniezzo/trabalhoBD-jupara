package br.edu.unifei.ecot13.especie;

import javax.persistence.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
public class Segurar extends Ataque {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4222390507360151620L;

	@Override
	public void estilo (Irritado i) {
		System.out.println("Ele está te segurando! Cuidado!!");
		i.setTipo(new Morder());
	}
}
