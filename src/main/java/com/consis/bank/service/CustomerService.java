package com.consis.bank.service;

import com.consis.bank.model.dto.CustomerDTO;
import java.util.List;

public interface CustomerService {
    CustomerDTO save(CustomerDTO customer);
    CustomerDTO update(CustomerDTO customer);
    CustomerDTO findById(Long id);
    List<CustomerDTO> findAll();
    void deleteById(Long id);
}