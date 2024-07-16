package br.edu.unifei.ecot13.especie;

import javax.persistence.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
public class Tranquilo extends Humor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4846966110740937322L;

	@Override
	public void interagir() {
		System.out.println("Olha, ele quer carinho!");
	}

}
