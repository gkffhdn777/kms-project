package kms.com.jpa.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Order <-- sql 키워드 사용불가
 */
@Entity
@Table(name="ORDERS")
public class Order {

	@Id @GeneratedValue
	@Column(name="ORDER_ID")
    private Long id;

	@ManyToOne
	@JoinColumn(name="MEMBER_ID")
    private Member member;      //주문 회원
	//mappedBy 설정의 주테이블 설정 FK 로 설정된 테이블이 주로 주테이블 된다
	//cascade 영속석 전의로 order 테이블 저장시 orderOtems도 저장 또는 type 조건에 따라 삭제,수정 가능하다
	@OneToMany(mappedBy="order" , cascade=CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<OrderItem>();

	//fetch 테이블 조회시 조인된 테일블 의 데이터를 다 가져올것인지 선택해서 가져올것인지 선택 보통은 LAZY 
	@OneToOne(cascade = CascadeType.ALL , fetch= FetchType.LAZY)
	@JoinColumn(name="DELIVERY_ID")
    private Delivery delivery;  //배송정보

	
    private Date orderDate;     //주문시간
    
    //DB의 상태값을 편리하게 저장 할수 있다 보통은 String 사용
    @Enumerated(EnumType.STRING)
    private OrderStatus status;//주문상태
    
    //==연관관계 메서드==//
    public void setMember(Member member) {
        this.member = member;
        member.getOrders().add(this);
    }

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.setOrder(this);
    }
    

    //배달은 상태값 변경 
    //맴버는 주문자
   public static Order createOrder(Member member , Delivery  delivery, OrderItem... orderItems){
	   Order order = new Order();
	   order.setMember(member);
	   order.setDelivery(delivery);
	   for (OrderItem orderItem : orderItems) {
		   order.addOrderItem(orderItem);
	   }
	   order.setStatus(OrderStatus.ORDERs);
	   order.setOrderDate(new Date());
	   return order;
   }
    
    public void cancel(){
    	
    	if(delivery.getStatus() == DeliveryStatus.COMP){
    		throw new RuntimeException("이미 배송완료된 상품은 취소가 불가능 합니다.");
    	}
    	
    	this.setStatus(OrderStatus.CANCEL);
    	for (OrderItem orderItem : orderItems) {
			orderItem.cancel();
		}
    	
    	
    }
    
    public int getTotalPrice(){
    	int totalPrice = 0;
    	for (OrderItem orderItem : orderItems) {
			totalPrice += orderItem.getOrderPrice();
			
		}
    	return totalPrice;
    }
 
  
    
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public Member getMember() {
		return member;
	}

	public Delivery getDelivery() {
		return delivery;
	}


    
    
   
}
