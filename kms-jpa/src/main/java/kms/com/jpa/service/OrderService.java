package kms.com.jpa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kms.com.jpa.domain.Delivery;
import kms.com.jpa.domain.Member;
import kms.com.jpa.domain.Order;
import kms.com.jpa.domain.OrderItem;
import kms.com.jpa.domain.OrderSearch;
import kms.com.jpa.domain.item.Item;
import kms.com.jpa.repository.MemberRepository;
import kms.com.jpa.repository.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private MemberRepository memberRepository;
	@Autowired
	private OrderRepository orderRepository ;
	@Autowired
	private ItemService itemService;
	
	
	public Long order (Long memberId , Long itemId , int count){
		
		//회원 아이디로 맴버 객체를 찾는다 
		//아이템 아이디로 아이템 객체를 찾는다
		//주문가 동시에 delivery 주문주소와 상태값은 배송 준비로 변한다
		//주문상품명을 생성한다  여기에는 주문정보와 가격 갯수를 입력한다
		//주문을 한다 회원정보와 , 주문주소 , 아이템 정보를 저장한다
		//작성된 주문 정보를 최종 저장한다
		//주문번호를 리턴한다
		Member member = memberRepository.findOne(memberId);
		Item item =itemService.findOne(itemId);
		
		Delivery delivery = new Delivery(member.getAddress());
		
		OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);
		
		
		Order order = Order.createOrder(member, delivery, orderItem);
		
		orderRepository.save(order);
		return order.getId();
	}
	
	public void cancelOrder(Long orderId){
		Order order = orderRepository.findOne(orderId);
		order.cancel();
		
	}
	
	public List<Order> findOrders(OrderSearch orderSearch){
		return orderRepository.findAll(orderSearch);
	}
	
	
	
}
