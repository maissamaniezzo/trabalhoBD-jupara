package br.edu.unifei.ecot13.especie;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
public class Irritado extends Humor {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6356864857279164108L;
	
	@OneToOne
	private Ataque tipo = new Segurar();
	
	@Override
	public void interagir() {
		tipo.estilo(this);
	}

	public Ataque getTipo() {
		return tipo;
	}

	public void setTipo(Ataque tipo) {
		this.tipo = tipo;
	}
	
}
