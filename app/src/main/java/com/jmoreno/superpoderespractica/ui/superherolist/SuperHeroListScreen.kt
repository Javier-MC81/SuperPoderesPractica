package com.jmoreno.superpoderespractica.ui.superherolist

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.jmoreno.superpoderespractica.model.Hero


@Composable
fun SuperHeroListScreen(viewModel: SuperHeroListViewModel) {

    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit){
        viewModel.getSuperheros()
    }


    SuperHeroListScreenContent(state) { hero ->

    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SuperHeroListScreenContent(heros: List<Hero>, onSuperHeroListClicked: (String) -> Unit) {
    Scaffold(modifier = Modifier.fillMaxSize()) {
        LazyColumn(Modifier.padding(it)){
            items(heros, key = {it.id}){
                Text(text = it.name)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SuperHeroListScreen_Preview() {
    SuperHeroListScreenContent(emptyList()) {  }
}

