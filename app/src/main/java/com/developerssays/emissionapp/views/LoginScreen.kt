package com.developerssays.emissionapp.views

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.developerssays.emissionapp.Route
import com.developerssays.emissionapp.compounds.EmailTextField
import com.developerssays.emissionapp.utils.Resource
import com.developerssays.emissionapp.viewmodel.AuthViewModel


@Composable
fun LoginScreen(
    viewModel: AuthViewModel?,
    navController: NavHostController)
{

    var email by remember { mutableStateOf("") }

    var password by remember { mutableStateOf("") }

    val authLoginRes = viewModel?.loginFlow?.collectAsState()


    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally)
    {


        Text(
            text = "Login Screen",

            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.heightIn(20.dp))
        EmailTextField(value = email,
            onValueChange ={email=it} , labelName ="E-mail" )

        Spacer(modifier = Modifier.heightIn(20.dp))
        EmailTextField(value = password,
            onValueChange ={password=it} , labelName ="password" )

        Spacer(modifier = Modifier.heightIn(20.dp))
        Button(
            onClick = {
                viewModel?.loginUser(email, password)
            },
        ) {
            Text(text = "Login",
                style = MaterialTheme.typography.titleMedium)
        }

        Spacer(modifier = Modifier.heightIn(10.dp))
        Button(
            onClick = {
                navController.navigate(Route.SignUpScreen.route)
            },
        ) {
            Text(text = "SignUp",
                style = MaterialTheme.typography.titleMedium)
        }

    }

    authLoginRes?.value.let {
        when(it){
            is Resource.Failure->{
                Toast.makeText(LocalContext.current, "${it.exception.message.toString()}",
                    Toast.LENGTH_SHORT).show()
            }

            is Resource.Loading ->{
                CircularProgressIndicator(
                    modifier = Modifier.size(50.dp)
                )
            }
            is Resource.Success ->{
                LaunchedEffect(Unit ){
                    navController.navigate(Route.HomeScreen.route){
                        popUpTo(Route.LoginScreen.route) { inclusive = true }
                    }

                }

            }

            else -> {}
        }
    }




}



@Preview(showBackground = true)
@Composable
fun LoginScreenPreviewLight() {

    AppTheme {
        LoginScreen(null, rememberNavController())
    }
}

@Composable
fun AppTheme(content: @Composable () -> Unit) {

}

