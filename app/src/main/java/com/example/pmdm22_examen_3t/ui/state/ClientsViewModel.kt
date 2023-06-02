package com.example.pmdm22_examen_3t.ui.state

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.example.pmdm22_examen_3t.data.Client
import com.example.pmdm22_examen_3t.data.getFakeClients

enum class DialogKind { CONFIRMARTION, ERROR }

class ClientsViewModel : ViewModel() {

    private val _listClients = getFakeClients().toMutableStateList()
    val listClients get() = _listClients.toList()

    private var _dialog: DialogKind? by mutableStateOf(null)
    val dialog: DialogKind? get() = _dialog
    fun hideDialog() {
        _dialog = null
    }

    private var _alreadySignedEmail by mutableStateOf(false)
    val alreadySignedEmail: Boolean get() = _alreadySignedEmail
    fun falseAlreadySignedEmail() {
        _alreadySignedEmail = false
    }

    private var _alreadySignedUsername by mutableStateOf(false)
    val alreadySignedUsername: Boolean get() = _alreadySignedUsername
    fun falseAlreadySignedUsername() {
        _alreadySignedUsername = false
    }


    fun signIn(username: String, email: String, password: String) {

        _listClients.find { it.email == email }?.let {
            _dialog = DialogKind.ERROR
            _alreadySignedEmail = true
        }
        _listClients.find { it.username == username }?.let {
            _dialog = DialogKind.ERROR
            _alreadySignedUsername = true
        }
        if (!_alreadySignedEmail && !_alreadySignedUsername) {
            _listClients.add(Client(username, email, password))
            _dialog = DialogKind.CONFIRMARTION

        }
    }


}

