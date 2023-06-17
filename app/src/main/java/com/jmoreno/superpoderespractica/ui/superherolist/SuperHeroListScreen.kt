package com.jmoreno.superpoderespractica.ui.superherolist

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.jmoreno.superpoderespractica.model.DataClass
import com.jmoreno.superpoderespractica.model.Hero
import com.jmoreno.superpoderespractica.model.Heroe
import com.jmoreno.superpoderespractica.ui.ui.login.LoginViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach



@Composable
fun SuperHeroListScreen(viewModel: SuperHeroListViewModel,onSuperHeroListClicked: (Long) -> Unit) {

    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit){
        //viewModel.getSuperheros()
        viewModel.doLogin()
    }



    SuperHeroListScreenContent(state) { heroId ->
        onSuperHeroListClicked(heroId)
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SuperHeroListScreenContent(heros: List<Heroe>, onSuperHeroListClicked: (Long) -> Unit) {
    /*Scaffold(modifier = Modifier.fillMaxSize()) {
        LazyColumn(Modifier.padding(it)){
            items(heros, key = {it.id}){
                Text(text = it.name)
            }
        }
    }*/
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp), contentPadding = it) {
            items(heros, key = { it.id }) { hero ->
                SuperheroItem(hero = hero, onHeroClick = onSuperHeroListClicked)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SuperHeroListScreen_Preview() {
    SuperHeroListScreenContent(emptyList()) {  }
}
@Composable
fun SuperheroItem(hero: Heroe, modifier: Modifier = Modifier, onHeroClick: (Long) -> Unit) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(300.dp).clickable { onHeroClick(hero.id) }
    ) {
        AsyncImage(
            model = "${hero.thumbnail?.path}.${hero.thumbnail?.thumbnailExtension}",
            contentDescription = "${hero.name} photo",
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentScale = ContentScale.Crop
        )
        Text(text = hero.name, style = MaterialTheme.typography.headlineLarge, modifier = Modifier.padding(8.dp))
    }
}


