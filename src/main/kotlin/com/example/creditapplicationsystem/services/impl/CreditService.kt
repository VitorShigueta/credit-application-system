package com.example.creditapplicationsystem.services.impl

import com.example.creditapplicationsystem.exception.BusinessException
import com.example.creditapplicationsystem.models.Credit
import com.example.creditapplicationsystem.repository.CredityRepository
import com.example.creditapplicationsystem.services.ICreditService
import org.springframework.stereotype.Service
import java.lang.RuntimeException
import java.util.*

@Service
class CreditService(
    private val credityRepository: CredityRepository,
    private val customerService: CustomerService
): ICreditService {

    override fun save(credit: Credit): Credit {
        credit.apply {
            customer = customerService.findById(credit.customer?.id!!)
        }
        return this.credityRepository.save(credit)
    }

    override fun findAllByCustomer(customerId: Long): List<Credit> {
        return this.credityRepository.findAllByCustomerId(customerId)
    }

    override fun findByCreditCode(customerId: Long,creditCode: UUID): Credit {
        val credit = this.credityRepository.findByCreditCode(creditCode)?:throw RuntimeException("CreditCode $creditCode not found");
        return if (credit.customer?.id == customerId) credit else throw BusinessException("Contact admin");
    }
}