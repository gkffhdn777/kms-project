package kms.com.jpa.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by holyeye on 2014. 3. 11..
 */

@Entity
public class Member {

  
    private Long id;

    private String name;

    
    private Address address;


    private List<Order> orders = new ArrayList<Order>();


   
}
