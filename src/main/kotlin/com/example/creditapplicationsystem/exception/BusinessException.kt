package com.example.creditapplicationsystem.exception

data class BusinessException(
    override val message: String?

): RuntimeException(message)


