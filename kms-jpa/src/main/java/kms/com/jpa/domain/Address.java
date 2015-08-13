package kms.com.jpa.domain;

import javax.persistence.Embeddable;

/**
 * Created by holyeye on 2014. 3. 11..
 */
@Embeddable
public class Address {

    private String city;
    private String street;
    private String zipcode;

    
}
