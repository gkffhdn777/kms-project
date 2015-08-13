package kms.com.jpa.domain;


import javax.persistence.*;

/**
 * Created by holyeye on 2014. 3. 11..
 */
@Entity
public class Delivery {


    private Long id;

  
    private Order order;

 
    private Address address;

   
    private DeliveryStatus status; //ENUM [READY(준비), COMP(배송)]

    public Delivery() {
    }

    public Delivery(Address address) {
        this.address = address;
        this.status = DeliveryStatus.READY;
    }

    
}
