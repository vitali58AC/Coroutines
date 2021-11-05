package com.skillbox.github.ui.compose.current_user

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.skillbox.github.ui.current_user.CurrentUserViewModel

@Composable
fun InputNameDialog(viewModel: CurrentUserViewModel) {
    val openDialog = remember { mutableStateOf(false) }
    var name by remember { mutableStateOf("") }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        TextButton(
            onClick = {
                openDialog.value = true
            },
            Modifier
                .padding(8.dp),
        ) {
            Text(text = "Change name", fontSize = 20.sp)
        }
        if (openDialog.value) {
            AlertDialog(
                onDismissRequest = { openDialog.value = false },
                text = {
                    Column {
                        Text(text = "Change name?", style = MaterialTheme.typography.h6)
                        Spacer(modifier = Modifier.height(24.dp))
                        TextField(
                            value = name,
                            onValueChange = { name = it },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                            label = {
                                Text(text = "Enter name")
                            }
                        )
                    }
                },
                confirmButton = {
                    Button(
                        onClick = {
                            openDialog.value = false
                            viewModel.patchUserName(name)
                        }) {
                        Text("Change")
                    }
                },
                dismissButton = {
                    Button(
                        onClick = {
                            openDialog.value = false
                        }) {
                        Text("Cancel")
                    }
                }
            )
        }
    }
}
