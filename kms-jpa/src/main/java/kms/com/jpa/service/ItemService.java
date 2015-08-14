package kms.com.jpa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kms.com.jpa.domain.item.Item;
import kms.com.jpa.repository.ItemRepository;

@Service
@Transactional
public class ItemService {

	@Autowired
	private ItemRepository itemRepository;
	
	public void saveItem(Item item){
		itemRepository.save(item);
	}
	
	
	public List<Item> findItem(){
		return itemRepository.findAll();
	}
	
	public Item findOne(Long itemId){
		return itemRepository.findOne(itemId);
	}
	
}
