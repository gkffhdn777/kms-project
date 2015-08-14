package kms.com.jpa.domain;



import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import kms.com.jpa.domain.item.Item;

/**
 * Created by holyeye on 2014. 3. 11..
 */
@Entity
public class Category {

	@Id @GeneratedValue
	@Column(name="CATEGORY_ID")
    private Long id;
	
	
    private String name;

    @ManyToMany
                                       //현제방향 조인
    @JoinTable(name="CATEGORY_ITEM" , joinColumns= @JoinColumn(name="CATEGORY_ID"),
    //반대방향 조인
    inverseJoinColumns = @ JoinColumn(name="ITEM_ID"))
    private List<Item> items = new ArrayList<Item>();

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="PARENT_ID")
    private Category parent;

    @OneToMany(mappedBy="parent")
    private List<Category> child = new ArrayList<Category>();

    //==연관관계 메서드==//
    public void addChildCategory(Category child) {
        this.child.add(child);
        child.setParent(this);
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

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public Category getParent() {
		return parent;
	}

	public void setParent(Category parent) {
		this.parent = parent;
	}

	public List<Category> getChild() {
		return child;
	}

	public void setChild(List<Category> child) {
		this.child = child;
	}
    
    

   
}
