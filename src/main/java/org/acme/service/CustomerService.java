package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.dto.CustomerDTO;
import org.acme.entity.CustomerEntity;
import org.acme.repository.CustomerRepositry;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@ApplicationScoped
public class CustomerService {

    @Inject
    private CustomerRepositry customerRepositry;

    public Optional<CustomerDTO> findByid(Long id){
      return Optional.of(CustomerDTO.of(customerRepositry.findById(id)));
    }

    public List<CustomerDTO> findAllCustomers(){
        List<CustomerDTO> customerDTOList = new ArrayList<>();
        customerRepositry.findAll().stream().forEach(
                item->{customerDTOList.add(CustomerDTO.of(item));
        });
       return customerDTOList;
    }

    public void creatNewCustomer(CustomerDTO customerDTO){
      customerRepositry.persist(CustomerDTO.of(customerDTO));
    }

    public void changeCustomer(Long id, CustomerDTO customerDTO){
       customerDTO.setId(id);
       customerRepositry.persist(CustomerDTO.of(customerDTO));
    }

    public void deleteCustomer(Long id){
       customerRepositry.deleteById(id);
    }

}
