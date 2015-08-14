package kms.com.jpa.domain;


import javax.persistence.*;

import kms.com.jpa.domain.item.Item;

/**
 * Created by holyeye on 2014. 3. 11..
 */
@Entity
/*@Table(name="ORDER_ITEM")*/
public class OrderItem {

	@Id @GeneratedValue
	@Column(name = "ORDER_ITEM_ID")
    private Long id;

	@ManyToOne(fetch= FetchType.LAZY)
    private Item item;      //주문 상품

    @ManyToOne(fetch = FetchType.LAZY)
    private Order order;    //주문

    private int orderPrice; //주문 가격
    private int count;      //주문 수량
    
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public int getOrderPrice() {
		return orderPrice;
	}
	public void setOrderPrice(int orderPrice) {
		this.orderPrice = orderPrice;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}

    
 //==생성 메서드==//
    public static OrderItem createOrderItem(Item item, int orderPrice, int count) {

        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setOrderPrice(orderPrice);
        orderItem.setCount(count);
        
        item.removeStock(count);
        return orderItem;
    }

    //==비즈니스 로직==//
    //** 주문 취소 *//*
    public void cancel() {
        getItem().addStock(count);
    }

    //==조회 로직==//
    //** 주문상품 전체 가격 조회 *//*
    public int getTotalPrice() {
        return getOrderPrice() * getCount();
    }
   
}
