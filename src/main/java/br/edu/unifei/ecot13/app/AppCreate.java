package br.edu.unifei.ecot13.app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.edu.unifei.ecot13.especie.*;
import br.edu.unifei.ecot13.grupo.*;

public class AppCreate {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("juparaPU");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		// Persistindo os gêneros
		Femea femea = new Femea();
		em.persist(femea);
		Macho macho = new Macho();
		em.persist(macho);
		
		// Persistindo características
		CaracteristicasFisicas caracteristicas1 = new CaracteristicasFisicas();
		caracteristicas1.setCorDoPelo("marrom escuro");
		caracteristicas1.setAltura(50);
		caracteristicas1.setAltura(40);
		caracteristicas1.setTemDeficiencia(true);
		em.persist(caracteristicas1);
		
		CaracteristicasFisicas caracteristicas2 = new CaracteristicasFisicas();
		caracteristicas2.setCorDoPelo("marrom claro");
		caracteristicas2.setTemDeficiencia(false);
		em.persist(caracteristicas2);
		
		//Persistindo humor
		Arranhar arranhar = new Arranhar();
		em.persist(arranhar);
		Morder morder = new Morder();
		em.persist(morder);
		Segurar segurar = new Segurar();
		em.persist(segurar);
		Irritado irritado = new Irritado();
		irritado.setTipo(segurar);
		em.persist(irritado);
		Aticado aticado = new Aticado();
		em.persist(aticado);
		Tranquilo tranquilo = new Tranquilo();
		em.persist(tranquilo);
		
		// Persistindo um grupo
		Grupo grupo1 = new Grupo();
		
		// Persistindo habitat
		Cativeiro cativeiro = new Cativeiro();
		em.persist(cativeiro);
		Natural natural = new Natural();
		em.persist(natural);
		
		grupo1.setHabitat(cativeiro);
		

		// Persistindo ameaças
		Desmatamento desmatamento = new Desmatamento();
		em.persist(desmatamento);
		Doenca doenca = new Doenca();
		em.persist(doenca);
		Predador predador = new Predador();
		em.persist(predador);
		
		grupo1.setAmeaca(doenca);
		em.persist(grupo1);
		
		// Persistindo Juparás
		Jupara jupara1 = new Jupara("Ananda");
//		Jupara jupara2 = new Jupara("Oswaldo");
		
		jupara1.setGenero(femea);
		jupara1.setCaracteristicas(caracteristicas1);
		jupara1.setHumor(tranquilo);
//		jupara2.setGenero(macho);
//		jupara2.setCaracteristicas(caracteristicas2);
//		jupara2.setHumor(irritado);
		jupara1.setGrupo(grupo1);
		em.persist(jupara1);
//		em.persist(jupara2);
		
        em.getTransaction().commit();
        em.close();
        emf.close();
	}
}
