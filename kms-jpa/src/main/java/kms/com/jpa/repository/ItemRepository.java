package kms.com.jpa.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import kms.com.jpa.domain.item.Item;

@Repository
public class ItemRepository {

	@PersistenceContext
	EntityManager entityManager;
	
	
	public void save (Item item){
		if(item.getId() == null){
			entityManager.persist(item);
		}else {
			entityManager.merge(item);
		}
	}
	
	public Item findOne(Long id){
		return entityManager.find(Item.class, id);
	}
	
	public List<Item>findAll(){
		return entityManager.createQuery("select i from Item i", Item.class).getResultList();
	}
	
	
}
