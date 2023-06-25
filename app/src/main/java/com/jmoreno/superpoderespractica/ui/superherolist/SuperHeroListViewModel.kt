package com.jmoreno.superpoderespractica.ui.superherolist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jmoreno.superpoderespractica.data.local.model.LocalHero
import com.jmoreno.superpoderespractica.data.remote.Repository
import com.jmoreno.superpoderespractica.model.Comics
import com.jmoreno.superpoderespractica.model.Heroe
import com.jmoreno.superpoderespractica.model.ResultSeries
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SuperHeroListViewModel @Inject constructor(private val repository: Repository): ViewModel() {


    private val _state = MutableStateFlow<List<LocalHero>>(emptyList())
    val state: StateFlow<List<LocalHero>> get() = _state

    private val _series = MutableLiveData<List<ResultSeries>>()
    val series: LiveData<List<ResultSeries>> get() = _series

    private val _comics = MutableStateFlow<List<Comics>>(emptyList())
    val comics: StateFlow<List<Comics>> get() = _comics

    private val _hero = MutableLiveData(LocalHero(1010,"","",false))
    val hero: LiveData<LocalHero> get() = _hero

    fun doLogin(){
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                repository.getWelcome()
            }
            _state.update { result } //Recogemos todos los datos del objeto que inicialmente devuelve la API

            launch(Dispatchers.IO) {
                repository.getHeroesFlow().collect{ heros ->
                    _state.update {  heros } //Nos quedamos a la escuch con el flow abierto de cualquier cambio en la lista de héroes de la BBDD
                }
            }
        }
    }
    fun getSeries(id: Long){
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO){
                repository.getSeries(id)
            }
            val series = result.data.results// Recogemos el objeto que devuelve de las series, pero nos quedamos con el array de series
            _series.value = series
        }
    }
    fun getComics(id: Long) {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO){
                repository.getComics(id)
            }
            val comics = result.data.results// Recogemos el objeto que devuelve de las series, pero nos quedamos con el array de series
            _comics.update { comics }
        }

    }
    fun findHero(id: Long){
        viewModelScope.launch {
            val hero = withContext(Dispatchers.IO){
                repository.getHero(id)
            }
            _hero.value = hero //Buscamos los datos de un heroe en la BBDD a través de su id y los traemos de vuelta para poder pntar sus datos en la pantalla de detalle
        }
    }
    fun updateHero(hero: LocalHero){
        viewModelScope.launch {
            repository.updateHero(hero)//Actualiza el estado de un héroe que haya sido pulsado su switch y haya sido seleccionado/no seleccionado como héroe favorito

        }
    }
}