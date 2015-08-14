package kms.com.jpa.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import kms.com.jpa.domain.Member;
import kms.com.jpa.domain.Order;
import kms.com.jpa.domain.OrderSearch;

@Repository
public class OrderRepository {

	@PersistenceContext
	EntityManager entityManager;
	
	public void save(Order order){
		entityManager.persist(order);
	}
	
	
	public  Order findOne(Long order){
		return entityManager.find(Order.class, order);
	}
	
	public List<Order> findAll(OrderSearch orderSearch){
		//Criteria 동적 sql 위한 사용
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Order> criteriaQuery = criteriaBuilder.createQuery(Order.class);
		Root<Order> root = criteriaQuery.from(Order.class);
		
		List<Predicate> criteria = new ArrayList<Predicate>();
		//주문상태 검색
		if(orderSearch.getOrderStatus() != null){
			Predicate status = criteriaBuilder.equal(root.get("status"), orderSearch.getOrderStatus());
			criteria.add(status);
			
		}
		//회원이름검색
		if(StringUtils.hasText(orderSearch.getMemberName())){
			Join<Order, Member> m = root.join("member" , JoinType.INNER);
			Predicate name = criteriaBuilder.like(m.<String>get("name"), "%" + orderSearch.getMemberName()+ "%");
			criteria.add(name);
		}
		
		criteriaQuery.where(criteriaBuilder.and(criteria.toArray(new Predicate[criteria.size()])));
		TypedQuery<Order> query = entityManager.createQuery(criteriaQuery).setMaxResults(1000);
		
		return query.getResultList();
	}
	
}
