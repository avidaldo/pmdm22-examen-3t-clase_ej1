package com.example.pmdm22_examen_3t.data

data class Client(
    val username: String,
    val email: String,
    val password: String,
)

fun getFakeClients() = List(5) {i -> Client("Cliente ${i+1}", "cliente${i+1}@iesteis.es", "cambiame")}