package com.jmoreno.superpoderespractica.ui.superherolist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.jmoreno.superpoderespractica.data.local.model.LocalHero
import com.jmoreno.superpoderespractica.model.Heroe
import com.jmoreno.superpoderespractica.model.Thumbnail


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
fun SuperHeroListScreenContent(heros: List<LocalHero>, onSuperHeroListClicked: (Long) -> Unit) {
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
    SuperHeroListScreenContent(listOf(LocalHero(101,"Hulk","http://i.annihil.us/u/prod/marvel/i/mg/6/30/4ce69c2246c21.jpg",false),
        LocalHero(102,"Hulk","http://i.annihil.us/u/prod/marvel/i/mg/6/30/4ce69c2246c21.jpg",false),
        LocalHero(103,"Hulk","http://i.annihil.us/u/prod/marvel/i/mg/6/30/4ce69c2246c21.jpg",false))) {  }
}
@Composable
fun SuperheroItem(hero: LocalHero, modifier: Modifier = Modifier, onHeroClick: (Long) -> Unit) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(300.dp).clickable { onHeroClick(hero.id) }
    ) {
        AsyncImage(
            model = hero.thumbnail,
            contentDescription = "${hero.name} photo",
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentScale = ContentScale.Crop

        )
        Row(horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.wrapContentHeight(Alignment.CenterVertically)){
            Text(text = hero.name, style = MaterialTheme.typography.headlineLarge, modifier = Modifier.padding(8.dp).weight(3f))
            if(hero.favorite){
                Icon(imageVector = Icons.Default.Favorite, contentDescription = "Favorite", tint = Color.Red, modifier = Modifier.weight(1f))
            }
        }

    }
}
@Preview(showBackground = true)
@Composable
fun SuperheroItem_Preview() {
    SuperheroItem(LocalHero(101,"Hulk","http://i.annihil.us/u/prod/marvel/i/mg/6/30/4ce69c2246c21.jpg",false)) {  }
}


