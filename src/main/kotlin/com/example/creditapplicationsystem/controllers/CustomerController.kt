package com.example.creditapplicationsystem.controllers

import com.example.creditapplicationsystem.DTO.CustomerDto
import com.example.creditapplicationsystem.DTO.CustomerUpdateDto
import com.example.creditapplicationsystem.DTO.CustomerView
import com.example.creditapplicationsystem.models.Customer
import com.example.creditapplicationsystem.services.impl.CustomerService
import jakarta.validation.Valid
import org.apache.coyote.Response
import org.springframework.data.jpa.domain.AbstractPersistable_.id
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/customers")
class CustomerController(
    private val customerService: CustomerService
) {

    @PostMapping
    fun saveCustomer(@RequestBody @Valid customerDto: CustomerDto): ResponseEntity<String> {
        val savedCustomer = this.customerService.save(customerDto.toEntity());
        return ResponseEntity.status(HttpStatus.CREATED).body("Customer ${savedCustomer.email} saved!");
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long):  ResponseEntity<CustomerView> {
        val customer: Customer = this.customerService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(CustomerView(customer));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteCustomer(@PathVariable id: Long) {
        this.customerService.delete(id);
    }

    @PatchMapping
    fun updateCustomer(@RequestParam(value = "customerId") id: Long, @RequestBody @Valid customerUpdateDto: CustomerUpdateDto): ResponseEntity<CustomerView> {
        val customer: Customer = this.customerService.findById(id);
        val customerToUpdate = customerUpdateDto.toEntity(customer);
        val customerUpdated = this.customerService.save(customerToUpdate);
        return ResponseEntity.status(HttpStatus.OK).body(CustomerView(customerUpdated))
    }
}