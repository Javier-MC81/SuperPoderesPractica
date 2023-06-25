package com.jmoreno.superpoderespractica.ui.heroeDetail

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import androidx.lifecycle.ViewModel
import coil.compose.AsyncImage
import com.jmoreno.superpoderespractica.R
import com.jmoreno.superpoderespractica.data.local.LocalDataSource
import com.jmoreno.superpoderespractica.data.local.mappers.RemoteToLocalMapper
import com.jmoreno.superpoderespractica.data.local.model.LocalHero
import com.jmoreno.superpoderespractica.data.remote.DefaultRepository
import com.jmoreno.superpoderespractica.data.remote.RemoteDataSource
import com.jmoreno.superpoderespractica.data.remote.Repository
import com.jmoreno.superpoderespractica.model.Comics
import com.jmoreno.superpoderespractica.model.Heroe
import com.jmoreno.superpoderespractica.model.ResultSeries
import com.jmoreno.superpoderespractica.model.Thumbnail
import com.jmoreno.superpoderespractica.ui.superherolist.SuperHeroListViewModel
import com.jmoreno.superpoderespractica.ui.superherolist.SuperheroItem


@Composable
fun DetailScreen(id: Long, viewModel: SuperHeroListViewModel) {

    val stateSeries by viewModel.series.observeAsState()
    val stateComics by viewModel.comics.collectAsState()
    val stateHero by viewModel.hero.observeAsState()

    LaunchedEffect(Unit) {
        viewModel.findHero(id)
        viewModel.getSeries(id)
        viewModel.getComics(id)
    }
    stateHero?.let {
        stateSeries?.let { it1 ->
            DetailScreenContent(it1, stateComics, it) {
                viewModel.updateHero(it)
            }
        }
    }
}

@SuppressLint("UnrememberedMutableState")
@Composable
fun DetailScreenContent(
    series: List<ResultSeries>,
    comics: List<Comics>,
    hero: LocalHero,
    onSuperHeroListClicked: (LocalHero) -> Unit
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.padding(16.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(16.dp)
                .weight(1f)
        ) {
            ImageAndSwitchBlock(hero, onSuperHeroListClicked)
            Text(
                text = hero.name,
                style = androidx.compose.material.MaterialTheme.typography.h5,
                modifier = Modifier.padding(8.dp),
                textAlign = TextAlign.Center
            )
        }
        Column(
            Modifier
                .fillMaxSize()
                .weight(2f)
        ) {
            SeriesBlock(series)
            ComicsBlock(comics)
        }
    }

}

@Preview(showBackground = true)
@Composable
fun DetailScreenContent_Preview() {
    DetailScreenContent(
        listOf(
            ResultSeries(
                9, "Captain Carter", "", 2022, 2022, "",
                Thumbnail("http://i.annihil.us/u/prod/marvel/i/mg/b/40/image_not_available", "jpg")
            )
        ),
        listOf(Comics(1010, "Title of comic")),
        LocalHero(
            101,
            "Hulk",
            "http://i.annihil.us/u/prod/marvel/i/mg/6/30/4ce69c2246c21.jpg",
            false
        )
    ) {
    }
}

@SuppressLint("UnrememberedMutableState")
@Composable
fun ImageAndSwitchBlock(hero: LocalHero, onSuperHeroListClicked: (LocalHero) -> Unit) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {

        AsyncImage(
            model = hero.thumbnail,
            contentDescription = "${hero.name} photo",
            modifier = Modifier
                .clip(CircleShape)
                .border(BorderStroke(3.dp, Color.Black), shape = CircleShape),
            contentScale = ContentScale.Crop
        )
        var checkedState = mutableStateOf(hero.favorite)
        //Habilitamos o deshabilitamos el estado del switch en función del estado del atributo favorito del héroe
        Switch(
            checked = checkedState.value,
            onCheckedChange = {
                //Cambiamos el estado del switch según se notifique desde la variable checkedState
                checkedState.value = it
                hero.favorite =
                    checkedState.value//Actualizamos el atributo favorito con el cambio del estado del switch
                Log.d("Heroe", "Marcado a ${hero.favorite}")
                onSuperHeroListClicked(hero)//Se envía a la base de datos el cambio de estado para reemplazar el heroe por este con el atributo nuevo
            },
            colors = SwitchDefaults.colors(Color.Blue)
        )
    }
}
@Preview(showBackground = true)
@Composable
fun ImageAndSwitchBlock_Preview() {
    ImageAndSwitchBlock(LocalHero(101,"Hulk","http://i.annihil.us/u/prod/marvel/i/mg/6/30/4ce69c2246c21.jpg",false)){}

}
@Composable
fun ComicsBlock(comics: List<Comics>) {
    Text(
        text = "ALSO STARRING in COMICS",
        style = MaterialTheme.typography.titleLarge,
        modifier = Modifier
            .padding(10.dp).testTag("Title comic tag")
    )
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.height(200.dp)
    ) {

        items(comics, key = { it.title }) { comic ->
            ComicItem(comic)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ComicsBlock_Preview() {
    ComicsBlock(
        comics = listOf(Comics(1010, "Title of comic"))
    )

}

@Composable
fun SeriesBlock(series: List<ResultSeries>) {
    Text(
        text = "STARRING in SERIES",
        style = MaterialTheme.typography.titleLarge,
        modifier = Modifier
            .padding(10.dp)
    )
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.height(200.dp)
    ) {

        items(series, key = { it.title }) { serie ->
            SerieItem(serie = serie)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SeriesBlock_Preview() {
    SeriesBlock(
        series = listOf(
            ResultSeries(
                9, "Captain Carter", "", 2022, 2022, "",
                Thumbnail(
                    "http://i.annihil.us/u/prod/marvel/i/mg/b/40/image_not_available",
                    "jpg"
                )
            )
        )
    )

}


@Composable
fun SerieItem(serie: ResultSeries, modifier: Modifier = Modifier) {
    Text(
        text = serie.title,
        style = MaterialTheme.typography.bodyLarge,
        modifier = Modifier.padding(3.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun SerieItem_Preview() {
    SerieItem(
        ResultSeries(
            1010, "Title of serie", "Description", 1, 2, "modified",
            Thumbnail("http://i.annihil.us/u/prod/marvel/i/mg/6/30/4ce69c2246c21", ".jpg")
        )
    )
}


@Composable
fun ComicItem(comic: Comics, modifier: Modifier = Modifier) {
    Text(
        text = comic.title,
        style = MaterialTheme.typography.bodyLarge,
        modifier = Modifier.padding(3.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun ComicItem_Preview() {
    ComicItem(Comics(1010, "Title of comic"))
}



