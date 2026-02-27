package com.javaweb.api.admin;
import com.javaweb.entity.CustomerEntity;
import com.javaweb.model.dto.AssignmentCustomerDTO;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.repository.CustomerRepository;
import com.javaweb.service.AssignmentCustomerService;
import com.javaweb.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Transactional
@RequestMapping("/api/customer")
public class CustomerAPI {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private CustomerRepository  customerRepository;
    @Autowired
    private AssignmentCustomerService assignmentCustomerService;
    @PostMapping
    public ResponseDTO addOrUpdateCustomer(@Valid @RequestBody CustomerDTO customerDTO, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            result.getFieldErrors().forEach(error ->
                    errors.put(error.getField(), error.getDefaultMessage())
            );
            ResponseDTO response = new ResponseDTO();
            response.setMessage("Validation failed");
            response.setData(errors);
            return response;
        }
        return customerService.save(customerDTO);
    }
    @DeleteMapping
    public void deleteCustomer(@RequestBody List<Long> ids){
        for(Long id : ids){
            CustomerEntity customer = customerRepository.findById(id).get();
            // xóa quan hệ many-to-many
            customer.getUserEntities().clear();
            customerRepository.save(customer);
            // transaction sẽ tự xóa nhờ cascade REMOVE
            customerRepository.deleteById(id);
        }
    }
    @GetMapping("/{id}/staffs")
    public ResponseDTO loadstaffs(@PathVariable Long id){
        ResponseDTO result = customerService.liststaffs(id);
        return result;
    }
    @PostMapping("/assignment")
    public void updateAssignmentCustomer(@RequestBody AssignmentCustomerDTO assignmentCustomerDTO){
        assignmentCustomerService.addAssignmentCustomerEntity(assignmentCustomerDTO);
    }
}