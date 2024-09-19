package com.consis.bank.model.dto;

import com.consis.bank.model.enums.CustomerStatus;
import com.consis.bank.model.enums.Gender;
import lombok.Data;

import java.util.List;

@Data
public class CustomerDTO {
    private Long id;
    private String name;
    private Gender gender;
    private Integer age;
    private String identification;
    private String address;
    private String phone;
    private String password;
    private CustomerStatus status;
}