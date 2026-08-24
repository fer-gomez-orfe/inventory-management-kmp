package org.montra.crudmuliplatform.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import crud_multiplatform.composeapp.generated.resources.Res
import crud_multiplatform.composeapp.generated.resources.allDrawableResources
import org.jetbrains.compose.resources.painterResource
import org.montra.crudmuliplatform.theme.color_palettes.RedColorBase


class LogInScreen:Screen {
    @Composable
    override fun Content() {
        Column(modifier = Modifier.fillMaxSize().background(
            if (isSystemInDarkTheme()) Color.Black else Color.White
        ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            ){
            cargaLogo()
            Spacer(modifier = Modifier.height(30.dp))
            emailTextField()
            Spacer(modifier = Modifier.height(8.dp))
            passwordTextField()
            Spacer(modifier = Modifier.height(30.dp))
            buttonLogin()
            Spacer(modifier = Modifier.height(8.dp))
            buttonSignIn()

        }
    }

    @Composable
    fun emailTextField(){
        var valueInput by remember { mutableStateOf("") }
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(0.8f).widthIn(max = 400.dp),
            value = valueInput,
            onValueChange = { text ->
                valueInput = text
            },
            enabled = true,
            readOnly = false,
            label = { Text(text = "Correo")},
            placeholder = { Text(text = "ejemplo@montrasolutions.com") },
            leadingIcon = {
                Icon(imageVector = Icons.Default.Email, contentDescription = "")
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            keyboardActions = KeyboardActions(onSearch = {}),
            singleLine = true,
            maxLines = 1,
            minLines = 1,
            shape = RoundedCornerShape(10.dp)
        )
    }

    @Composable
    fun passwordTextField(){
        var valueInput by remember { mutableStateOf("") }
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(0.8f).widthIn(max = 400.dp),
            value = valueInput,
            onValueChange = { text ->
                valueInput = text
            },
            enabled = true,
            readOnly = false,
            label = { Text(text = "Contraseña")},
            placeholder = { Text(text = "********") },
            leadingIcon = {
                Icon(imageVector = Icons.Default.Lock, contentDescription = "")
            },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            keyboardActions = KeyboardActions(onSearch = {}),
            singleLine = true,
            maxLines = 1,
            minLines = 1,
            shape = RoundedCornerShape(10.dp),
        )
    }

    @Composable
    fun buttonLogin(){
        val navigator = LocalNavigator.current
        Button(
            modifier = Modifier.fillMaxWidth(0.5f).widthIn(max = 300.dp),
            onClick = { navigator?.push(InterScreen())},
        ){
            Text(fontSize = 20.sp,text = "Ingresar")
        }
    }

    @Composable
    fun buttonSignIn(){
        val navigator = LocalNavigator.current
        Button(
            modifier = Modifier.fillMaxWidth(0.5f).widthIn(max = 300.dp),
            onClick = { navigator?.push(SignInScreen()) },
            colors = ButtonDefaults.buttonColors(
                containerColor = if (isSystemInDarkTheme()) RedColorBase.secondaryDark else RedColorBase.secondaryLight,
                contentColor = if (isSystemInDarkTheme()) RedColorBase.onSecondaryDark else RedColorBase.onSecondaryLight
            )
        ){
            Text(fontSize = 20.sp,text = "Registrarse")
        }
    }

    @Composable
    fun cargaLogo(){
        Box(
            modifier = Modifier.fillMaxWidth().height(200.dp).background(Color.Black),
            contentAlignment = Alignment.Center) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = null,
                modifier = Modifier.size(100.dp),
                tint = MaterialTheme.colorScheme.primary
            )
        }
    }

}

