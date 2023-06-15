package com.example.creditapplicationsystem.controllers

import com.example.creditapplicationsystem.DTO.CreditDto
import com.example.creditapplicationsystem.DTO.CreditView
import com.example.creditapplicationsystem.DTO.CreditViewList
import com.example.creditapplicationsystem.DTO.CustomerDto
import com.example.creditapplicationsystem.models.Credit
import com.example.creditapplicationsystem.services.impl.CreditService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.Collections
import java.util.UUID
import java.util.stream.Collectors

@RestController
@RequestMapping("/api/credits")
class CreditController(
    private val creditService: CreditService
) {

    @PostMapping
    fun saveCredit(@RequestBody @Valid creditDto: CreditDto): ResponseEntity<String> {
        val savedCredit: Credit = this.creditService.save(creditDto.toEntity());
        return ResponseEntity.status(HttpStatus.CREATED).body("Customer ${savedCredit.creditCode} - Customer ${savedCredit.customer?.firstName} saved! ");
    }

    @GetMapping
    fun findAllByCustomerId(@RequestParam(value = "customerId") customerId: Long): ResponseEntity<List<CreditViewList>> {
        val creditViewLists: List<CreditViewList> =  this.creditService.findAllByCustomer(customerId).stream().map { credit: Credit -> CreditViewList(credit) }.collect(Collectors.toList())
        return ResponseEntity.status(HttpStatus.OK).body(creditViewLists)
    }

    @GetMapping("/{creditCode}")
    fun findByCreditCode(@RequestParam(value = "customerId") customerId: Long, @PathVariable creditCode: UUID): ResponseEntity<CreditView> {
        val credit: Credit = this.creditService.findByCreditCode(customerId, creditCode);
        return ResponseEntity.status(HttpStatus.OK).body(CreditView(credit));
    }
}