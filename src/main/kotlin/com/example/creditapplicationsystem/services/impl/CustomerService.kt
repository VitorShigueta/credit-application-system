package com.example.creditapplicationsystem.services.impl

import com.example.creditapplicationsystem.exception.BusinessException
import com.example.creditapplicationsystem.models.Customer
import com.example.creditapplicationsystem.repository.CustomerRepository
import com.example.creditapplicationsystem.services.ICustomerService
import org.springframework.stereotype.Service
import java.lang.RuntimeException

@Service
class CustomerService(
    private val customerRepository: CustomerRepository
): ICustomerService {

    override fun save(customer: Customer): Customer =
        this.customerRepository.save(customer);

    override fun findById(id: Long): Customer =
        this.customerRepository.findById(id).orElseThrow{
            throw BusinessException("Id $id nos found");
        }

    override fun delete(id: Long) {
        val customer: Customer = this.findById(id);
        this.customerRepository.delete(customer);
    }
}