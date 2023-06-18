package com.jmoreno.superpoderespractica.ui.heroeDetail

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.jmoreno.superpoderespractica.R
import com.jmoreno.superpoderespractica.model.Comics
import com.jmoreno.superpoderespractica.model.Heroe
import com.jmoreno.superpoderespractica.model.ResultSeries
import com.jmoreno.superpoderespractica.model.Thumbnail
import com.jmoreno.superpoderespractica.ui.superherolist.SuperHeroListViewModel


@Composable
fun DetailScreen(id: Long, viewModel: SuperHeroListViewModel) {

    val stateSeries by viewModel.series.collectAsState()
    val stateComics by viewModel.comics.collectAsState()

    LaunchedEffect(Unit){
        //viewModel.getSuperheros()
        viewModel.getSeries(id)
        viewModel.getComics(id)

    }
    DetailScreenContent(stateSeries,stateComics) {
    }
}

@Composable
fun DetailScreenContent(series: List<ResultSeries>, comics: List<Comics>, onSuperHeroListClicked: (Long) -> Unit) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.padding(16.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(16.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "Logo",
                Modifier
                    .clip(CircleShape)
                    .border(BorderStroke(3.dp, Color.Black), shape = CircleShape)
                    .background(Color.Green)
            )
            Text(
                text = "HEROE DE MARVEL",
                style = androidx.compose.material.MaterialTheme.typography.h5,
                modifier = Modifier.padding(8.dp),
                textAlign = TextAlign.Center
            )
        }
        Column(Modifier.fillMaxSize()) {
            Text(
                text = "SERIES",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(10.dp).align(Alignment.CenterHorizontally)
            )
            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.weight(1f)
            ) {

                items(series, key = { it.title }) { serie ->
                    SerieItem(serie = serie)
                }
            }
            Text(
                text = "COMICS",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(10.dp).align(Alignment.CenterHorizontally)
            )
            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.weight(1f)
            ) {

                items(comics, key = { it.title }) { comic ->
                    ComicItem(comic = comic)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailScreenScreen_Preview() {
    DetailScreenContent(listOf(ResultSeries(10,"Captain Carter","",2022,2022,"", Thumbnail("http://i.annihil.us/u/prod/marvel/i/mg/b/40/image_not_available","jpg")),
        ResultSeries(9,"Captain Carter","",2022,2022,"",Thumbnail("http://i.annihil.us/u/prod/marvel/i/mg/b/40/image_not_available","jpg")
        )
    )
    )
}
@Composable
fun SerieItem(serie: ResultSeries, modifier: Modifier = Modifier) {
    Text(text = serie.title, style = MaterialTheme.typography.bodyLarge, modifier = Modifier.padding(3.dp))
}
@Composable
fun ComicItem(comic: Comics, modifier: Modifier = Modifier) {
    Text(text = comic.title, style = MaterialTheme.typography.bodyLarge, modifier = Modifier.padding(3.dp))
}

