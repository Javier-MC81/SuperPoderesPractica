package com.jmoreno.superpoderespractica

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.jmoreno.superpoderespractica.ui.navigation.NavigationGraph
import com.jmoreno.superpoderespractica.ui.superherolist.SuperHeroListViewModel
import com.jmoreno.superpoderespractica.ui.theme.SuperPoderesPracticaTheme
import com.jmoreno.superpoderespractica.ui.ui.login.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val loginViewModel: LoginViewModel by viewModels()
    private val superHeroListViewModel: SuperHeroListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SuperPoderesPracticaTheme {
                NavigationGraph(loginViewModel, superHeroListViewModel)
            }
        }
    }
}
@Composable
fun Screen1(onClickListener: ()->(Unit)){
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Red), contentAlignment = Alignment.Center){
        Text("Screen 1", modifier = Modifier.clickable { onClickListener() })
    }
}

@Preview
@Composable
fun Screen1_Preview() {
    Screen1({})
}

@Composable
fun Screen2(onClickListener: (Int?)->(Unit)){
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.Blue), verticalArrangement = Arrangement.Center){
        Text("Screen 2 without default",  modifier = Modifier.clickable { onClickListener(6) })
        Text("Screen 2 with default",  modifier = Modifier.clickable { onClickListener(null) })
    }
}

@Composable
fun Screen3(text: String, date: Int?){
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Green), contentAlignment = Alignment.Center){
        if (date == -1){
            Text(text = "ERROR")
        } else {
            Text("Screen 3: $text at $date")
        }
    }
}

