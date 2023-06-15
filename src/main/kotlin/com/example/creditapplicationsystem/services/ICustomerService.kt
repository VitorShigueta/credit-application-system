package com.example.creditapplicationsystem.services

import com.example.creditapplicationsystem.models.Credit
import com.example.creditapplicationsystem.models.Customer


interface ICustomerService {

    fun save(customer: Customer): Customer

    fun findById(id: Long): Customer

    fun delete(id: Long)
}