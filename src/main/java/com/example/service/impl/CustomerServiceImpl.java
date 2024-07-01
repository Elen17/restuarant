package com.example.service.impl;

import com.example.entity.Address;
import com.example.entity.Customer;
import com.example.repository.CustomerRepository;
import com.example.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void createCustomer(Map<String, Object> customerDetails) {
        Customer customer = new Customer();
        customer.setFirstName((String) customerDetails.get("firstName"));
        customer.setLastName((String) customerDetails.get("lastName"));

        Map<String, Object> addressDetails = (Map<String, Object>) Optional.of(customerDetails.get("address")).orElse(Collections.emptyMap());

        if(!addressDetails.isEmpty()) {
            Address address = new Address();

            address.setCountry((String) addressDetails.get("country"));
            address.setCity((String) addressDetails.get("city"));
            address.setStreet((String) addressDetails.get("street"));
            address.setState((String) addressDetails.get("state"));
            address.setZip((String) addressDetails.get("zip"));

            customer.setAddress(address);
        }

        customer.setPhone((String) customerDetails.get("phone"));
        customer.setEmail((String) customerDetails.get("email"));
        this.customerRepository.save(customer);
    }
}
