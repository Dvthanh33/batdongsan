package com.javaweb.service.impl;
import com.javaweb.entity.AssignmentCustomerEntity;
import com.javaweb.entity.CustomerEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.model.dto.AssignmentCustomerDTO;
import com.javaweb.repository.AssignmentCustomerRepository;
import com.javaweb.repository.CustomerRepository;
import com.javaweb.repository.UserRepository;
import com.javaweb.service.AssignmentCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
@Service
public class AssignmentCustomerServiceImpl implements AssignmentCustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AssignmentCustomerRepository assignmentCustomerRepository;
    @Autowired
    private UserRepository userRepository;
    @Transactional
    @Override
    public void addAssignmentCustomerEntity(AssignmentCustomerDTO assignmentCustomerDTO){
        CustomerEntity customerEntity = customerRepository.findById(assignmentCustomerDTO.getCustomerId()).get();
        assignmentCustomerRepository.deleteByCustomer(customerEntity);
        List<Long> staffIds = assignmentCustomerDTO.getStaffs();
        for(Long it : staffIds){
            AssignmentCustomerEntity assignmentCustomerEntity = new AssignmentCustomerEntity();
            assignmentCustomerEntity.setCustomer(customerEntity);
            UserEntity userEntity =  userRepository.findById(it).get();
            assignmentCustomerEntity.setStaff(userEntity);
            assignmentCustomerRepository.save(assignmentCustomerEntity);
        }
    }
}
