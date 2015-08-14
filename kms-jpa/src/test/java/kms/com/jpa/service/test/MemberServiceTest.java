package kms.com.jpa.service.test;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import kms.com.jpa.domain.Member;
import kms.com.jpa.domain.item.Item;
import kms.com.jpa.repository.MemberRepository;
import kms.com.jpa.service.ItemService;
import kms.com.jpa.service.MemberService;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:/appConfig.xml")
@Transactional
public class MemberServiceTest {

	
	@Autowired
	private MemberRepository memberRepository;
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private ItemService itemService;
	
/*	@Test
	public void singMember() {
		//given
		Member member = new Member();
		member.setName("kim");
		
		Member member2 = new Member();
		member2.setName("kim");
		
		//when
		Long saveId = memberService.join(member);
		
		Long saveId2 = memberService.join(member2);
		//then
		assertEquals(member , memberRepository.findOne(saveId));
		
		assertEquals(member , memberRepository.findOne(saveId2));
	}*/
	
	
	
	@Test
	public void payItem() {
		//given
		Item item1 = new Item();
		item1.setName("유니크");
		item1.addStock(1);
		
		Item item2 = new Item();
		item2.setName("레어");
		item1.addStock(1);
		item1.addStock(1);
		item1.removeStock(1);
		
		//when
		itemService.saveItem(item1);
		itemService.saveItem(item2);
		
		
		
		Item itemOne = itemService.findOne(item1.getId());
		System.out.println(itemOne.getName());
		//then
		List<Item> items = itemService.findItem();
		for (Item itemObj : items) {
			System.out.println(itemObj.getName());
			System.out.println(itemObj.getStockQuantity());
		}
		
		
	}
	

}
