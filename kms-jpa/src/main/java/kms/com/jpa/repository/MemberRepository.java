package kms.com.jpa.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import kms.com.jpa.domain.Member;

@Repository
public class MemberRepository {

	@PersistenceContext
	private EntityManager entityManager;
	
	//영속화
	public void save(Member member){
		entityManager.persist(member);
	}
	
	
	public Member findOne(Long id){
		return entityManager.find(Member.class,id);
		
	}
	//jPQL
	public List<Member> findAll(){
		return entityManager.createQuery("select m from Member m" , Member.class).getResultList();
	}
	
	public List<Member> findByName(String name){
		   return entityManager.createQuery("select m from Member m where m.name = :name", Member.class)
	                .setParameter("name", name)
	                .getResultList();
	}
	
	
}
