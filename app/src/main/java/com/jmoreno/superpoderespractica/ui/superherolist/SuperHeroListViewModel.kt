package com.jmoreno.superpoderespractica.ui.superherolist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jmoreno.superpoderespractica.data.remote.Repository
import com.jmoreno.superpoderespractica.model.Hero
import com.jmoreno.superpoderespractica.model.Heroe
import com.jmoreno.superpoderespractica.model.ResultSeries
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SuperHeroListViewModel @Inject constructor(private val repository: Repository): ViewModel() {


    private val _state = MutableStateFlow<List<Heroe>>(emptyList())
    val state: StateFlow<List<Heroe>> get() = _state

    private val _series = MutableStateFlow<List<ResultSeries>>(emptyList())
    val series: StateFlow<List<ResultSeries>> get() = _series


    fun getSuperheros() {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO){
               // repository.getHeros()
            }

            //_state.update { result }
        }

    }
    fun doLogin(){
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO){
                repository.getWelcome()
            }
            val heroes = result.data.results
            _state.update { heroes }
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
}