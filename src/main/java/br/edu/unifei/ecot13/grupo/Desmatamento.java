package br.edu.unifei.ecot13.grupo;
import br.edu.unifei.ecot13.especie.Jupara;

import java.util.List;

import javax.persistence.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
public class Desmatamento extends Ameaca {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4473177375623279396L;

	public List<Jupara> quedaPopulacao(List<Jupara> g) {
		if(g.size()<4){
			System.out.println("Eles fugiram");
		} else {
			for(int i=0; i<3; i++){
				g.remove(g.size()-1);
			}
			System.out.println("Infelizmente, 3 Juparás se separaram do grupo\n");
		}
		return g;
	}

}
