package br.edu.unifei.ecot13.grupo;
import br.edu.unifei.ecot13.especie.Jupara;

import java.util.List;

import javax.persistence.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
public class Predador extends Ameaca {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3725638195333640565L;

	@Override
	public List<Jupara> quedaPopulacao(List<Jupara> g) {
		if(g.size()<3){
			System.out.println("Eles fugiram");
		} else {
			g.remove(g.size()-1);
			System.out.println("Infelizmente, 1 Jupará sumiu no meio da correria\n");
		}
		return g;
	}
	
}
