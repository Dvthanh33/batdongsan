package com.javaweb.entity;
import javax.persistence.*;

@Entity
@Table(name="assignmentcustomer")
public class AssignmentCustomerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name="staffid")
    private UserEntity staff;
    @ManyToOne
    @JoinColumn(name="customerid")
    private CustomerEntity customer;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public CustomerEntity getCustomer() {
        return customer;
    }
    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }
    public UserEntity getStaff() {
        return staff;
    }
    public void setStaff(UserEntity staff) {
        this.staff = staff;
    }
}
