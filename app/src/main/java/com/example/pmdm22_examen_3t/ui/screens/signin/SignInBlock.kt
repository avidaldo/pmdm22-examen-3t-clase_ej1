package com.example.pmdm22_examen_3t.ui.screens.signin

import android.util.Patterns
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.pmdm22_examen_3t.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignInBlock(
    onSignIn: (String, String, String) -> Unit,
    errorUsername: Boolean,
    dismissErrorUsername: () -> Unit,
    errorEmail: Boolean,
    dismissErrorEmail: () -> Unit,
) {

    var username by rememberSaveable { mutableStateOf("") }
    var emailString by rememberSaveable { mutableStateOf("") }
    var passwordString by rememberSaveable { mutableStateOf("") }
    var passwordRepeatString by rememberSaveable { mutableStateOf("") }


    Column(
        modifier = Modifier
            .border(
                width = 2.dp,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                shape = RoundedCornerShape(5.dp)
            )
            .background(
                color = MaterialTheme.colorScheme.primaryContainer,
                shape = RoundedCornerShape(5.dp)
            ).padding(vertical = 20.dp, horizontal = 8.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = username,
            onValueChange = {
                username = it.trim()
                dismissErrorUsername()
            },
            label = { Text(text = stringResource(R.string.user_name)) },
            isError = errorUsername,
        )
        OutlinedTextField(
            value = emailString,
            onValueChange = {
                emailString = it.trim()
                dismissErrorEmail()
            },
            label = { Text(text = stringResource(R.string.email)) },
            isError = errorEmail,
        )
        OutlinedTextField(
            value = passwordString, onValueChange = { passwordString = it },
            label = { Text(text = stringResource(R.string.password)) },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        )
        OutlinedTextField(
            value = passwordRepeatString, onValueChange = { passwordRepeatString = it },
            label = { Text(text = stringResource(R.string.repeat_password)) },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            isError = passwordString != passwordRepeatString && passwordRepeatString.isNotEmpty()
        )
        Button(
            onClick = { onSignIn(username, emailString, passwordString) },
            enabled = username.length > 3 && emailString.isValidEmail() && passwordString.length > 4 &&
                    passwordString == passwordRepeatString
        ) {
            Text(text = "Registrar")
        }
    }


}

fun String.isValidEmail() = Patterns.EMAIL_ADDRESS.matcher(this).matches()