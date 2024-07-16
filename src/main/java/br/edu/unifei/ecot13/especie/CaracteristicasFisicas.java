package br.edu.unifei.ecot13.especie;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class CaracteristicasFisicas implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6667641923184747857L;
	
	@Id
	@GeneratedValue
	private int codigo;
	
	private String corDoPelo = "Marrom";
	private int altura = 76;
	private int tamanhoRabo = 54;
	private boolean temDeficiencia = false;
	
	// -- Get e Set de corPelo
	public String getCorDoPelo() {
		return corDoPelo;
	}
	
	public void setCorDoPelo(String corDoPelo) {
		this.corDoPelo = corDoPelo;
	}
	
	// -- Get e Set de altura (centimentros)
	public int getAltura() {
		return altura;
	}
	
	public void setAltura(int altura) {
		this.altura = altura;
	}
	
	// -- Get e Set de tamanhoRabo (centimetros)
	public int getTamanhoRabo() {
		return tamanhoRabo;
	}
	
	public void setTamanhoRabo(int tamanhoRabo) {
		this.tamanhoRabo = tamanhoRabo;
	}
	
	// -- Get e Set de temDeficiencia
	public boolean getTemDeficiencia() {
		return temDeficiencia;
	}
	
	public void setTemDeficiencia(boolean temDeficiencia) {
		this.temDeficiencia = temDeficiencia;
	}
	
}
