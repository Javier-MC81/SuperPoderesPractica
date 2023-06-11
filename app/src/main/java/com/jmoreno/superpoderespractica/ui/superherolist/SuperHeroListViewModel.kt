package com.jmoreno.superpoderespractica.ui.superherolist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jmoreno.superpoderespractica.data.remote.Repository
import com.jmoreno.superpoderespractica.model.Hero
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


    private val _state = MutableStateFlow<List<Hero>>(emptyList())
    val state: StateFlow<List<Hero>> get() = _state


    fun getSuperheros() {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO){
                repository.getHeros()
            }

            _state.update { result }
        }
    }
}