package br.edu.unifei.ecot13.grupo;
import br.edu.unifei.ecot13.especie.Jupara;

import java.util.List;

import javax.persistence.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
public class Doenca extends Ameaca {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4056694703905291352L;

	public List<Jupara> quedaPopulacao(List<Jupara> g) {
		if(g.size()<2){
			System.out.println("Ele se curou");
		} else {
			g.remove(g.size()-1);
			System.out.println("Infelizmente, 1 Jupará foi levado embora do grupo\n");
		}
		return g;
	}

}
