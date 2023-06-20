package com.jmoreno.superpoderespractica.ui.superherolist

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

    private val _series = MutableStateFlow<List<ResultSeries>>(emptyList())
    val series: StateFlow<List<ResultSeries>> get() = _series

    private val _comics = MutableStateFlow<List<Comics>>(emptyList())
    val comics: StateFlow<List<Comics>> get() = _comics

    private val _hero = MutableStateFlow(LocalHero(1010,"","",false))
    val hero: StateFlow<LocalHero> get() = _hero


    fun doLogin(){
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                repository.getWelcome()
            }
            _state.update { result }

            launch(Dispatchers.IO) {
                repository.getHeroesFlow().collect{ heros ->
                    _state.update {  heros }
                }
            }
        }
    }
    fun getSeries(id: Long){
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO){
                repository.getSeries(id)
            }
            val series = result.data.results
            _series.update { series }
        }
    }
    fun getComics(id: Long) {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO){
                repository.getComics(id)
            }
            val comics = result.data.results
            _comics.update { comics }
        }

    }
    fun findHero(id: Long){
        viewModelScope.launch {
            val hero = withContext(Dispatchers.IO){
                repository.getHero(id)
            }
            _hero.update { hero }
            /*launch(Dispatchers.IO) {
                repository.getHeroFlow(id).collect{ hero ->
                    _hero.update {  hero }
                }
            }*/
        }
    }
    fun updateHero(hero: LocalHero){
        viewModelScope.launch {
            repository.updateHero(hero)

        }
    }
}