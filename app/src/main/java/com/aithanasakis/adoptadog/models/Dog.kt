package com.aithanasakis.adoptadog.models

// one model shared across all layers...npt optimal...
data class Dog(
    val id: Int,
    val name: String,
    val age: Int,
    val description: String,
    val photoUrl: String,
    val breed: String
)