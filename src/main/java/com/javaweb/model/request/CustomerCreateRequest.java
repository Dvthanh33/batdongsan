package com.javaweb.model.request;

import com.javaweb.model.dto.AbstractDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerCreateRequest extends AbstractDTO {
    private String fullName;
    private String phone;
    private String email;
    private String companyName;
    private String demand;
    private String status;
}