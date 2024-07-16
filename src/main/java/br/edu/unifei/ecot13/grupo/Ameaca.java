package br.edu.unifei.ecot13.grupo;
import br.edu.unifei.ecot13.especie.Jupara;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public abstract class Ameaca implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6156850167018315259L;
	
	@Id
	@GeneratedValue
	private int codigo;

	public abstract List<Jupara> quedaPopulacao(List<Jupara> g);
}
