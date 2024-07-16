package br.edu.unifei.ecot13.grupo;
import br.edu.unifei.ecot13.especie.Jupara;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Data;

@Data
@Entity
public class Grupo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1230724216164033347L;
	
	@Id
	@GeneratedValue
	private int codigo;
	
	@OneToOne
	private Habitat habitat = new Habitat();
	@OneToOne
	private Ameaca ameaca = new Doenca();
	@OneToMany
	private List<Jupara> lista = new ArrayList<Jupara>();
	
	public int getCodigo() {
		return codigo;
	}
	public Habitat getHabitat() {
		return habitat;
	}
	public void setHabitat(Habitat habitat) {
		this.habitat = habitat;
	}
	public Ameaca getAmeaca() {
		return ameaca;
	}
	public void setAmeaca(Ameaca ameaca) {
		this.ameaca = ameaca;
	}
	public List<Jupara> getLista() {
		return lista;
	}
	
	public void conhecer(Jupara j) {
		for(Jupara especie : lista) {
			if(especie == j) {
				System.out.println("Eles já se conhecem :)\n");
				return;
			}
		}
		lista.add(j);
		System.out.println("Seu jupará entrou em um grupo, parabéns pra ele\n");
		j.setGrupo(this);
		return;
	}
	
}
