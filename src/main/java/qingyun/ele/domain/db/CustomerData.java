package qingyun.ele.domain.db;
// Generated 2017-4-17 14:45:18 by Hibernate Tools 3.2.2.GA


import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Customer generated by hbm2java
 */
@Entity
@Table(name="customer_data"
    ,catalog="ele"
)
public class CustomerData  implements java.io.Serializable {


     private Long id;
     private Long idCustomer;
     private String customerName;
     private String customerCode;
     private String customerAddress;
     private String timeDuration;
     private Float capacity;
    
    public CustomerData() {
    }

     @Id @GeneratedValue(strategy=IDENTITY)
    
    @Column(name="id", unique=true, nullable=false)
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    
    @Column(name="customer_name", length=64)
    public String getCustomerName() {
        return this.customerName;
    }
    
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    
    @Column(name="time_duration", length=64)
    public String getTimeDuration() {
        return this.timeDuration;
    }
    
    public void setTimeDuration(String timeDuration) {
        this.timeDuration = timeDuration;
    }
    
    @Column(name="customer_code", length=64)
    public String getCustomerCode() {
        return this.customerCode;
    }
    
    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }
    
  
    @Column(name="capacity", precision=12, scale=0)
    public Float getCapacity() {
        return this.capacity;
    }
    
    public void setCapacity(Float capacity) {
        this.capacity = capacity;
    }

    @Column(name="id_customer")
	public Long getIdCustomer() {
		return idCustomer;
	}

	public void setIdCustomer(Long idCustomer) {
		this.idCustomer = idCustomer;
	}

	 @Column(name="customer_address", length=128)
	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

}

