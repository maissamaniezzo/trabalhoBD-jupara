package br.edu.unifei.ecot13.especie;

import br.edu.unifei.ecot13.grupo.Grupo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.Data;

@Data
@Entity
public class Jupara implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1852412053044519232L;
	
	@Id
	@GeneratedValue
	private int codigo;
	
	
	private String nome = "jupara";
	@OneToOne
	private Genero genero = new Femea();
	@OneToOne
	private CaracteristicasFisicas caracteristicas;
	@ManyToOne
	private Grupo grupo = new Grupo();
	@OneToOne
	private Humor humor = new Tranquilo();
	
	public Jupara() {}
	
	public Jupara(String nome) {
		this.nome = nome;
	}
	
	public int getCodigo() {
		return codigo;
	}

	// -- Get e Set de nome
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	// -- Get e Set de genero
	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	// -- Get e Set de caracteristicas
	public CaracteristicasFisicas getCaracteristicas() {
		return caracteristicas;
	}

	public void setCaracteristicas(CaracteristicasFisicas caracteristicas) {
		this.caracteristicas = caracteristicas;
	}

	// -- Get e Set de grupo
	public Grupo getGrupo() {
		System.out.println("O grupo do seu Jupará inclui:");
		for(Jupara j : grupo.getLista()) {
			System.out.println(j.getNome());
		}
		System.out.println();
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	// -- Get e Set de humor
	public Humor getHumor() {
		return humor;
	}

	public void setHumor(Humor humor) {
		this.humor = humor;
	}
	
}
