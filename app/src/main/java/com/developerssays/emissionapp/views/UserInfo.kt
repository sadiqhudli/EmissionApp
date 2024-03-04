package com.developerssays.emissionapp.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.developerssays.emissionapp.R
import com.developerssays.emissionapp.viewmodel.AuthViewModel


@Composable
fun UserInfo(
    viewModel: AuthViewModel?,
    navController: NavController,
){

    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally)
    {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
            .heightIn(55.dp)
            , horizontalArrangement = Arrangement.Absolute.Left,
            verticalAlignment = Alignment.CenterVertically
            )
        {


            Image(painter = painterResource(id = R.drawable.ic_launcher_background),
                modifier = Modifier
                    .size(80.dp)
                    .clip(shape = RoundedCornerShape(40.dp)),
                contentDescription ="" )
            Spacer(modifier = Modifier.width(10.dp))

            Text(
                text = "name",
                style = MaterialTheme.typography.displaySmall,
                color = MaterialTheme.colorScheme.onSurface
            )



        }

    }


}



@Preview(showBackground = true)
@Composable
fun userInfoPreview(){

  //  UserInfo()
}