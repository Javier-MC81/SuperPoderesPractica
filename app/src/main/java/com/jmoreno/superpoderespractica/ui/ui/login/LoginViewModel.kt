/*package com.jmoreno.superpoderespractica.ui.ui.login


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jmoreno.superpoderespractica.data.remote.Repository
import com.jmoreno.superpoderespractica.model.Heroe
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val repository: Repository): ViewModel() {

    /*private val _state = MutableLiveData(false)
    val state: LiveData<Boolean> get() = _state*/
    private val _state =  MutableStateFlow<List<Heroe>>(emptyList())

    val state: StateFlow<List<Heroe>> get() = _state
    /*
    private val _state = MutableStateFlow(false)
    val state: StateFlow<Boolean> get() = _state
     */

    fun doLogin(){
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO){
                repository.getWelcome()
            }
            val heroes = result.data.results
            _state.update { heroes }
        }
    }
}
*/