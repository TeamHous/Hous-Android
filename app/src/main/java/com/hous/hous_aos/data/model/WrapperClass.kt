package com.hous.hous_aos.data.model

data class WrapperClass<T>(
    val status: Int,
    val success: Boolean,
    val message: String,
    val data: T? = null
)
