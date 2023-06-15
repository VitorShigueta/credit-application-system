package com.example.creditapplicationsystem.DTO

import com.example.creditapplicationsystem.models.Credit
import com.example.creditapplicationsystem.models.Customer
import jakarta.validation.constraints.Future
import jakarta.validation.constraints.NotNull
import java.math.BigDecimal
import java.time.LocalDate

data class CreditDto(
    @field:NotNull(message = "Invalid Input")
    val creeditValue: BigDecimal,
    @field:Future
    @field:NotNull(message = "Invalid Input")
    val dayFirstOfInstallment: LocalDate,
    @field:NotNull(message = "Invalid Input")
    val numberOfInstallments: Int,
    @field:NotNull(message = "Invalid Input")
    val customerId: Long,
){
    fun toEntity(): Credit = Credit(
        creditValue = this.creeditValue,
        dayFirstInstallment = this.dayFirstOfInstallment,
        numberOfInstallments = this.numberOfInstallments,
        customer = Customer(id = this.customerId)
    );
}
