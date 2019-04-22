package hu.daniel.hari.learn.spring.Tutorial_SpringORMwithTX.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;

import hu.daniel.hari.learn.spring.Tutorial_SpringORMwithTX.model.Product;

@Component
public class ProductDao {
	
	@PersistenceContext
	private EntityManager em;
	
	public void persist(Product product) {
		em.persist(product);
	}

	public List<Product> findAll() {
		return em.createQuery("SELECT p FROM Product p").getResultList();
	}
}
