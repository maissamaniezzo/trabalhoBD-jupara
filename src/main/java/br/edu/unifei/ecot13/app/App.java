package br.edu.unifei.ecot13.app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.edu.unifei.ecot13.especie.*;
import br.edu.unifei.ecot13.grupo.*;

public class App {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("juparaPU");
		EntityManager em = emf.createEntityManager();
		
		em.close();
        emf.close();
	}
}
