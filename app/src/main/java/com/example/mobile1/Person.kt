package com.example.mobile1

import java.util.UUID

data class Person(val id: UUID=UUID.randomUUID(), var name: String, var age: Int)
