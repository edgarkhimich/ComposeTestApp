package com.example.composetestapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val scaffoldState = rememberScaffoldState()

            var textFieldState by remember {
                mutableStateOf("")
            }

            val scope = lifecycleScope
            val focusManager = LocalFocusManager.current

            Scaffold(
                modifier = Modifier.fillMaxSize(),
                scaffoldState = scaffoldState
            ) {

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            horizontal = 30.dp
                        )
                ) {
                    TextField(
                        value = textFieldState,
                        label = {
                            Text(
                                text = "Enter your name"
                            )
                        },
                        onValueChange = {
                            textFieldState = it
                        },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    )
                    Button(
                        onClick = {
                            focusManager.clearFocus()

                            scope.launch {
                                scaffoldState.snackbarHostState.showSnackbar(
                                    message = "Hello $textFieldState"
                                )
                            }
                        }
                    ) {
                        Text(
                            text = "Here we go"
                        )
                    }
                }
            }
        }
    }
}