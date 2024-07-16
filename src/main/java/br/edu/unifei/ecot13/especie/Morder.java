package br.edu.unifei.ecot13.especie;

import javax.persistence.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
public class Morder extends Ataque {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7814068629870178212L;

	@Override
	public void estilo (Irritado i) {
		System.out.println("Você foi mordido, vai infeccionar");
		i.setTipo(new Arranhar());
	}
}
