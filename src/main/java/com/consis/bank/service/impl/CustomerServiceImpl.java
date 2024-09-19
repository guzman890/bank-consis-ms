package com.consis.bank.service.impl;

import com.consis.bank.exception.EntityNotFoundException;
import com.consis.bank.model.dto.CustomerDTO;
import com.consis.bank.service.mapper.CustomerMapper;
import com.consis.bank.repository.CustomerRepository;
import com.consis.bank.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public CustomerDTO save(CustomerDTO customerDTO) {
        return CustomerMapper.toDTO(customerRepository.save(CustomerMapper.toEntity(customerDTO)));
    }

    @Override
    public CustomerDTO update(CustomerDTO customerDTO) {
        return customerRepository.findById(customerDTO.getId())
                .map(existingCustomer -> {
                    existingCustomer.setName(customerDTO.getName());
                    existingCustomer.setGender(customerDTO.getGender());
                    existingCustomer.setAge(customerDTO.getAge());
                    existingCustomer.setIdentification(customerDTO.getIdentification());
                    existingCustomer.setAddress(customerDTO.getAddress());
                    existingCustomer.setPhone(customerDTO.getPhone());
                    existingCustomer.setPassword(customerDTO.getPassword());
                    existingCustomer.setStatus(customerDTO.getStatus());
                    return customerRepository.save(existingCustomer);
                })
                .map(CustomerMapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    @Override
    public CustomerDTO findById(Long id) {
        return customerRepository.findById(id)
                .map(CustomerMapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    @Override
    public List<CustomerDTO> findAll() {
        return customerRepository.findAll().stream()
                .map(CustomerMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        customerRepository.deleteById(id);
    }
}