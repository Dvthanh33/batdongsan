package com.javaweb.service.impl;
import com.javaweb.converter.TransactionConverter;
import com.javaweb.entity.CustomerEntity;
import com.javaweb.entity.TransactionEntity;
import com.javaweb.model.dto.TransactionDTO;
import com.javaweb.model.request.TransactionCreateRequest;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.repository.CustomerRepository;
import com.javaweb.repository.TransactionRepository;
import com.javaweb.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private TransactionConverter transactionConverter;
    @Autowired
    private CustomerRepository customerRepository;
//    @Override
//    public ResponseDTO save(TransactionCreateRequest transactionCreateRequest, Long staffId) {
//        ResponseDTO responseDTO = new ResponseDTO();
//        try{
//            transactionRepository.save(transactionConverter.toTransactionEntity(transactionCreateRequest, staffId));
//            if(transactionCreateRequest.getId() == null){
//                responseDTO.setMessage("Thêm giao dịch thành công");
//            }
//            else responseDTO.setMessage("Cập nhật giao dịch thành công");
//        }catch (Exception e){
//            System.out.println(e);
//        }
//        return responseDTO;
//    }
    @Override
    public ResponseDTO save(TransactionCreateRequest request, Long staffId) {
        ResponseDTO responseDTO = new ResponseDTO();
        TransactionEntity entity;
        try {
            if (request.getId() != null) {
                // ===== UPDATE =====
                entity = transactionRepository.findById(request.getId())
                        .orElseThrow(() -> new RuntimeException("Transaction not found"));
            } else {
                // ===== INSERT =====
                entity = new TransactionEntity();
            }
            // set dữ liệu chung
            entity.setNote(request.getNote());
            entity.setCode(request.getCode());
            // set customer
            CustomerEntity customer = customerRepository
                    .findById(request.getCustomerId())
                    .orElseThrow(() -> new RuntimeException("Customer not found"));
            entity.setCustomer(customer);
            // set staff nếu bạn có field staff
            // entity.setStaff(...)
            transactionRepository.save(entity);
            if (request.getId() == null) {
                responseDTO.setMessage("Thêm giao dịch thành công");
            } else {
                responseDTO.setMessage("Cập nhật giao dịch thành công");
            }
        } catch (Exception e) {
            responseDTO.setMessage("Lỗi khi lưu giao dịch");
            e.printStackTrace();
        }
        return responseDTO;
    }
    @Override
    public List<TransactionDTO> findAllByCodeAndCustomer(String code, Long id) {
        CustomerEntity customer = customerRepository.findById(id).get();
        List<TransactionEntity> transactionEntities = transactionRepository.findAllByCodeAndCustomer(code, customer);
        List<TransactionDTO> transactionDTOS = new ArrayList<>();
        for(TransactionEntity t : transactionEntities){
            transactionDTOS.add(transactionConverter.toTransactionDTO(t));
        }
        return transactionDTOS;
    }

//    @Override
//    public ResponseDTO findById(Long id) {
//        ResponseDTO responseDTO = new ResponseDTO();
//        TransactionEntity transactionEntity = transactionRepository.findById(id).get();
//        responseDTO.setMessage("success");
//        responseDTO.setData(transactionEntity.getNote());
//        return responseDTO;
//    }
@Override
public TransactionCreateRequest findById(Long id) {

    TransactionEntity entity = transactionRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Transaction not found"));

    TransactionCreateRequest dto = new TransactionCreateRequest();
    dto.setId(entity.getId());
    dto.setNote(entity.getNote());
    dto.setCode(entity.getCode());
    dto.setCustomerId(entity.getCustomer().getId());

    return dto;
}
    @Override
    public void deleteTransaction(Long id) {
        transactionRepository.deleteById(id);
    }
}