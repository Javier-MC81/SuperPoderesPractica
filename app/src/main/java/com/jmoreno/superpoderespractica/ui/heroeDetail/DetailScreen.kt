package com.jmoreno.superpoderespractica.ui.heroeDetail

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
import com.jmoreno.superpoderespractica.model.Heroe
import com.jmoreno.superpoderespractica.model.ResultSeries
import com.jmoreno.superpoderespractica.ui.superherolist.SuperHeroListViewModel


@Composable
fun DetailScreen(id: Long, viewModel: SuperHeroListViewModel) {

    val state by viewModel.series.collectAsState()

    LaunchedEffect(Unit){
        //viewModel.getSuperheros()
        viewModel.getSeries(id)
        //TODO CALL RETROFIT TO SERIES AND COMICS

    }
    DetailScreenContent(state) {

    }


}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreenContent(series: List<ResultSeries>, onSuperHeroListClicked: (Long) -> Unit) {

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp), contentPadding = it) {
            items(series, key = { it.title }) { serie ->
                SuperheroItem(serie = serie)
            }
        }
    }
    //SuperheroItem(hero = id)
}
/*
@Preview(showBackground = true)
@Composable
fun DetailScreenScreen_Preview() {
    DetailScreenContent(ResultSeries(10,"Captain Carter","",2022,2022,"",
        ResultSeries(10,"Captain Carter","",2022,2022,"")) {  }
}*/
@Composable
fun SuperheroItem(serie: ResultSeries, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(300.dp)
    ) {

        Text(text = serie.title, style = MaterialTheme.typography.headlineLarge, modifier = Modifier.padding(8.dp))
    }

}

