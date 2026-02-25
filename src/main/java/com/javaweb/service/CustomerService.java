package com.javaweb.service;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.request.CustomerCreateRequest;
import com.javaweb.model.response.ResponseDTO;

import java.util.List;
import java.util.Map;

public interface CustomerService {
    List<CustomerDTO> findAll(Map<String, Object> conditions);
    ResponseDTO save(CustomerDTO customerDTO);
    CustomerDTO findOneById(Long id);
    ResponseDTO liststaffs(Long buildingId);
    ResponseDTO createFromContact(CustomerCreateRequest request);
}
