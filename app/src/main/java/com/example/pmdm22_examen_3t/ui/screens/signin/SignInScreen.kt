package com.example.pmdm22_examen_3t.ui.screens.signin

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.pmdm22_examen_3t.R
import com.example.pmdm22_examen_3t.ui.state.ClientsViewModel
import com.example.pmdm22_examen_3t.ui.state.DialogKind

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignInScreen(viewModel: ClientsViewModel) {

    Scaffold(topBar = {
        TopAppBar(title = {
            Row {
                Text(text = stringResource(R.string.registro), Modifier.weight(1f))
                Text(text = stringResource(R.string.cuenta_d, viewModel.listClients.size))
            }
        })
    }) {
        Box(
            modifier = Modifier.padding(it).fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {

            SignInBlock(
                onSignIn = { username, email, password ->
                    viewModel.signIn(username = username, email = email, password = password)
                },
                errorUsername = viewModel.alreadySignedUsername,
                errorEmail = viewModel.alreadySignedEmail,
                dismissErrorEmail = { viewModel.falseAlreadySignedEmail() },
                dismissErrorUsername = { viewModel.falseAlreadySignedUsername() },
            )

            viewModel.dialog?.let {
                AlertDialog(onDismissRequest = { viewModel.hideDialog() },
                    confirmButton = {
                        Button(onClick = { viewModel.hideDialog() }) {
                            Text(text = "ok")
                        }
                    },
                    text = {
                        Text(
                            text = if (viewModel.dialog == DialogKind.CONFIRMARTION) "todo bien"
                            else "Ya existe"
                        )
                    })
            }
        }


    }

}

