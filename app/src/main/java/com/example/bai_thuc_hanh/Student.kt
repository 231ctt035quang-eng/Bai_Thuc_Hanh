package com.example.bai_thuc_hanh

import java.io.Serializable

data class Student(
    val id: String,
    val name: String,
    val major: String,
    val email: String,
    val phone: String
) : Serializable