package br.edu.unifei.ecot13.grupo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Habitat implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 772774332296613650L;
	
	@Id
	@GeneratedValue
	private int codigo;
	private String nome;
	private String local;
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getLocal() {
		return local;
	}
	public void setLocal(String local) {
		this.local = local;
	}
}
