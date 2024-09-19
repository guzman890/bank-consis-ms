package com.consis.bank.service.mapper;

import com.consis.bank.model.dto.CustomerDTO;
import com.consis.bank.model.entity.Customer;

public class CustomerMapper {

    public static CustomerDTO toDTO(Customer customer) {
        CustomerDTO dto = new CustomerDTO();
        dto.setId(customer.getId());
        dto.setName(customer.getName());
        dto.setGender(customer.getGender());
        dto.setAge(customer.getAge());
        dto.setIdentification(customer.getIdentification());
        dto.setAddress(customer.getAddress());
        dto.setPhone(customer.getPhone());
        dto.setPassword(customer.getPassword());
        dto.setStatus(customer.getStatus());
        return dto;
    }

    public static Customer toEntity(CustomerDTO dto) {
        Customer customer = new Customer();
        customer.setId(dto.getId());
        customer.setName(dto.getName());
        customer.setGender(dto.getGender());
        customer.setAge(dto.getAge());
        customer.setIdentification(dto.getIdentification());
        customer.setAddress(dto.getAddress());
        customer.setPhone(dto.getPhone());
        customer.setPassword(dto.getPassword());
        customer.setStatus(dto.getStatus());
        return customer;
    }
}