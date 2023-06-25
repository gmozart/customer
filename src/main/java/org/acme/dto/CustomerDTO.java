package org.acme.dto;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;
import org.acme.entity.CustomerEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Data
@Jacksonized
@Builder
public class CustomerDTO {

    private Long id;

    private String name;

    private String phone;

    private String email;

    private String address;

    private Long age;

    public static CustomerDTO of(CustomerEntity customerEntity){
        return CustomerDTO.builder()
                .id(customerEntity.getId())
                .name(customerEntity.getName())
                .phone(customerEntity.getPhone())
                .email(customerEntity.getEmail())
                .address(customerEntity.getAddress())
                .age(customerEntity.getAge())
                .build();
    }

    public static CustomerEntity of(CustomerDTO customerDTO){
        return CustomerEntity.builder()
                .id(customerDTO.getId())
                .name(customerDTO.getName())
                .phone(customerDTO.getPhone())
                .email(customerDTO.getEmail())
                .address(customerDTO.getAddress())
                .age(customerDTO.getAge())
                .build();
    }


    public static Optional<CustomerDTO> of(Optional<CustomerEntity> customerEntity){
       return customerEntity.stream().map(CustomerDTO::of).findAny();
    }
}
