package com.javaweb.repository;
import com.javaweb.entity.CustomerEntity;
import com.javaweb.entity.AssignmentCustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssignmentCustomerRepository extends JpaRepository<AssignmentCustomerEntity,Long> {
    public void deleteByCustomer(CustomerEntity customerEntity);
    public List<AssignmentCustomerEntity> findByCustomer(CustomerEntity customerEntity);
    public void deleteByCustomerIdIn(List<Long> ids);

}
