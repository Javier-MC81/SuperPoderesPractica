package com.jmoreno.superpoderespractica.ui.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jmoreno.superpoderespractica.data.remote.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val repository: Repository): ViewModel() {

    private val _state = MutableLiveData(false)
    val state: LiveData<Boolean> get() = _state

    /*
    private val _state = MutableStateFlow(false)
    val state: StateFlow<Boolean> get() = _state
     */

    fun doLogin(user: String, password: String){
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO){
                repository.login(user, password)
            }

            _state.value = result != ""
        }
    }
}
