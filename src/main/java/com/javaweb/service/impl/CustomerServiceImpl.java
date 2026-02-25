package com.javaweb.service.impl;
import com.javaweb.converter.CustomerConverter;
import com.javaweb.entity.CustomerEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.request.CustomerCreateRequest;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.model.response.StaffResponseDTO;
import com.javaweb.repository.CustomerRepository;
import com.javaweb.repository.UserRepository;
import com.javaweb.service.CustomerService;
import com.javaweb.utils.CustomerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerConverter customerConverter;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CustomerUtils customerUtils;

    @Override
    public List<CustomerDTO> findAll(Map<String, Object> conditions) {
        List<CustomerEntity> customerEntityList = customerRepository.findAll(conditions);
        List<CustomerDTO> customerDTOS = new ArrayList<>();
        for (CustomerEntity c : customerEntityList) {
            customerDTOS.add(customerConverter.toCustomerDTO(c));
        }
        return customerDTOS;
    }

    @Override
    public ResponseDTO save(CustomerDTO customerDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        CustomerEntity customerEntity = customerConverter.toCustomerEntity(customerDTO);
        if (customerDTO.getId() != null) {
            CustomerEntity customerEntityTmp = customerRepository.findById(customerDTO.getId()).get();
            customerUtils.setCustomerField(customerEntity, customerEntityTmp);
        }
        try {
            customerRepository.save(customerEntity);
            if (customerDTO.getId() != null) {
                responseDTO.setMessage("Cập nhật khách hàng thành công");
            } else responseDTO.setMessage("Thêm khách hàng thành công");
        } catch (Exception e) {
            System.out.println("e");
            responseDTO.setMessage("Thêm khách hàng thất bại");
        }
        return responseDTO;
    }

    @Override
    public CustomerDTO findOneById(Long id) {
        CustomerEntity customerEntity = customerRepository.findById(id).get();
        if (customerEntity != null)
            return customerConverter.toCustomerDTO(customerEntity);
        return null;
    }

    @Override
    public ResponseDTO liststaffs(Long buildingId) {
        CustomerEntity building = customerRepository.findById(buildingId).get();
        List<UserEntity> staffs = userRepository.findByStatusAndRoles_Code(1, "STAFF");
        List<UserEntity> staffAssignment = building.getUserEntities();
        List<StaffResponseDTO> staffResponseDTOS = new ArrayList<>();
        ResponseDTO responseDTO = new ResponseDTO();
        for (UserEntity it : staffs) {
            StaffResponseDTO staffResponseDTO = new StaffResponseDTO();
            staffResponseDTO.setFullName(it.getFullName());
            staffResponseDTO.setStaffId(it.getId());
            if (staffAssignment.contains(it)) {
                staffResponseDTO.setChecked("checked");
            } else {
                staffResponseDTO.setChecked("");
            }
            staffResponseDTOS.add(staffResponseDTO);
        }
        responseDTO.setData(staffResponseDTOS);
        responseDTO.setMessage("success");
        return responseDTO;
    }

    @Override
    public ResponseDTO createFromContact(CustomerCreateRequest request) {
        ResponseDTO response = new ResponseDTO();
        try {
            CustomerEntity entity = customerConverter.toCustomerEntity(request);
            entity.setIsActive(1); // nếu có field này
            customerRepository.save(entity);
            response.setMessage("Gửi liên hệ thành công");
            response.setStatus("success");
        } catch (Exception e) {
            e.printStackTrace();
            response.setMessage("Gửi liên hệ thất bại");
            response.setStatus("error");
        }
        return response;
    }
}
