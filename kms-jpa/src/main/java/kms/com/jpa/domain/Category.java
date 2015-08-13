package kms.com.jpa.domain;

import jpabook.jpashop.domain.item.Item;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by holyeye on 2014. 3. 11..
 */
@Entity
public class Category {

    private Long id;

    private String name;

 
    private List<Item> items = new ArrayList<Item>();

  
    private Category parent;


    private List<Category> child = new ArrayList<Category>();

    //==연관관계 메서드==//
    public void addChildCategory(Category child) {
        this.child.add(child);
        child.setParent(this);
    }

   
}
