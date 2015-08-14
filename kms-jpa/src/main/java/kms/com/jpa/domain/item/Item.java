package kms.com.jpa.domain.item;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;


import kms.com.jpa.domain.Category;
import kms.com.jpa.exception.NotEnoughStockException;

@Entity
//상속 매핑 사용시에 꼭 지정해 주어야함 
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
//식별컬럼 이컬럼으로 저장된 자식 테이블을 구분가능 기본 DTYPE
@DiscriminatorColumn(name="DTYPE")
public class Item {

	@Id @GeneratedValue
	@Column(name="ITEM_ID")
	private Long id;
	
	private String name;
	
	private int price;
	
	private int stockQuantity;
	
	@ManyToMany(mappedBy="items")
	private List<Category> categories = new ArrayList<Category>();

	
	public void addStock(int quantity){
		this.stockQuantity += quantity;
	}
	
	public void removeStock(int quantity){
		int restStock = this.stockQuantity - quantity;
		if(restStock < 0){
			throw new NotEnoughStockException("need more stock");
		}
		this.stockQuantity = restStock;
	}
	
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getStockQuantity() {
		return stockQuantity;
	}

	public void setStockQuantity(int stockQuantity) {
		this.stockQuantity = stockQuantity;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
	
	
	
	
	
	
}
