package com.developerssays.emissionapp.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.developerssays.emissionapp.Route
import com.developerssays.emissionapp.model.UserData
import com.developerssays.emissionapp.viewmodel.AuthViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.math.log


@Composable
fun HomeScreen(
    viewModel: AuthViewModel?,
    navController: NavHostController
) {
    val authLoginRes = viewModel?.loginFlow?.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {

        Row(
            modifier = Modifier.fillMaxWidth().heightIn(40.dp),
            verticalAlignment = Alignment.CenterVertically
        )
        {
            Button( modifier = Modifier.fillMaxWidth(0.7f).heightIn(20.dp),
                onClick = {
                    GlobalScope.launch { viewModel?.addserCheck(userData = UserData()) }
                    viewModel?.logout()
                    navController.navigate(Route.LoginScreen.route)
                },
            ) {
                Text(
                    text = "LogOut Button",
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }


        Text(text = "Home Page")
        Spacer(modifier = Modifier.heightIn(10.dp))

        Button(
            onClick = {
                GlobalScope.launch { viewModel?.fetchData() }
            },

        ) {
            Text(
                text = "FetchData",
                style = MaterialTheme.typography.titleMedium
            )
        }


    }
}







