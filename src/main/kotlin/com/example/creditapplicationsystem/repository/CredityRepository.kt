package com.example.creditapplicationsystem.repository

import com.example.creditapplicationsystem.models.Credit
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface CredityRepository: JpaRepository<Credit, Long> {

    fun findByCreditCode(creditCode: UUID): Credit?;

    @Query(value = "SELECT * FROM CREDIT WHERE CUSTOMER_ID = ?1", nativeQuery = true)
    fun findAllByCustomerId(customerId: Long): List<Credit>
}